package com.kmbridge.itdoc.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ReviewDialogHandler extends Handler {

	Context context;

	public static final int MSG_LIKE = 1;
	public static final int MSG_SOSO = 2;
	public static final int MSG_BAD = 3;
	public static final int MSG_COMPLETE = 4;

	public ReviewDialogHandler(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {

		super.handleMessage(msg);

		
		switch (msg.what) {
		
		case MSG_COMPLETE:
			break;

		default:
			break;
		}

	}

}
