package com.kmbridge.itdoc.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class ProfilePictureActivity extends ImageSelectHelperActivity implements View.OnClickListener{

	ActionBar actionBar = null; // 액션바 세팅 시작
	private Button btn_activity_profile_upload;
	private Button btn_activity_profile_change;
	private Button btn_activity_profile_finish;
    private ImageView userProfileImgView;
    public boolean isNotFirst; //처음 설치 여부 체크 변수
    
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
		//*****************************actionbar title setting ***********************
		getActionBar().setTitle(R.string.title_activity_profile_picture);
		//****************************************************************************
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
			intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
	
	@Override
	public void onBackPressed() {
		
		//ActionBar actionbar = this.getActionBar();
    	//actionbar.hide();
    	//
    	//처음 설치 여부를 확인
    	//SharedPreferenceUtil userInfo = new SharedPreferenceUtil();
    	//isNotFirst=SharedPreferenceUtil.isExist(this, ItDocConstants.SHARED_KEY_FIRST_CHECK);
		//
		//Log.d("check_kim","join_check = "+isNotFirst);
    	////처음 설치가 처음 설치가 아닌경우 메인으로 이동
    	//
		//if(isNotFirst==true)
    	//{
    	//	Intent intent = new Intent(this,MainDrawerActivity.class);
    	//	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//	startActivity(intent);
    	//}
    	//else
    	//{
    	//	Intent intent = new Intent(this,ScreenSlideActivity.class);
    	//	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	//	startActivity(intent);
    	//}
		//
		/*findViewById(R.id.linearLayout_pager).setVisibility(View.VISIBLE);
		findViewById(R.id.linearLayout_screen_slide_for_join).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_screen_slide_for_login).setVisibility(View.GONE);
		*/
		//밑에 코드에 의해서 실제 백버튼 기능이 수행됨.
		super.onBackPressed();
	}
}
