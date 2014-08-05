package com.kmbridge.itdoc.thread;

import java.util.Properties;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.fragment.JoinFragment;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;

public class JoinConnectThread extends Thread{
	
	private JoinConnectHandler handler;
	private String methodUrl;
	private Properties prop;
	private Context context;
	public String result;
	
	JoinFragment joinfragment = new JoinFragment();
	

	public JoinConnectThread(String methodUrl, Properties prop, Context context)
	{
		this.handler = new JoinConnectHandler(context, joinfragment);
		this.methodUrl = methodUrl;
		this.prop = prop;
		this.context = context;
	}
	
	@Override
	public void run()
	{
		Message msg = handler.obtainMessage();
		handler.sendEmptyMessage(JoinConnectHandler.SHOW_LOADING_LAYOUT);
		
		ConnectionBridge bridge = new ConnectionBridge();
		result= bridge.register(methodUrl, prop, context);
		
		if(result.equals(ItDocConstants.SUCCESS)){
			//joinfragment.JoinSucess();
			//Log.d("JoinMsg","Thread : "+result);
			msg = handler.obtainMessage();
			msg.what = JoinConnectHandler.SHOW_JOIN;
			msg.obj = result;
			handler.sendMessage(msg);
			
		}else if(result.equals(ItDocConstants.EXIST)){	
			msg = handler.obtainMessage();
			msg.what = JoinConnectHandler.SHOW_EXIT;
			handler.sendMessage(msg);
		}else if(result.equals(ItDocConstants.ERROR)){
			msg = handler.obtainMessage();
			msg.what = JoinConnectHandler.SHOW_ERROR;
			handler.sendMessage(msg);
		}
		handler.sendEmptyMessage(ProfilePictureConnectHandler.END_LOADING_LAYOUT);
	}
	
	public String getResult()
	{
		return result;
	}

}
