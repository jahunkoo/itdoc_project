package com.kmbridge.itdoc.thread;

import java.util.ArrayList;

import android.content.Context;
import android.os.Message;

import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicDetailView;

public class ClinicDetailThread extends Thread {

	

	private ClinicDetailHandler handler;
	private String methodUrl;
	private Context context;
	private int id;
	
	
	
	public ClinicDetailThread(String methodUrl, Context context, int id )  {
		this.handler = new ClinicDetailHandler(context);	
		this.methodUrl = methodUrl;
		this.context = context;
		this.id = id;
	}


	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		msg.what = ClinicDetailHandler.SHOW_LOADING_LAYOUT;
		handler.sendMessage(msg);
		
		ConnectionBridge bridge = new ConnectionBridge();
		ArrayList<KmClinicDetailView> kmClinicDetailView = bridge.getKmClinicDetailViewList(methodUrl, context, id);
		
		msg.obj = kmClinicDetailView.get(0);
		msg.what = ClinicDetailHandler.SET_ELEMENTS;
		handler.sendMessage (msg);
		
		msg = handler.obtainMessage();
		msg.what = ClinicDetailHandler.END_LOADING_LAYOUT;
		handler.sendMessage(msg);
		
	}

}
