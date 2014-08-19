package com.kmbridge.itdoc.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;

public class SupporterActivity extends ActionBarActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supporters);
		
		//1번 메니페스트
		//2번 ActionBarActivity달기
		//3번째 이거 추가
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		Bundle bundle = getIntent().getExtras();
		String position = bundle.getString("position");
		if(position.equals("up")){
			Log.d("koo", "test up");
		}else if(position.equals("down")){
			Log.d("koo", "test down");
		}
		
		
	}

	
	
	
}
