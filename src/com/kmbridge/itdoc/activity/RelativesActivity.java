package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.R.layout;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.dto.UserView;
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

	TextView txtLikedUsers;

	boolean checkItem0 = false;
	boolean checkItem1 = false;
	boolean checkItem2 = false;
	boolean checkItem3 = false;
	boolean checkItem4 = false;


	// 추천한 이웃의 사진을 저장할 배열
	ImageView likeUser[] = new ImageView[5];

	// 추천한 이웃의 이름을 저장할 배열
	TextView likeName[] = new TextView[5];
	
	// 추천한 이웃의 팔로우 숫자를 저장할 배열
	TextView likeFollow[] = new TextView[5];
	
	// 추천한 이웃의 팔로우 버튼을 저장할 배열
	Button likeButton[] = new Button[5];

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

		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		simpleList = load.getRandomUserSimpleInfoList(clinicNumber);

		for(int j=0;j<simpleList.size();j++)
		{
			String UserEmail = simpleList.get(j).getEmail();
			String UserName = simpleList.get(j).getName();
			String pictureName = simpleList.get(j).getPicturePath();
			String []arr = pictureName.split(".png");
			String resName = arr[0];
			int pictureId = getResources().getIdentifier(resName, "drawable", this.getPackageName());
			
			
			// 유저 정보 객체를 가져옴
			UserView userView = load.getUserView(UserEmail);
			int UserFollowNum = userView.getFollowNum(); //팔로워 숫자 저장.
			
			likeName[j].setText(UserName); //이름지정
			likeUser[j].setImageResource(pictureId); //사진지정
			likeFollow[j].setText("팔로워 "+UserFollowNum); //팔로워 숫자 지정
			likeButton[j].setVisibility(View.VISIBLE); //팔로워 버튼 보이기
		}
	}

	private void setLayout() {
		likeButton[0] = (Button) findViewById(R.id.btn_follow0);
		likeButton[1] = (Button) findViewById(R.id.btn_follow1);
		likeButton[2] = (Button) findViewById(R.id.btn_follow2);
		likeButton[3] = (Button) findViewById(R.id.btn_follow3);
		likeButton[4] = (Button) findViewById(R.id.btn_follow4);
		txtLikedUsers = (TextView) findViewById(R.id.txtLikedUsers);

		// 추천한 이웃들 사진 지정
		likeUser[0] = (ImageView) findViewById(R.id.user1);
		likeUser[1] = (ImageView) findViewById(R.id.user2);
		likeUser[2] = (ImageView) findViewById(R.id.user3);
		likeUser[3] = (ImageView) findViewById(R.id.user4);
		likeUser[4] = (ImageView) findViewById(R.id.user5);

		// 추천한 이웃들 이름 지정
		likeName[0] = (TextView) findViewById(R.id.user1_name);
		likeName[1] = (TextView) findViewById(R.id.user2_name);
		likeName[2] = (TextView) findViewById(R.id.user3_name);
		likeName[3] = (TextView) findViewById(R.id.user4_name);
		likeName[4] = (TextView) findViewById(R.id.user5_name);
		
		// 추천한 이웃의 팔로우 숫자 지정
		likeFollow[0] = (TextView) findViewById(R.id.user1_follow);
		likeFollow[1] = (TextView) findViewById(R.id.user2_follow);
		likeFollow[2] = (TextView) findViewById(R.id.user3_follow);
		likeFollow[3] = (TextView) findViewById(R.id.user4_follow);
		likeFollow[4] = (TextView) findViewById(R.id.user5_follow);
		
	}

	private void setListener() {
		likeButton[0].setOnClickListener(this);
		likeButton[1].setOnClickListener(this);
		likeButton[2].setOnClickListener(this);
		likeButton[3].setOnClickListener(this);
		likeButton[4].setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_follow0:

			if (checkItem0 == true) {
				likeButton[0].setBackgroundResource(R.drawable.follow_on);
				checkItem0 = false;
			} else {
				likeButton[0].setBackgroundResource(R.drawable.follow_off);
				checkItem0 = true;
			}
			break;
		case R.id.btn_follow1:

			if (checkItem1 == true) {
				likeButton[1].setBackgroundResource(R.drawable.follow_on);
				checkItem1 = false;
			} else {
				likeButton[1].setBackgroundResource(R.drawable.follow_off);
				checkItem1 = true;
			}
			break;
		case R.id.btn_follow2:

			if (checkItem2 == true) {
				likeButton[2].setBackgroundResource(R.drawable.follow_on);
				checkItem2 = false;
			} else {
				likeButton[2].setBackgroundResource(R.drawable.follow_off);
				checkItem2 = true;
			}
			break;
		case R.id.btn_follow3:

			if (checkItem3 == true) {
				likeButton[3].setBackgroundResource(R.drawable.follow_on);
				checkItem3 = false;
			} else {
				likeButton[3].setBackgroundResource(R.drawable.follow_off);
				checkItem3 = true;
			}
			break;
		case R.id.btn_follow4:

			if (checkItem4 == true) {
				likeButton[4].setBackgroundResource(R.drawable.follow_on);
				checkItem4 = false;
			} else {
				likeButton[4].setBackgroundResource(R.drawable.follow_off);
				checkItem4 = true;
			}
			break;
		}
	}
}
