package com.kmbridge.itdoc.activity;

import com.kmbridge.itdoc.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class KeywordSelectActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keyword_select);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		getActionBar().setTitle("관심 분야 설정");
		
	}

	
}
