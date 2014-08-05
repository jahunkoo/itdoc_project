package com.kmbridge.itdoc.thread;

import java.util.Map;
import java.util.Properties;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.fragment.LoginFragment;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

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
		
		//Map<String, String> loginMap;
		
		ConnectionBridge bridge = new ConnectionBridge();
		//isLoginCheck= bridge.login(methodUrl, prop, context);
		Map<String, String> loginMap = bridge.login(methodUrl, prop, context);
		
		String LoginCheck = loginMap.get(ItDocConstants.PARSE_LOGINRESULT);
		String LoginName = loginMap.get(ItDocConstants.PARSE_NAME);
		Log.d("kim2","LoginThread : "+LoginCheck);
		Log.d("kim2","LoginTHread : "+LoginName);
		
		
		
		//if(isLoginCheck==false){
		if(LoginCheck.equals("false")){
			msg = handler.obtainMessage();
			msg.what = LoginConnectHandler.SHOW_ERROR;
			handler.sendMessage(msg);
		}else if(LoginCheck.equals("true")){	
			//사용자 이름(무자열) 자르기
			String firstName = LoginName.split("_")[0];
			String lastName = LoginName.split("_")[1];
			SharedPreferenceUtil.setData(context, ItDocConstants.SHARED_KEY_NAME, lastName+firstName);
			msg = handler.obtainMessage();
			msg.what = LoginConnectHandler.SHOW_LOGIN;
			handler.sendMessage(msg);
		}
		handler.sendEmptyMessage(ProfilePictureConnectHandler.END_LOADING_LAYOUT);
	}
}
