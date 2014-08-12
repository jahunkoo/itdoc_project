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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_follower);
	
		getActionBar().setTitle("윤성수님 팔로워");
	
		setElements();
		
	}
	
	private void setElements() {
		
		email = "test@gmail.com";
		
		followerAdapter = new FollowerAdapter(this, email, 1);
		
		mListView = (ListView) findViewById(R.id.listview_user_follower);
		
		mListView.setAdapter(followerAdapter);
		
	}
	
}
