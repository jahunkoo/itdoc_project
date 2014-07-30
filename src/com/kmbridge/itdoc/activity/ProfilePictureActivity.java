package com.kmbridge.itdoc.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kmbridge.itdoc.R;

public class ProfilePictureActivity extends ImageSelectHelperActivity implements View.OnClickListener{

	ActionBar actionBar = null; // 액션바 세팅 시작
	private Button btn_activity_profile_goLogin;
    private Button btn_activity_profile_imageUpload;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_picture);
		
		btn_activity_profile_imageUpload = (Button)findViewById(R.id.image_upload);
		btn_activity_profile_imageUpload.setOnClickListener(this);
		btn_activity_profile_goLogin = (Button)findViewById(R.id.go_login);
		btn_activity_profile_goLogin.setOnClickListener(this);
		btn_activity_profile_goLogin.setVisibility(View.GONE);
	

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
		case R.id.image_upload:
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
