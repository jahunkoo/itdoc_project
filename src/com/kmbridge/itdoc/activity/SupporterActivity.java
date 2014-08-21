package com.kmbridge.itdoc.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.plus.model.people.Person.Image;
import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.image.ImageManager;

public class SupporterActivity extends ActionBarActivity implements OnClickListener{

	private TextView titleTextView;
	private LinearLayout clinicContainLayout;
	private TextView body1TextView;
	private TextView body2TitleTextView;
	private TextView body2_1TextView;
	private TextView body2_2TextView;
	private TextView body2_3TextView;
	private LinearLayout doctorContainLayout;
	private LayoutInflater inflator; 
	
	RelativeLayout relative_morock;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_supporters_choice);
		inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		titleTextView 		= (TextView) findViewById(R.id.textview_choice_clinic_top_title);
		clinicContainLayout =  (LinearLayout) findViewById(R.id.linearlayout_choice_clinic_contain_clinic);
		body1TextView 		= (TextView) findViewById(R.id.textview_choice_clinic_body_1);
		body2TitleTextView 	= (TextView) findViewById(R.id.textview_choice_clinic_body_2_title);
		body2_1TextView 	= (TextView) findViewById(R.id.textview_choice_clinic_body_2_1);
		body2_2TextView 	= (TextView) findViewById(R.id.textview_choice_clinic_body_2_2);
		body2_3TextView 	= (TextView) findViewById(R.id.textview_choice_clinic_body_2_3);
		doctorContainLayout =  (LinearLayout) findViewById(R.id.linearlayout_choice_clinic_contain_doctor_img);
		
		//1번 메니페스트
		//2번 ActionBarActivity달기
		//3번째 이거 추가
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		String position = getIntent().getExtras().getString("position");
		int index = Integer.parseInt(position);
		switch(index){
		case 0: setClinicLayout(index);
				String[] textArr = getResources().getStringArray(R.array.choose_clinic_contents_0_array);
				setContents(textArr);
				setDoctorLayout(index);
				break;
		}
		
	}
	
	private void setClinicLayout(int index){
		RelativeLayout layout = (RelativeLayout) inflator.inflate(R.layout.hard_search_clinic_list_item, clinicContainLayout, false);
		//relative_morock = (RelativeLayout) findViewById(R.id.relative_layout_morock_info);
		relative_morock = (RelativeLayout) layout.findViewById(R.id.relative_layout_morock_info);
		relative_morock.setOnClickListener(this);
		clinicContainLayout.addView(layout);
	}
	
	private void setDoctorLayout(int index){
		LinearLayout layout = (LinearLayout) inflator.inflate(R.layout.layout_choose_clinic_doctor_itm, clinicContainLayout, false);
		doctorContainLayout.addView(layout);
	}
	
	private void setContents(String[]textArr){
		titleTextView.setText(textArr[0]);		
		body1TextView.setText(textArr[1]);		
		body2TitleTextView.setText(textArr[2]);
		body2_1TextView.setText(textArr[3]);
		body2_2TextView.setText(textArr[4]);
		body2_3TextView.setText(textArr[5]); 	
		
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId())
		{
		case R.id.relative_layout_morock_info:
			Intent intent = new Intent(this, KmClinicDetailActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("clinicNumber", 12);
			startActivity(intent);
			break;
		}

	}
	
}
