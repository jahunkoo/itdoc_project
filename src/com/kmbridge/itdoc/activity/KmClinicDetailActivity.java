package com.kmbridge.itdoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.kmbridge.itdoc.R;

public class KmClinicDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_detail);
		
		int clinicId = getIntent().getIntExtra("clinicId", -1);
		
		Log.d("kim","KmClinicDetail(20) clinic id is " + clinicId);
		
	}
}
