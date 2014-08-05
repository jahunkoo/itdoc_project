package com.kmbridge.itdoc.thread;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.ProfilePictureActivity;
import com.kmbridge.itdoc.image.RoundedImageView;

public class ProfilePictureConnectHandler extends Handler {

	public static final int SHOW_LOADING_LAYOUT = 0;
	public static final int END_LOADING_LAYOUT = 1;
	public static final int SHOW_IMAGE = 2;
	
	private View loadingView;
	private Activity activity;
	
	
	private ProfilePictureActivity profilePictureActivity;
	
	public ProfilePictureConnectHandler(Context context) {
		activity = (Activity) context;
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadingView = inflator.inflate(R.layout.connection_loading, null);
		activity.addContentView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		profilePictureActivity = ((ProfilePictureActivity)activity);
	}
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {
		case SHOW_LOADING_LAYOUT:
			//*************버튼 비활성화
			profilePictureActivity.setButtonEnable(false);
			loadingView.bringToFront();
			loadingView.setVisibility(View.VISIBLE);
			Log.d("koo", "loading page start");
			break;
		case END_LOADING_LAYOUT:
			loadingView.setVisibility(View.GONE);
			//*************버튼 활성화
			profilePictureActivity.setButtonEnable(true);
			Log.d("koo", "loading page end");
			break;
		case SHOW_IMAGE:
			Log.d("koo", "loading end imageview_show start");
			File uploadFile =  (File) msg.obj;
			//ProfilePictureActivity profilePictureActivity = ((ProfilePictureActivity)activity);
			// sample size 를 적용하여 bitmap load.
			Bitmap bitmap = profilePictureActivity.loadImageWithSampleSize(uploadFile);
			// image boundary size 에 맞도록 이미지 축소.
			bitmap = profilePictureActivity.resizeImageWithinBoundary(bitmap);
			bitmap = RoundedImageView.getCroppedBitmap(bitmap, bitmap.getWidth());
			View view = activity.findViewById(R.id.imageview_activity_profile_picture_user_profile);
			ImageView imgView = (ImageView) view;
			imgView.setImageBitmap(bitmap);
			
			profilePictureActivity.setButtonsVisible(false, true, true);
			break;
		default:
			break;
		}
	}
	
	
}
