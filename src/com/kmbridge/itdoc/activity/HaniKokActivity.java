package com.kmbridge.itdoc.activity;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HaniKokActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hani_kok);
		
		Intent intent = new Intent(this,MainDrawerActivity.class);
		startActivity(intent);
		finish();
	}
}
