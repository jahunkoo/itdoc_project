package com.kmbridge.itdoc.thread;

import java.util.Properties;

import android.content.Context;
import android.os.Message;

import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.fragment.LoginFragment;
import com.kmbridge.itdoc.util.ItDocConstants;

public class LoginConnectThread extends Thread{
	
	private LoginConnectHandler handler;
	private String methodUrl;
	private Properties prop;
	private Context context;
	private boolean isLoginCheck = false;
	
	LoginFragment loginfragment = new LoginFragment();
	

	public LoginConnectThread(String methodUrl, Properties prop, Context context)
	{
		this.handler = new LoginConnectHandler(context, loginfragment);
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
		isLoginCheck= bridge.login(methodUrl, prop, context);
		
		if(isLoginCheck==false){
			msg = handler.obtainMessage();
			msg.what = LoginConnectHandler.SHOW_ERROR;
			handler.sendMessage(msg);
		}else if(isLoginCheck==true){	
			msg = handler.obtainMessage();
			msg.what = LoginConnectHandler.SHOW_LOGIN;
			handler.sendMessage(msg);
		}
		handler.sendEmptyMessage(ProfilePictureConnectHandler.END_LOADING_LAYOUT);
	}
}
