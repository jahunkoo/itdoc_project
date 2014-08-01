package com.kmbridge.itdoc.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;

public class ProfilePictureActivity extends ImageSelectHelperActivity implements View.OnClickListener{

	ActionBar actionBar = null; // 액션바 세팅 시작
	private Button btn_activity_profile_upload;
	private Button btn_activity_profile_change;
	private Button btn_activity_profile_finish;
    private ImageView userProfileImgView;
    
	public void setButtonsVisible(boolean isShowUploadBtn, boolean isShowChangeBtn, boolean isShowFinishBtn){
		if(isShowUploadBtn)	btn_activity_profile_upload.setVisibility(View.VISIBLE);
		else 				btn_activity_profile_upload.setVisibility(View.GONE);
		
		if(isShowChangeBtn) btn_activity_profile_change.setVisibility(View.VISIBLE);
		else				btn_activity_profile_change.setVisibility(View.GONE);
		
		if(isShowFinishBtn)	btn_activity_profile_finish.setVisibility(View.VISIBLE);
		else 				btn_activity_profile_finish.setVisibility(View.GONE);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_picture);
		
		setElements();
		setLisner();
		setButtonsVisible(true, false, false);
		
		getSelectedImageFile(); // extract selected & saved image file.
	}
	
	private void setLisner() {
		btn_activity_profile_upload.setOnClickListener(this);
		btn_activity_profile_change.setOnClickListener(this);
		btn_activity_profile_finish.setOnClickListener(this);
		//userProfileImgView.setOnClickListener(this);
	}

	private void setElements() {
		btn_activity_profile_upload = (Button)findViewById(R.id.button_activity_profile_picture_upload);
		btn_activity_profile_change = (Button)findViewById(R.id.button_activity_profile_picture_change);
		btn_activity_profile_finish = (Button)findViewById(R.id.button_activity_profile_picture_finish);
		userProfileImgView = (ImageView) findViewById(R.id.imageview_activity_profile_picture_user_profile);
		
	}

	private void callImageActivity(){
		super.callActivity = this;
		setImageSizeBoundary(400);
		setCropOption(1, 1);
		startSelectImage();
	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_activity_profile_picture_upload:
			callImageActivity();
			
			break;
		case R.id.button_activity_profile_picture_change:
			callImageActivity();
			
			break;
		case R.id.button_activity_profile_picture_finish:
			Intent intent = new Intent(this, MainDrawerActivity.class);
			startActivity(intent);
			finish();
			break;
		

		}
	}
	
	public void setButtonEnable(boolean isEnable){
		btn_activity_profile_upload.setEnabled(isEnable);
		btn_activity_profile_change.setEnabled(isEnable);
		btn_activity_profile_finish.setEnabled(isEnable);
	}
}
