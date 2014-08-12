package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.R.layout;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.hardcoding.LoadData;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RelativesActivity extends FragmentActivity implements	OnClickListener {
	ActionBar actionBar = null; // 액션바 세팅 시작

	Button btnFollow0;
	Button btnFollow1;
	Button btnFollow2;
	Button btnFollow3;
	Button btnFollow4;
	Button btnFollow5;
	Button btnFollow6;

	TextView txtLikedUsers;

	boolean checkItem0 = false;
	boolean checkItem1 = false;
	boolean checkItem2 = false;
	boolean checkItem3 = false;
	boolean checkItem4 = false;
	boolean checkItem5 = false;
	boolean checkItem6 = false;

	// 추천한 이웃의 사진을 저장할 배열
	ImageView likeUser[] = new ImageView[7];

	// 추천한 이웃의 이름을 저장할 배열
	TextView likeName[] = new TextView[7];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relatives);

		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		int clinicNumber = intent.getExtras().getInt("clinicNumber");

		// json파서 로드
		LoadData load = new LoadData(this);
		// 한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicNumber);

		// 병원 이름 지정
		getActionBar().setTitle(KmClinicview.getName());

		setLayout();
		setListener();

		// 제목 지정
		txtLikedUsers.setText(KmClinicview.getName() + "을 추천한 사용자");

		// 추천한 사용자 이미지, 이름 지정
		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		simpleList = KmClinicview.getUserSimpleInfoList();
		for (int j = 0; j < 7; j++) {
			String userName = simpleList.get(j).getName();
			String pictureName = simpleList.get(j).getPicturePath();
			String[] arr = pictureName.split(".png");
			String resName = arr[0];
			int pictureId = getResources().getIdentifier(resName, "drawable",this.getPackageName());
			likeUser[j].setImageResource(pictureId);
			likeName[j].setText(userName);
		}
	}

	private void setLayout() {
		btnFollow0 = (Button) findViewById(R.id.btn_follow0);
		btnFollow1 = (Button) findViewById(R.id.btn_follow1);
		btnFollow2 = (Button) findViewById(R.id.btn_follow2);
		btnFollow3 = (Button) findViewById(R.id.btn_follow3);
		btnFollow4 = (Button) findViewById(R.id.btn_follow4);
		btnFollow5 = (Button) findViewById(R.id.btn_follow5);
		btnFollow6 = (Button) findViewById(R.id.btn_follow6);
		txtLikedUsers = (TextView) findViewById(R.id.txtLikedUsers);

		// 추천한 이웃들 사진 지정
		likeUser[0] = (ImageView) findViewById(R.id.user1);
		likeUser[1] = (ImageView) findViewById(R.id.user2);
		likeUser[2] = (ImageView) findViewById(R.id.user3);
		likeUser[3] = (ImageView) findViewById(R.id.user4);
		likeUser[4] = (ImageView) findViewById(R.id.user5);
		likeUser[5] = (ImageView) findViewById(R.id.user6);
		likeUser[6] = (ImageView) findViewById(R.id.user7);

		// 추천한 이웃들 이름 지정
		likeName[0] = (TextView) findViewById(R.id.user1_name);
		likeName[1] = (TextView) findViewById(R.id.user2_name);
		likeName[2] = (TextView) findViewById(R.id.user3_name);
		likeName[3] = (TextView) findViewById(R.id.user4_name);
		likeName[4] = (TextView) findViewById(R.id.user5_name);
		likeName[5] = (TextView) findViewById(R.id.user6_name);
		likeName[6] = (TextView) findViewById(R.id.user7_name);
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
