package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.KmDoctor;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class KmClinicMoreDoctorActivity extends Activity {
	ActionBar actionBar = null; // 액션바 세팅 시작

	//레이아웃 
	LinearLayout layoutDoctor[] = new LinearLayout[5];
	
	TextView txtMoreDocterName[] = new TextView[5]; // 의료진 이름
	TextView txtDoctorAcademy[] = new TextView[5];
	TextView txtDoctorMajor[] = new TextView[5];
	TextView txtDoctorCareer[] = new TextView[5];
	
	ImageView moreDoctorFace[] = new ImageView[5]; //의료진 사진
	
	//의사 사진
	String doctorFace[] = {"doctor1","doctor2","doctor3","doctor4","doctor5","doctor6","doctor7","doctor8","doctor9","doctor10"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_more_doctor);
		
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		int clinicNumber = intent.getExtras().getInt("clinicNumber");

		
		//레이아웃 설정
		setLayout();
		
		// json파서 로드
		LoadData load = new LoadData(this);
		// 한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicNumber);
		// 병원 이름 지정
		getActionBar().setTitle(KmClinicview.getName());
		
		//한의원 한의사 리스트를 가져옴
		List<KmDoctor> doctorList = new ArrayList<KmDoctor>();
		doctorList = KmClinicview.getDoctorList();
		for(int i=0; i< Math.min(doctorList.size(),layoutDoctor.length); i++)
		{
			layoutDoctor[i].setVisibility(View.VISIBLE);
			
			//한의사 이름
			String doctorName = doctorList.get(i).getName();
			txtMoreDocterName[i].setText(doctorName);
			
			//한의사 학력정보
			String doctorAcademy = doctorList.get(i).getAcademy();
			txtDoctorAcademy[i].setText(doctorAcademy);
			
			//한의사 경력정보
			String doctorCarrer = doctorList.get(i).getCareer();
			txtDoctorMajor[i].setText(doctorCarrer);
			
			//한의사 기타정보
			String doctorMajor = doctorList.get(i).getMajor();
			txtDoctorCareer[i].setText(doctorMajor);
			
			//한의사 시진
			//의료진 대표사진
			int doctorNum = (doctorList.get(i).getId())%10;
			Log.d("kim4",""+doctorNum);
			int doctorFaceId = getResources().getIdentifier(doctorFace[doctorNum], "drawable", this.getPackageName());
			moreDoctorFace[i].setImageResource(doctorFaceId); //사진지정
		}
		
		
	}

	private void setLayout() {
		txtMoreDocterName[0] = (TextView) findViewById(R.id.txt_moredoctor1_name);
		txtMoreDocterName[1] = (TextView) findViewById(R.id.txt_moredoctor2_name);
		txtMoreDocterName[2] = (TextView) findViewById(R.id.txt_moredoctor3_name);
		txtMoreDocterName[3] = (TextView) findViewById(R.id.txt_moredoctor4_name);
		txtMoreDocterName[4] = (TextView) findViewById(R.id.txt_moredoctor5_name);
		
		txtDoctorAcademy[0] = (TextView) findViewById(R.id.txt_moredoctor1_academy);
		txtDoctorAcademy[1] = (TextView) findViewById(R.id.txt_moredoctor2_academy);
		txtDoctorAcademy[2] = (TextView) findViewById(R.id.txt_moredoctor3_academy);
		txtDoctorAcademy[3] = (TextView) findViewById(R.id.txt_moredoctor4_academy);
		txtDoctorAcademy[4] = (TextView) findViewById(R.id.txt_moredoctor5_academy);
		
		txtDoctorCareer[0] = (TextView) findViewById(R.id.txt_moredoctor1_carrer);
		txtDoctorCareer[1] = (TextView) findViewById(R.id.txt_moredoctor2_carrer);
		txtDoctorCareer[2] = (TextView) findViewById(R.id.txt_moredoctor3_carrer);
		txtDoctorCareer[3] = (TextView) findViewById(R.id.txt_moredoctor4_carrer);
		txtDoctorCareer[4] = (TextView) findViewById(R.id.txt_moredoctor5_carrer);
		
		txtDoctorMajor[0] = (TextView) findViewById(R.id.txt_moredoctor1_major);
		txtDoctorMajor[1] = (TextView) findViewById(R.id.txt_moredoctor2_major);
		txtDoctorMajor[2] = (TextView) findViewById(R.id.txt_moredoctor3_major);
		txtDoctorMajor[3] = (TextView) findViewById(R.id.txt_moredoctor4_major);
		txtDoctorMajor[4] = (TextView) findViewById(R.id.txt_moredoctor5_major);
		
		moreDoctorFace[0] = (ImageView) findViewById(R.id.moreDoctorFace1);
		moreDoctorFace[1] = (ImageView) findViewById(R.id.moreDoctorFace2);
		moreDoctorFace[2] = (ImageView) findViewById(R.id.moreDoctorFace3);
		moreDoctorFace[3] = (ImageView) findViewById(R.id.moreDoctorFace4);
		moreDoctorFace[4] = (ImageView) findViewById(R.id.moreDoctorFace5);
		
		layoutDoctor[0] = (LinearLayout) findViewById(R.id.layout_doctor1);
		layoutDoctor[1] = (LinearLayout) findViewById(R.id.layout_doctor2);
		layoutDoctor[2] = (LinearLayout) findViewById(R.id.layout_doctor3);
		layoutDoctor[3] = (LinearLayout) findViewById(R.id.layout_doctor4);
		layoutDoctor[4] = (LinearLayout) findViewById(R.id.layout_doctor5);
	}
}
