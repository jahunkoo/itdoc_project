package com.kmbridge.itdoc.activity;

import com.kmbridge.itdoc.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class HanbangInfoActivity extends ActionBarActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hanbang_info);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle bundle = getIntent().getExtras();
		String position = bundle.getString("position");
		if(position.equals("0")){
			Log.d("koo", "test up");
		}else if(position.equals("1")){
			Log.d("koo", "test down");
		}
		
		
	}

	
	
	
}
