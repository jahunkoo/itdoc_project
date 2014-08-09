package com.kmbridge.itdoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ReviewAdapter;

public class SeeAllReviewListActivity extends Activity{

	ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_all_review_list);
		
		getActionBar().setTitle("윤성수");
		
		setElements();
		
		ReviewAdapter reviewAdapter = new ReviewAdapter(this);
		
		mListView.setAdapter(reviewAdapter);
		
	}

	
	private void setElements() {
		mListView = (ListView) findViewById(R.id.listview_see_all_review_list);
	}
	
}
