package com.kmbridge.itdoc.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class LoadingViewThread extends Thread implements Runnable {

	public static final int SHOW_LOADING_LAYOUT = 0;
	public static final int END_LOADING_LAYOUT = 1;
	private View view;
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.d("koo", "ItDocHandler handleMessage");
			switch (msg.what) {
			case SHOW_LOADING_LAYOUT:
				view.bringToFront();
				view.setVisibility(View.VISIBLE);
				Log.d("koo", "loading page start");
				break;
			case END_LOADING_LAYOUT:
				// view.setVisibility(View.GONE);
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
	};

	public LoadingViewThread(View view) {
		super();
		this.view = view;
	}

	
	@Override
	public void run() {
		super.run();
		Log.d("koo", "LoadingViewThread run start");
		Message msg = mHandler.obtainMessage();
		msg.what = ItDocHandler.SHOW_LOADING_LAYOUT;
		mHandler.sendMessage(msg);
		Log.d("koo", "LoadingViewThread run end");
	}

}
