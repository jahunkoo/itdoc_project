package com.kmbridge.itdoc.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.kmbridge.itdoc.R;

public class BasicActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
	}
}
