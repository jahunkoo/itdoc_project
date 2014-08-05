package com.kmbridge.itdoc.thread;

import java.io.File;
import java.text.ParseException;


import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.util.ItDocConstants;

import android.content.Context;
import android.os.Message;

/**
 * 
 * @author	: jahunkoo
 * @date	: 2014. 8. 1.
 * @desc	: 프로필사진을 업로드 할 때, ImageSelectHelperActivity의 dofinalProcess에서 호출되는 스레드
 */
public class ProfilePictureConnectThread extends Thread{

	private ProfilePictureConnectHandler handler;
	private String methodUrl;
	private File uploadFile;
	private Context context;
	private String id;
	
	
	
	public ProfilePictureConnectThread(String methodUrl, File uploadFile, Context context, String id )  {
		this.handler = new ProfilePictureConnectHandler(context);	
		this.methodUrl = methodUrl;
		this.uploadFile = uploadFile;
		this.context = context;
		this.id = id;
	}


	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		handler.sendEmptyMessage(ProfilePictureConnectHandler.SHOW_LOADING_LAYOUT);
		
		ConnectionBridge bridge = new ConnectionBridge();
		String result = bridge.insertImage(methodUrl, uploadFile, context, id);
		if(result.equals(ItDocConstants.SUCCESS)){
			msg = handler.obtainMessage();
			msg.what = ProfilePictureConnectHandler.SHOW_IMAGE;
			msg.obj = uploadFile;
			handler.sendMessage(msg);
		}else{	// 에러 표시를 해줘야 함
			
		}
		handler.sendEmptyMessage(ProfilePictureConnectHandler.END_LOADING_LAYOUT);
	}

	
}
