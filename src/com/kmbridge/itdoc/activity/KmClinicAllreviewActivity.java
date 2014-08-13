package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class KmClinicAllreviewActivity extends Activity {
	ActionBar actionBar = null; // 액션바 세팅 시작

	// 리뷰쓴 사람의 사진을 저장할 배열
	ImageView reViewUserImage[] = new ImageView[5];

	// 리뷰쓴 사람의 이름을 저장할 배열
	TextView reViewUserName[] = new TextView[5];
	
	// 사람들이 쓴 리뷰를 저장할 배열
	TextView reViewUserReview[] = new TextView[5];

	// 리뷰쓴 사람의 작성 시간을 저장할 배열
	TextView reViewUserDate[] = new TextView[5];
	
	// 리뷰 이미지 저장할 배열
	ImageView reViewTypeImage[] = new ImageView[5];

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
		
		//유저 객체 
		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		simpleList = load.getRandomUserSimpleInfoList(clinicNumber);
		
		//리뷰 객체
		List<ReviewView> reviewList = new ArrayList<ReviewView>();
		reviewList = load.getRandomReviewViewList(clinicNumber);
		
		for(int j=0;j<simpleList.size();j++)
		{
			String UserName = simpleList.get(j).getName(); //유저이름
			String pictureName = simpleList.get(j).getPicturePath(); //유저 사진
			String []arr = pictureName.split(".png");
			String resName = arr[0];
			int pictureId = getResources().getIdentifier(resName, "drawable", this.getPackageName());
			
			String userReview = reviewList.get(j).getComment();
			String userReviewData = reviewList.get(j).getReviewTime();
			
			int userReviewFavorType = reviewList.get(j).getFavoriteType(); //추천이미지에 따른 번호를 가져옴.
			int favorPictureId = setFavorImage(userReviewFavorType);
			
			reViewUserName[j].setText(UserName); //이름지정
			reViewUserImage[j].setImageResource(pictureId); //사진지정
			reViewUserDate[j].setText(userReviewData); //리뷰 작성 시간 지정
			reViewUserReview[j].setText(userReview); //리뷰지정 
			reViewTypeImage[j].setImageResource(favorPictureId); //리뷰 평가 이미지 지정
			
		}

		
	}

	private int setFavorImage(int type) {
		int favorId=0;
		
		if(type==1)
		{
			favorId = getResources().getIdentifier("face_nice_on", "drawable", this.getPackageName());
		}
		else if(type==2)
		{
			favorId = getResources().getIdentifier("face_notbad_on", "drawable", this.getPackageName());
		}
		else if(type==3)
		{
			favorId = getResources().getIdentifier("face_bad_on", "drawable", this.getPackageName());
		}
		
		return favorId;
	}

	private void setLayout() {
		
		// 리뷰를 작성한 유저 사진 지정
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
		
		//리뷰를 작성한 유저 이름 지정
		reViewUserName[0] = (TextView) findViewById(R.id.review_user1_name);
		reViewUserName[1] = (TextView) findViewById(R.id.review_user2_name);
		reViewUserName[2] = (TextView) findViewById(R.id.review_user3_name);
		reViewUserName[3] = (TextView) findViewById(R.id.review_user4_name);
		reViewUserName[4] = (TextView) findViewById(R.id.review_user5_name);
		
		//리뷰를 작성한 시간 지정
		reViewUserDate[0] = (TextView) findViewById(R.id.review_user1_date);
		reViewUserDate[1] = (TextView) findViewById(R.id.review_user2_date);
		reViewUserDate[2] = (TextView) findViewById(R.id.review_user3_date);
		reViewUserDate[3] = (TextView) findViewById(R.id.review_user4_date);
		reViewUserDate[4] = (TextView) findViewById(R.id.review_user5_date);
		
		//리뷰 타입 이미지 지정
		reViewTypeImage[0] = (ImageView) findViewById(R.id.review_user1_favorImg);
		reViewTypeImage[1] = (ImageView) findViewById(R.id.review_user2_favorImg);
		reViewTypeImage[2] = (ImageView) findViewById(R.id.review_user3_favorImg);
		reViewTypeImage[3] = (ImageView) findViewById(R.id.review_user4_favorImg);
		reViewTypeImage[4] = (ImageView) findViewById(R.id.review_user5_favorImg);

	}

	private void setListener() {

	}

}
