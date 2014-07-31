package com.kmbridge.itdoc.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kmbridge.itdoc.R;

public class ProfilePictureActivity extends ImageSelectHelperActivity implements View.OnClickListener{

	ActionBar actionBar = null; // 액션바 세팅 시작
	private Button btn_activity_profile_imageUpload;
	private Button btn_activity_profile_change;
	private Button btn_activity_profile_finish;
    
    
	public void setButtonsVisible(boolean isShowUploadBtn, boolean isShowChangeBtn, boolean isShowFinishBtn){
		if(isShowUploadBtn)	btn_activity_profile_imageUpload.setVisibility(View.VISIBLE);
		else 				btn_activity_profile_imageUpload.setVisibility(View.GONE);
		
		if(isShowChangeBtn) btn_activity_profile_change.setVisibility(View.VISIBLE);
		else				btn_activity_profile_change.setVisibility(View.GONE);
		
		if(isShowFinishBtn)	btn_activity_profile_finish.setVisibility(View.VISIBLE);
		else 				btn_activity_profile_finish.setVisibility(View.GONE);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_picture);
		
		btn_activity_profile_imageUpload = (Button)findViewById(R.id.button_activity_profile_picture_upload);
		btn_activity_profile_change = (Button)findViewById(R.id.button_activity_profile_picture_change);
		btn_activity_profile_finish = (Button)findViewById(R.id.button_activity_profile_picture_finish);
		btn_activity_profile_imageUpload.setOnClickListener(this);
		
		btn_activity_profile_finish.setOnClickListener(this);
		btn_activity_profile_finish.setVisibility(View.GONE);
	

		/*findViewById(R.id.image_upload).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// setImageSizeBoundary(400); // optional. default is
						// 500.
						// setCropOption(1, 1); // optional. default is no crop.
						// setCustomButtons(btnGallery, btnCamera, btnCancel);
						// // you can set these buttons.
						startSelectImage();
					}
				});*/

		getSelectedImageFile(); // extract selected & saved image file.
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_activity_profile_picture_upload:
			setImageSizeBoundary(400);
			setCropOption(1, 1);
			startSelectImage();
			//작업중 .... 2014_07_30 
			//btn_activity_profile_goLogin.setVisibility(View.VISIBLE);
			//btn_activity_profile_imageUpload.setVisibility(View.GONE);
			break;
		

		}
	}
}
