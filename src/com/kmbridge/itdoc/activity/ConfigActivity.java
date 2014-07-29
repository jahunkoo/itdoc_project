package com.kmbridge.itdoc.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.kmbridge.itdoc.R;

public class ConfigActivity extends BasicActivity implements OnClickListener {
	private TextView txtView_activity_config_push;
	private TextView txtView_activity_config_alarm;
	private TextView txtView_activity_config_helpcomment;
	private TextView txtView_activity_config_helpdesk;
	private TextView txtView_activity_config_evaluation;
	private TextView txtView_activity_config_logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);

		setLayout();
		setListener();
	}

	private void setLayout() {
		// TODO Auto-generated method stub
		txtView_activity_config_push = (TextView) findViewById(R.id.txt_view_push);
		txtView_activity_config_alarm = (TextView) findViewById(R.id.txt_view_alarm);
		txtView_activity_config_helpcomment = (TextView) findViewById(R.id.txt_view_helpcomment);
		txtView_activity_config_helpdesk = (TextView) findViewById(R.id.txt_view_helpdesk);
		txtView_activity_config_evaluation = (TextView) findViewById(R.id.txt_view_evalution);
		txtView_activity_config_logout = (TextView) findViewById(R.id.txt_view_logout);
	}

	private void setListener() {
		// TODO Auto-generated method stub
		txtView_activity_config_push.setOnClickListener(this);
		txtView_activity_config_alarm.setOnClickListener(this);
		txtView_activity_config_helpcomment.setOnClickListener(this);
		txtView_activity_config_helpdesk.setOnClickListener(this);
		txtView_activity_config_evaluation.setOnClickListener(this);
		txtView_activity_config_logout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.txt_view_push:
			Toast.makeText(this, "푸시 알림", Toast.LENGTH_SHORT).show();
			break;

		case R.id.txt_view_alarm:
			Toast.makeText(this, "알림 소리", Toast.LENGTH_SHORT).show();
			break;

		case R.id.txt_view_helpcomment:
			Toast.makeText(this, "도움말", Toast.LENGTH_SHORT).show();
			break;

		case R.id.txt_view_helpdesk:
			Toast.makeText(this, "고객센터", Toast.LENGTH_SHORT).show();
			break;

		case R.id.txt_view_evalution:
			Toast.makeText(this, "잇닷(it doc) 평가하기", Toast.LENGTH_SHORT).show();
			break;

		case R.id.txt_view_logout:
			break;
		}

	}
}
