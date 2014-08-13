package com.kmbridge.itdoc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.kmbridge.itdoc.R;

public class UserProfileEditActivity extends Activity implements OnClickListener {

	LinearLayout favorateChange;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_user_profile_edit);

		getActionBar().setTitle("프로필 수정하기");

		setContents();

	}

	private void setContents() {
		favorateChange = (LinearLayout) findViewById(R.id.linearlayout_user_profile_edit_favorate_change);

		favorateChange.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.linearlayout_user_profile_edit_favorate_change:
			Intent intent = new Intent(this, KeywordSelectActivity.class);
			startActivity(intent);
			break;

		default:
			break;

		}

	}

}
