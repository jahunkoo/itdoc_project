package com.kmbridge.itdoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.thread.ClinicDetailThread;

public class KmClinicDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_detail);
		
		int clinicId = getIntent().getIntExtra("clinicId", -1);
		
		View view = new View(this);
		view.setTag("KmClinicDetailView");
		
		TextView name;
		
		name = (TextView) findViewById(R.id.textview_km_clinic_detail_clinic_name);
		
		name.setTag("detailViewName");
		
		ClinicDetailThread mThread = new ClinicDetailThread("getDetailKmClinic", this, clinicId);
		mThread.start();
		
	}
	
	public void setElements() {
		Log.d("kim","kmClinicDetailActivity (35) setElements run");
	}
	
	
	
}
