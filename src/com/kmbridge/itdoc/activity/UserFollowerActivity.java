package com.kmbridge.itdoc.activity;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.FollowerAdapter;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class UserFollowerActivity extends Activity{

	FollowerAdapter followerAdapter;
	ListView mListView;
	String email;
	int type;
	
	// type이 0이면 팔로잉	
	// type이 1이면 팔로워

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_follower);
		
		
		type = getIntent().getExtras().getInt("followType");
		
		if(type == 1) {
			getActionBar().setTitle("윤성수님 팔로워");
		} else if( type == 0) {
			getActionBar().setTitle("윤성수님 팔로잉");
		}
		
		setElements();
		
	}
	
	private void setElements() {
		
		email = "test@gmail.com";
		
		followerAdapter = new FollowerAdapter(this, email, type);
		
		mListView = (ListView) findViewById(R.id.listview_user_follower);
		
		mListView.setAdapter(followerAdapter);
		
	}
	
}
