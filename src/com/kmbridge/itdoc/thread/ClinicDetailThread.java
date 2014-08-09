package com.kmbridge.itdoc.thread;

import java.util.ArrayList;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicDetailView;

public class ClinicDetailThread extends Thread {

	private ClinicDetailHandler handler;
	private String methodUrl;
	private Context context;
	private int id;

	public ClinicDetailThread(String methodUrl, Context context, int id) {
		this.handler = new ClinicDetailHandler(context);
		this.methodUrl = methodUrl;
		this.context = context;
		this.id = id;
	}

	@Override
	public void run() {
		
		/*handler.sendEmptyMessage(ClinicDetailHandler.SHOW_LOADING_LAYOUT);

		ConnectionBridge bridge = new ConnectionBridge();
		ArrayList<KmClinicDetailView> kmClinicDetailView = bridge.getKmClinicDetailViewList(methodUrl, context, id);
		handler.setElements(kmClinicDetailView.get(0));
		
		handler.sendEmptyMessage(ClinicDetailHandler.SET_ELEMENTS);

		handler.sendEmptyMessage(ClinicDetailHandler.END_LOADING_LAYOUT);*/

	}

}
