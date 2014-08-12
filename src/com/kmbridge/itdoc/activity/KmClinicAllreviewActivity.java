package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class KmClinicAllreviewActivity extends Activity {
	ActionBar actionBar = null; // 액션바 세팅 시작

	// 사람들이 쓴 리뷰를 저장할 배열
	TextView reViewUserReview[] = new TextView[5];
	
	// 리뷰쓴 사람의 사진을 저장할 배열
	ImageView reViewUserImage[] = new ImageView[5];

	// 리뷰쓴 사람의 이름을 저장할 배열
	TextView reViewUserName[] = new TextView[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allreview);

		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		int clinicNumber = intent.getExtras().getInt("clinicNumber");

		// json파서 로드
		LoadData load = new LoadData(this);
		// 한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicNumber);
		//리뷰 객체를 가져옴
		List<ReviewView> list = load.getAllReviewView();

		// 병원 이름 지정
		getActionBar().setTitle(KmClinicview.getName());

		setLayout();
		setListener();
		
		// 리뷰쓴 사람의 정보 지정
		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		simpleList = KmClinicview.getUserSimpleInfoList();
		for (int j = 0; j < 5; j++) {
			//String userName = simpleList.get(j).getName();
			String pictureName = simpleList.get(j).getPicturePath();
			String[] arr = pictureName.split(".png");
			String resName = arr[0];
			int pictureId = getResources().getIdentifier(resName, "drawable",this.getPackageName());
			
			String strUserName = simpleList.get(j).getName();
			reViewUserName[j].setText(strUserName);
			//reViewUserImage[j].setImageResource(pictureId);
			//reViewUserName[j].setText(userName);
			//String reViews = list.get(j).getComment();
			//reViewUserReview[j].setText(reViews);
		}

		
	}

	private void setLayout() {
		
		// 추천한 이웃들 사진 지정
		reViewUserImage[0] = (ImageView) findViewById(R.id.review_user1_Image);
		reViewUserImage[1] = (ImageView) findViewById(R.id.review_user2_Image);
		reViewUserImage[2] = (ImageView) findViewById(R.id.review_user3_Image);
		reViewUserImage[3] = (ImageView) findViewById(R.id.review_user4_Image);
		reViewUserImage[4] = (ImageView) findViewById(R.id.review_user5_Image);
		
		// 리뷰 저장
		reViewUserReview[0] = (TextView) findViewById(R.id.review_user1_review);
		reViewUserReview[1] = (TextView) findViewById(R.id.review_user2_review);
		reViewUserReview[2] = (TextView) findViewById(R.id.review_user3_review);
		reViewUserReview[3] = (TextView) findViewById(R.id.review_user4_review);
		reViewUserReview[4] = (TextView) findViewById(R.id.review_user5_review);
		
		reViewUserName[0] = (TextView) findViewById(R.id.review_user1_name);
		reViewUserName[1] = (TextView) findViewById(R.id.review_user2_name);
		reViewUserName[2] = (TextView) findViewById(R.id.review_user3_name);
		reViewUserName[3] = (TextView) findViewById(R.id.review_user4_name);
		reViewUserName[4] = (TextView) findViewById(R.id.review_user5_name);
		

	}

	private void setListener() {

	}

}
