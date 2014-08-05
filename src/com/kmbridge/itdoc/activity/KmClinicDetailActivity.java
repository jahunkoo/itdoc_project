package com.kmbridge.itdoc.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.thread.ClinicDetailThread;

public class KmClinicDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_detail);
		
		int clinicId = getIntent().getIntExtra("clinicId", -1);
		
		ClinicDetailThread mThread = new ClinicDetailThread("getDetailKmClinic", this, clinicId);
		mThread.start();
		
	}

	public void setElements(KmClinicDetailView kmClinicDetailView) {
		
		TextView name;
		
		name = (TextView) findViewById(R.id.textview_km_clinic_detail_clinic_name);
		
		name.setText(kmClinicDetailView.getName());
		
		
	}
	
	
	
}
