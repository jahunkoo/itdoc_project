package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.List;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.R.layout;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.KmDoctor;
import com.kmbridge.itdoc.hardcoding.LoadData;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class KmClinicMoreDoctorActivity extends Activity {
	ActionBar actionBar = null; // 액션바 세팅 시작

	TextView txtMoreDocterName; // 의료진 이름
	TextView txtMoreDocterComment; // 의료진 설명
	private String doctorName;
	private String doctorAcademy;
	private String doctorMajor;
	private String doctorCareer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_more_doctor);

		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		int clinicNumber = intent.getExtras().getInt("clinicNumber");

		// json파서 로드
		LoadData load = new LoadData(this);
		// 한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicNumber);
		
		//한의원 한의사 리스트를 가져옴
		List<KmDoctor> doctorList = new ArrayList<KmDoctor>();
		doctorList = KmClinicview.getDoctorList();
		for(int i=0; i< doctorList.size(); i++)
		{
			doctorList.get(i).getAcademy();
		}
		// 병원 이름 지정
		getActionBar().setTitle(KmClinicview.getName());
		
		//레이아웃 설정
		setLayout();
		
		//의료진 이름 설정
		txtMoreDocterName.setText(KmClinicview.getName());
		//의료진 설명 설정
		txtMoreDocterComment.setText(KmClinicview.getDetails());
		
	}

	private void setLayout() {
		txtMoreDocterName = (TextView) findViewById(R.id.txt_moredoctor_name);
		txtMoreDocterComment = (TextView) findViewById(R.id.txt_moredoctor_comment);
	}
}
