package com.kmbridge.itdoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.kmbridge.itdoc.R;

public class BasicActivity extends FragmentActivity implements OnClickListener {

	Button btn_activity_basic_usermanager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);

		btn_activity_basic_usermanager = (Button) findViewById(R.id.usermanger);
		btn_activity_basic_usermanager.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// 회원가입
		case R.id.usermanger:
			Intent intent = new Intent(this, UserManagerActivity.class);
			startActivity(intent);
			break;
		}

	}
}
