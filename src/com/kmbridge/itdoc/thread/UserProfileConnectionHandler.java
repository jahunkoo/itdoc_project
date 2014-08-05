package com.kmbridge.itdoc.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.UserProfileActivity;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.util.ItDocConstants;

public class UserProfileConnectionHandler extends Handler {

	public static final int SHOW_LOADING_LAYOUT = 0;
	public static final int END_LOADING_LAYOUT = 1;
	public static final int START_SET_DATA = 2;
	public static final int START_DOWNLOAD_PROFILE_IMG = 3;
	private View loadingView;
	private UserProfileActivity activity;
	private UserView downloadedUserView;
	
	public UserProfileConnectionHandler(Context context) {
		activity = (UserProfileActivity) context;
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadingView = inflator.inflate(R.layout.connection_loading, null);
		activity.addContentView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}
	
	
	public void setUserView(UserView userView){
		this.downloadedUserView = userView;
	}
	
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		
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
		case START_SET_DATA:
			setData();
			break;
		case START_DOWNLOAD_PROFILE_IMG:
			getUserProfileImg(downloadedUserView.getPicturePath());
			break;
			/*
		 * case SEND_THREAD_STOP_MESSAGE: mCountThread.stopThread();
		 * tv_Count.setText("Count Thread가 중지 되었습니다."); break;
		 */
		default:
			break;
		}
		
	}
	
	
	private void setData(){
		activity.setDownloadData(downloadedUserView);
	}
	
	
	public void getUserProfileImg(String picturePath){
		String url = "http://yss159.cafe24.com:8080/ItDocImgServer/getPicture?picturePath=" + picturePath +"&objectType="+ItDocConstants.OBJECT_TYPE_USER;
		activity.imageLoader.DisplayUserImage(url, activity.getUserProfileImgView());
	}
	
	
	
}
