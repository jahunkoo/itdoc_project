package com.kmbridge.itdoc.activity;

import com.kmbridge.itdoc.R;

import android.app.Activity;
import android.os.Bundle;

public class KeywordSelectActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keyword_select);
		
		getActionBar().setTitle("관심 분야 설정");
		
	}

	
}
