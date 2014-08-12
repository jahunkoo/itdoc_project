package com.kmbridge.itdoc.activity;

import java.util.Arrays;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RelativesActivity extends FragmentActivity implements
		OnClickListener {
	Button btnFollow0;
	Button btnFollow1;
	Button btnFollow2;
	Button btnFollow3;
	Button btnFollow4;
	Button btnFollow5;
	Button btnFollow6;

	boolean checkItem0 = false;
	boolean checkItem1 = false;
	boolean checkItem2 = false;
	boolean checkItem3 = false;
	boolean checkItem4 = false;
	boolean checkItem5 = false;
	boolean checkItem6 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relatives);

		setLayout();
		setListener();

	}

	private void setLayout() {
		btnFollow0 = (Button) findViewById(R.id.btn_follow0);
		btnFollow1 = (Button) findViewById(R.id.btn_follow1);
		btnFollow2 = (Button) findViewById(R.id.btn_follow2);
		btnFollow3 = (Button) findViewById(R.id.btn_follow3);
		btnFollow4 = (Button) findViewById(R.id.btn_follow4);
		btnFollow5 = (Button) findViewById(R.id.btn_follow5);
		btnFollow6 = (Button) findViewById(R.id.btn_follow6);
	}

	private void setListener() {
		btnFollow0.setOnClickListener(this);
		btnFollow1.setOnClickListener(this);
		btnFollow2.setOnClickListener(this);
		btnFollow3.setOnClickListener(this);
		btnFollow4.setOnClickListener(this);
		btnFollow5.setOnClickListener(this);
		btnFollow6.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_follow0:

			if (checkItem0 == true) {
				btnFollow0.setBackgroundResource(R.drawable.follow_on);
				checkItem0 = false;
			} else {
				btnFollow0.setBackgroundResource(R.drawable.follow_off);
				checkItem0 = true;
			}
			break;
		case R.id.btn_follow1:

			if (checkItem1 == true) {
				btnFollow1.setBackgroundResource(R.drawable.follow_on);
				checkItem1 = false;
			} else {
				btnFollow1.setBackgroundResource(R.drawable.follow_off);
				checkItem1 = true;
			}
			break;
		case R.id.btn_follow2:

			if (checkItem2 == true) {
				btnFollow2.setBackgroundResource(R.drawable.follow_on);
				checkItem2 = false;
			} else {
				btnFollow2.setBackgroundResource(R.drawable.follow_off);
				checkItem2 = true;
			}
			break;
		case R.id.btn_follow3:

			if (checkItem3 == true) {
				btnFollow3.setBackgroundResource(R.drawable.follow_on);
				checkItem3 = false;
			} else {
				btnFollow3.setBackgroundResource(R.drawable.follow_off);
				checkItem3 = true;
			}
			break;
		case R.id.btn_follow4:

			if (checkItem4 == true) {
				btnFollow4.setBackgroundResource(R.drawable.follow_on);
				checkItem4 = false;
			} else {
				btnFollow4.setBackgroundResource(R.drawable.follow_off);
				checkItem4 = true;
			}
			break;
		case R.id.btn_follow5:

			if (checkItem5 == true) {
				btnFollow5.setBackgroundResource(R.drawable.follow_on);
				checkItem5 = false;
			} else {
				btnFollow5.setBackgroundResource(R.drawable.follow_off);
				checkItem5 = true;
			}
			break;
		case R.id.btn_follow6:

			if (checkItem6 == true) {
				btnFollow6.setBackgroundResource(R.drawable.follow_on);
				checkItem6 = false;
			} else {
				btnFollow6.setBackgroundResource(R.drawable.follow_off);
				checkItem6 = true;
			}
			break;

		}
	}
}
