package com.kmbridge.itdoc.thread;



import android.content.Context;
import android.util.Log;

import com.kmbridge.itdoc.activity.UserProfileActivity;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.UserView;

public class UserProfileConnectionThread extends Thread{

	private UserProfileConnectionHandler handler;
	private String methodUrl;
	private UserProfileActivity activity;
	private String myEmail;
	private String userEmail;
	
	public UserProfileConnectionThread(Context context, String methodUrl, String myEmail, String userEmail)  {
		this.handler = new UserProfileConnectionHandler(context);	
		activity =  (UserProfileActivity) context;
		this.methodUrl = methodUrl;
		this.myEmail = myEmail;
		this.userEmail = userEmail;
	}

	

	@Override
	public void run() {
		handler.sendEmptyMessage(UserProfileConnectionHandler.SHOW_LOADING_LAYOUT);
		ConnectionBridge bridge = new ConnectionBridge();
		UserView userView= bridge.getUserViewByEmail(activity, methodUrl, myEmail, userEmail);
		Log.d("koo", "result:"+userView.toString());
		handler.sendEmptyMessage(UserProfileConnectionHandler.END_LOADING_LAYOUT);
		handler.setUserView(userView);
		handler.sendEmptyMessage(UserProfileConnectionHandler.START_SET_DATA);
		handler.sendEmptyMessage(UserProfileConnectionHandler.START_DOWNLOAD_PROFILE_IMG);
		
	}

	
	
}
