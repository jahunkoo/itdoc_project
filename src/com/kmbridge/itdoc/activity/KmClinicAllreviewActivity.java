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
import com.kmbridge.itdoc.dto.ReviewKeyword;
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
	
	// 리뷰쓴 사람의 키워드를 저장할 배열
	TextView reViewUserKeyword[] = new TextView[5];
	
	// 리뷰 이미지 저장할 배열
	ImageView reViewTypeImage1[] = new ImageView[5];
	ImageView reViewTypeImage2[] = new ImageView[5];
	ImageView reViewTypeImage3[] = new ImageView[5];
	
	// 레이아웃 지정
	LinearLayout layoutUserReview1[] = new LinearLayout[5];
	// 레이아웃 지정
	LinearLayout layoutUserReview2[] = new LinearLayout[5];
	
	//의사 사진
	String reviewKeyword[] = {"피부","비염","다이어트","디스크","한방","아이","보약","위장","여성","남성","침","당뇨","스트레스","탈모"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allreview);

		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		int clinicNumber = intent.getExtras().getInt("clinicNumber");

//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
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
		
		//의사 객체
		List<KmDoctor> doctorList = new ArrayList<KmDoctor>();
		doctorList = KmClinicview.getDoctorList();
		
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
			//int favorPictureId = setFavorImage(userReviewFavorType);
			
			layoutUserReview1[j].setVisibility(View.VISIBLE);
			layoutUserReview2[j].setVisibility(View.VISIBLE);
			
			reViewUserName[j].setText(UserName); //이름지정
			reViewUserImage[j].setImageResource(pictureId); //사진지정
			reViewUserDate[j].setText(userReviewData); //리뷰 작성 시간 지정
			reViewUserReview[j].setText(userReview); //리뷰지정 
			//reViewTypeImage[j].setImageResource(favorPictureId); //리뷰 평가 이미지 지정
			
			int favorId1 = getResources().getIdentifier("emoticon_good_grey", "drawable", this.getPackageName());
			reViewTypeImage1[j].setImageResource(favorId1);
			int favorId2 = getResources().getIdentifier("emoticon_soso_grey", "drawable", this.getPackageName());
			reViewTypeImage2[j].setImageResource(favorId2);
			int favorId3 = getResources().getIdentifier("emoticon_bad_grey", "drawable", this.getPackageName());
			reViewTypeImage3[j].setImageResource(favorId3);
			
			setFavorImage(userReviewFavorType, j); //리뷰 평가 이미지 지정
			
		}
		for(int i=0; i< Math.min(reViewUserKeyword.length,doctorList.size()); i++)
		{
			//키워드 대표사진
			int keywordNum = (doctorList.get(i).getId())%10;
			Log.d("kim4","test"+keywordNum);
			reViewUserKeyword[i].setText(reviewKeyword[keywordNum]);
		}

		
	}

	private void setFavorImage(int type, int number) {
		int favorId;
		Log.d("kim2","type:"+type);
		if(type==1)
		{
			favorId = getResources().getIdentifier("emoticon_good_red", "drawable", this.getPackageName());
			Log.d("kim2",""+favorId);
			reViewTypeImage1[number].setImageResource(favorId);//리뷰 평가 이미지 지정
		}
		else if(type==2)
		{
			favorId = getResources().getIdentifier("emoticon_soso_red", "drawable", this.getPackageName());
			reViewTypeImage2[number].setImageResource(favorId); //리뷰 평가 이미지 지정
		}
		else if(type==3)
		{
			favorId = getResources().getIdentifier("emoticon_bad_red", "drawable", this.getPackageName());
			reViewTypeImage3[number].setImageResource(favorId);//리뷰 평가 이미지 지정
		}
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
		reViewTypeImage1[0] = (ImageView) findViewById(R.id.review_user1_favorImg1);
		reViewTypeImage1[1] = (ImageView) findViewById(R.id.review_user2_favorImg1);
		reViewTypeImage1[2] = (ImageView) findViewById(R.id.review_user3_favorImg1);
		reViewTypeImage1[3] = (ImageView) findViewById(R.id.review_user4_favorImg1);
		reViewTypeImage1[4] = (ImageView) findViewById(R.id.review_user5_favorImg1);
		
		reViewTypeImage2[0] = (ImageView) findViewById(R.id.review_user1_favorImg2);
		reViewTypeImage2[1] = (ImageView) findViewById(R.id.review_user2_favorImg2);
		reViewTypeImage2[2] = (ImageView) findViewById(R.id.review_user3_favorImg2);
		reViewTypeImage2[3] = (ImageView) findViewById(R.id.review_user4_favorImg2);
		reViewTypeImage2[4] = (ImageView) findViewById(R.id.review_user5_favorImg2);
		
		reViewTypeImage3[0] = (ImageView) findViewById(R.id.review_user1_favorImg3);
		reViewTypeImage3[1] = (ImageView) findViewById(R.id.review_user2_favorImg3);
		reViewTypeImage3[2] = (ImageView) findViewById(R.id.review_user3_favorImg3);
		reViewTypeImage3[3] = (ImageView) findViewById(R.id.review_user4_favorImg3);
		reViewTypeImage3[4] = (ImageView) findViewById(R.id.review_user5_favorImg3);
		
		reViewUserKeyword[0] = (TextView) findViewById(R.id.review_user1_keyword);
		reViewUserKeyword[1] = (TextView) findViewById(R.id.review_user2_keyword);
		reViewUserKeyword[2] = (TextView) findViewById(R.id.review_user3_keyword);
		reViewUserKeyword[3] = (TextView) findViewById(R.id.review_user4_keyword);
		reViewUserKeyword[4] = (TextView) findViewById(R.id.review_user5_keyword);
		
		layoutUserReview1[0] = (LinearLayout) findViewById(R.id.layout_user1_review1);
		layoutUserReview1[1] = (LinearLayout) findViewById(R.id.layout_user2_review1);
		layoutUserReview1[2] = (LinearLayout) findViewById(R.id.layout_user3_review1);
		layoutUserReview1[3] = (LinearLayout) findViewById(R.id.layout_user4_review1);
		layoutUserReview1[4] = (LinearLayout) findViewById(R.id.layout_user5_review1);
		
		layoutUserReview2[0] = (LinearLayout) findViewById(R.id.layout_user1_review2);
		layoutUserReview2[1] = (LinearLayout) findViewById(R.id.layout_user2_review2);
		layoutUserReview2[2] = (LinearLayout) findViewById(R.id.layout_user3_review2);
		layoutUserReview2[3] = (LinearLayout) findViewById(R.id.layout_user4_review2);
		layoutUserReview2[4] = (LinearLayout) findViewById(R.id.layout_user5_review2);

	}

	private void setListener() {

	}

}
