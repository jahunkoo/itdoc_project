package com.kmbridge.itdoc.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.thread.ClinicDetailThread;

public class KmClinicDetailActivity extends FragmentActivity implements OnClickListener{

	TextView name;
	Button gotoFollower;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_detail);
		
		int clinicId = getIntent().getIntExtra("clinicId", -1);
		
		ClinicDetailThread mThread = new ClinicDetailThread("getDetailKmClinic", this, clinicId);
		mThread.start();
		
	}

	public void setElements(KmClinicDetailView kmClinicDetailView) {
		
		name = (TextView) findViewById(R.id.textview_km_clinic_detail_clinic_name);
		gotoFollower = (Button) findViewById(R.id.button_km_clinic_detail_gotofollower);
		
		gotoFollower.setOnClickListener(this);
		
		name.setText(kmClinicDetailView.getName());
		
	}

	@Override
	public void onClick(View v) {

		
		switch(v.getId()) {
		
		case R.id.button_km_clinic_detail_gotofollower : 
			
			Intent intent = new Intent(this,UserLikeClinicActivity.class);
			
			startActivity(intent);
			
			break;
			
			default :
				break;
		
		}
		
	}
	
}
