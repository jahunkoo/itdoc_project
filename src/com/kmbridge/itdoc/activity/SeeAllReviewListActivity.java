package com.kmbridge.itdoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ReviewAdapter;

public class SeeAllReviewListActivity extends Activity implements OnClickListener {

	ListView mListView;
	TextView setting;
	private boolean bGood = true;
	private boolean bSoso = true;
	private boolean bBad = true;
	ReviewAdapter reviewAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_all_review_list);

		getActionBar().setTitle("윤성수");

		setElements();

		reviewAdapter = new ReviewAdapter(this, isbGood(), isbSoso(), isbBad());

		mListView.setAdapter(reviewAdapter);

	}

	private void setElements() {
		mListView = (ListView) findViewById(R.id.listview_see_all_review_list);
		setting = (TextView) findViewById(R.id.button_see_all_review_list_setting);

		setting.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.button_see_all_review_list_setting:

			ReviewRecommendDialog dialog = new ReviewRecommendDialog(this, isbGood(), isbSoso(), isbBad());
			dialog.show();

			break;

		default:
			break;
		}

	}

	public boolean isbGood() {
		return bGood;
	}

	public void setbGood(boolean bGood) {
		this.bGood = bGood;
	}

	public boolean isbSoso() {
		return bSoso;
	}

	public void setbSoso(boolean bSoso) {
		this.bSoso = bSoso;
	}

	public boolean isbBad() {
		return bBad;
	}

	public void setbBad(boolean bBad) {
		this.bBad = bBad;
	}

	public void adapterChange() {
		reviewAdapter = new ReviewAdapter(this, isbGood(), isbSoso(), isbBad());
		reviewAdapter.notifyDataSetChanged();
		mListView.setAdapter(reviewAdapter);
	}

}
