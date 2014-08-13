package com.kmbridge.itdoc.activity;

import com.kmbridge.itdoc.R;

import android.app.Activity;
import android.os.Bundle;

public class UserProfileEditActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_user_profile_edit);
		
		getActionBar().setTitle("프로필 수정하기");
		
	}

	
	
}
