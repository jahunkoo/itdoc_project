package com.kmbridge.itdoc.thread;

import java.io.File;
import java.text.ParseException;


import com.kmbridge.itdoc.connect.ConnectionBridge;

import android.content.Context;
import android.os.Message;

public class ConnectionThread extends Thread{

	private LoadingViewHandler handler;
	private String methodUrl;
	private File uploadFile;
	private Context context;
	private String id;
	
	
	
	public ConnectionThread(String methodUrl, File uploadFile, Context context, String id )  {
		this.handler = new LoadingViewHandler(context);	
		this.methodUrl = methodUrl;
		this.uploadFile = uploadFile;
		this.context = context;
		this.id = id;
	}


	@Override
	public void run() {
		
		handler.sendEmptyMessage(LoadingViewHandler.SHOW_LOADING_LAYOUT) ;
		
		ConnectionBridge bridge = new ConnectionBridge();
		bridge.insertImage(methodUrl, uploadFile, context, id);
		
		handler.sendEmptyMessage(LoadingViewHandler.END_LOADING_LAYOUT) ;
		
	}

	
}
