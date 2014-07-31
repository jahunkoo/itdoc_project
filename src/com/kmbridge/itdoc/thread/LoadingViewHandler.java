package com.kmbridge.itdoc.thread;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.kmbridge.itdoc.R;

public class LoadingViewHandler extends Handler {

	public static final int SHOW_LOADING_LAYOUT = 0;
	public static final int END_LOADING_LAYOUT = 1;
	private View loadingView;
	
	
	public LoadingViewHandler(Context context) {
		Activity activity = (Activity) context;
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadingView = inflator.inflate(R.layout.connection_loading, null);
		activity.addContentView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	
	}
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		Log.d("koo", "ItDocHandler handleMessage");
		switch (msg.what) {
		case SHOW_LOADING_LAYOUT:
			loadingView.bringToFront();
			loadingView.setVisibility(View.VISIBLE);
			Log.d("koo", "loading page start");
			break;
		case END_LOADING_LAYOUT:
			loadingView.setVisibility(View.GONE);
			Log.d("koo", "loading page end");
			break;
		/*
		 * case SEND_THREAD_STOP_MESSAGE: mCountThread.stopThread();
		 * tv_Count.setText("Count Thread가 중지 되었습니다."); break;
		 */
		default:
			break;
		}
	}
	
	
}
