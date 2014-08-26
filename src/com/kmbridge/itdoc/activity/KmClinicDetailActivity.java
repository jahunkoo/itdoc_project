package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.KmDoctor;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.dto.TimeTable;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class KmClinicDetailActivity extends ActionBarActivity implements
		OnClickListener {
	ActionBar actionBar = null; // 액션바 세팅 시작
	int clinicNumber;

	ImageView detailRelatives; // 추천한 이웃들 이미지
	Button detailAllreview; // 리뷰 모두 보기
	Button detailClinicMoreInfo;
	Button detailClinicMoreDoctor;
	//Button detailClinicVisited;
	ImageView detailClinicVisited;
	//Button detailClinicCall;
	ImageView detailClinicCall;

	TextView kmName;
	TextView kmLoacation;
	TextView kmLoacationRemain;
	TextView kmUserName;
	TextView txtKmclinicMap;
	TextView txtReviewKeyword;
	TextView txtKmclinicComment;
	TextView txtUserReview;
	TextView txtDoctorName;
	TextView txtDoctorCommnet;
	TextView txtDoctorAcademy;
	ImageView kmUserImage;
	ImageView kmClinicImage;
	ImageView kmClinicImage2;
	ImageView kmClinicDoctorFace;
	TextView txtUserReviewDate;
	ImageView kmClinicEmotionImg1;
	ImageView kmClinicEmotionImg2;
	ImageView kmClinicEmotionImg3;

	LinearLayout liniearLikeUsers;

	// 진료시간
	TextView txtMoreInfoWeekDay;
	TextView txtMoreInfoWeekend;
	// 의사 사진
	String doctorFace[] = { "doctor1", "doctor2", "doctor3", "doctor4",
			"doctor5", "doctor6", "doctor7", "doctor8", "doctor9", "doctor10" };

	// 키워드
	// 의사 사진
	String reviewKeyword[] = { "피부", "다이어트", "비염", "디스크", "한방", "아이", "보약",
			"위장", "여성", "남성", "침", "당뇨", "스트레스", "탈모" };

	// 추천한 이웃들 저장할 이미지뷰 배열
	ImageView likeUser[] = new ImageView[5];
	GoogleMap HaniMap;

	TextView name;
	Button gotoFollower;
	
	private TextView favoriteGoodText;
	private TextView favoriteSoSoText;
	private TextView favoriteBadText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_km_clinic_detail);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		clinicNumber = intent.getExtras().getInt("clinicNumber");

		setLayout();
		setListener();

		// json파서 로드
		LoadData load = new LoadData(this);

		// 한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicNumber);

		// List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		// simpleList = KmClinicview.getUserSimpleInfoList();

		List<KmDoctor> doctorList = new ArrayList<KmDoctor>();
		doctorList = KmClinicview.getDoctorList();
		/*
		 * for(int i=0; i< doctorList.size(); i++) {
		 * doctorList.get(i).getAcademy(); }
		 */

		// String pictureName = simpleList.get(j).getPicturePath();
		// load.getAllUserSimpleInfo()
		// KmClinicview.setUserSimpleInfoList(userSimpleInfoList);

		// List<ReviewKeyword> listKeyword =
		// load.getAllReviewView().get(2).getReviewKeywordList();

		//추천, 괜찮다, 개선필요 에 대한 텍스트뷰 
		favoriteGoodText = (TextView) findViewById(R.id.TextView02);
		favoriteSoSoText = (TextView) findViewById(R.id.TextView01);
		favoriteBadText = (TextView) findViewById(R.id.textView5);
		
		// 병원 사진 지정
		kmClinicImage = (ImageView) findViewById(R.id.kmclinic_detail_picture);
		kmClinicImage2 = (ImageView) findViewById(R.id.kmclinic_detail_picture2);
		// Log.d("kim2",""+KmClinicview.getPicturePath());

		String ClinicPictureName = KmClinicview.getPicturePath();
		String[] ClinicArr = ClinicPictureName.split(".png");
		String resClinName = ClinicArr[0];
		Log.d("kim4","Path : "+resClinName);
		int clinicPictureId = getResources().getIdentifier(resClinName,"drawable", this.getPackageName());
		// Log.d("kim4","Resource :"+pictureId);

		// 원래 있던 코드
		kmClinicImage.setImageResource(clinicPictureId);

		kmClinicImage2.setImageResource(clinicPictureId);

		// 검색했을 때 result에서 한의원 사진을 들어가면 out of memory error 가 뜨는 형태가 뜨는 것 때문에
		// bitmap factory를 사용해서 사진의 크기를 조절하는 코드
		// Bitmap bitmap =
		// com.kmbridge.itdoc.image.ImageManager.decodeSampledBitmapFromResource(this.getResources(),
		// clinicPictureId, 128, 64);
		// kmClinicImage.setImageBitmap(bitmap);

		// String kmclinicImagePath = list.get(2).getKmClinicPicturePath();
		// Log.d("kim",kmclinicImagePath);
		// kmClinicImage.setImageResource(list.get(2).getKmClinicPicturePath());

		// 병원 이름 지정
		getActionBar().setTitle(KmClinicview.getName());
		kmName = (TextView) findViewById(R.id.kmclinic_detail_kmname);
		kmName.setText(KmClinicview.getName());

		// 병원 주소 지정
		kmLoacation = (TextView) findViewById(R.id.kmclinic_detail_kmlocation);
		kmLoacationRemain = (TextView) findViewById(R.id.kmclinic_detail_kmlocation_remain);
		kmLoacation.setText(KmClinicview.getBigRegionName()
				+ KmClinicview.getMiddleRegionName());
		kmLoacationRemain.setText(KmClinicview.getRemainRegion());

		// 추천한 이웃들 사진 지정
		likeUser[0] = (ImageView) findViewById(R.id.like_user0);
		likeUser[1] = (ImageView) findViewById(R.id.like_user1);
		likeUser[2] = (ImageView) findViewById(R.id.like_user2);
		likeUser[3] = (ImageView) findViewById(R.id.like_user3);
		likeUser[4] = (ImageView) findViewById(R.id.like_user4);

		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		simpleList = load.getRandomUserSimpleInfoList(clinicNumber);

		// 추천한 이웃들 사진 뿌리기
		for (int j = 0; j < simpleList.size(); j++) {
			String pictureName = simpleList.get(j).getPicturePath();
			String[] arr = pictureName.split(".png");
			String resName = arr[0];
			// Log.d("kim4","Path : "+resName);
			int pictureId = getResources().getIdentifier(resName, "drawable",
					this.getPackageName());
			// Log.d("kim4","Resource :"+pictureId);
			likeUser[j].setImageResource(pictureId);
		}
		// 리뷰 갯수 지정

		// 대표유저 사진 지정
		String pictureName = simpleList.get(0).getPicturePath();
		String[] arr = pictureName.split(".png");
		String resName = arr[0];
		int pictureId = getResources().getIdentifier(resName, "drawable",
				this.getPackageName());
		kmUserImage.setImageResource(pictureId);

		// 대표유저 이름 지정
		String userName = simpleList.get(0).getName();
		kmUserName = (TextView) findViewById(R.id.km_detail_user_name);
		kmUserName.setText(userName);

		// 대표유저 리뷰 지정
		List<ReviewView> reviewList = new ArrayList<ReviewView>();
		reviewList = load.getRandomReviewViewList(clinicNumber);
		String userReview = reviewList.get(0).getComment();
		txtUserReview.setText(userReview);

		// 대표유저 리뷰 작성 시간 지정
		String userReviewData = reviewList.get(0).getReviewTime();
		String sub_userReviewData = userReviewData.substring(0, 10);
		txtUserReviewDate.setText(sub_userReviewData); // 리뷰 작성 시간 지정

		// //추천이미지에 따른 번호를 가져옴.
		int userReviewFavorType = reviewList.get(0).getFavoriteType();
		/*
		 * int favorPictureId = setFavorImage(userReviewFavorType);
		 * kmClinicEmotionImg1.setImageResource(favorPictureId); //리뷰 평가 이미지 지정
		 * kmClinicEmotionImg2.setImageResource(favorPictureId); //리뷰 평가 이미지 지정
		 * kmClinicEmotionImg3.setImageResource(favorPictureId); //리뷰 평가 이미지 지정
		 */
		Log.d("kim2", "" + userReviewFavorType);
		setFavorImage(userReviewFavorType);

		// 한의원 설명
		txtKmclinicComment.setText(KmClinicview.getDetails());

		// 진료시간 들어갈 곳
		List<TimeTable> timeList = KmClinicview.getTimeTableList();

		for (int i = 0; i < 2; i++) {
			String infoDay = timeList.get(i).getWeekDay();
			String startTime = timeList.get(i).getStartTime();
			String endTime = timeList.get(i).getEndTime();
			String lunchStartTime = timeList.get(i).getLunchStartTime();
			String lucnhEndtime = timeList.get(i).getLunchEndTime();

			// 평일 진료 시간 지정
			String moreInfoDay = infoDay + " " + startTime + "~" + endTime
					+ "(점심시간 " + lunchStartTime + "~" + lucnhEndtime + ")";
			if (i == 0) {
				txtMoreInfoWeekDay.setText(moreInfoDay);
			} else if (i == 1) {
				txtMoreInfoWeekend.setText(moreInfoDay);
			}
		}

		// 맵 좌표 들어갈 곳
		String kmClinicMap = KmClinicview.getMapPoint();
		String[] Mappoint = kmClinicMap.split(",");
		String latitude = Mappoint[0];
		String longitude = Mappoint[1];
		Double latitudeD = Double.parseDouble(latitude);
		Double longitudeD = Double.parseDouble(longitude);

		// 맵 한글 위치 들어갈 곳
		txtKmclinicMap.setText(KmClinicview.getName() + "\n"
				+ KmClinicview.getBigRegionName()
				+ KmClinicview.getMiddleRegionName()
				+ KmClinicview.getRemainRegion());

		// LatLng loc = new LatLng(Double.parseDouble(latitude),
		// Double.parseDouble(longitude));
		LatLng loc = new LatLng(latitudeD, longitudeD);
		CameraPosition cp = new CameraPosition.Builder().target((loc)).zoom(16)
				.build();
		// MarkerOptions marker = new MarkerOptions().position(loc);

		HaniMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		HaniMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

		MarkerOptions hani_1 = new MarkerOptions();
		hani_1.position(new LatLng(Double.parseDouble(latitude),
		Double.parseDouble(longitude)));
		hani_1.position(new LatLng(latitudeD, longitudeD));
		//hani_1.title(KmClinicview.getName());
		HaniMap.addMarker(hani_1).showInfoWindow();

		// Log.d("kim4",""+doctorList.get(0).getName());
		// 의료진 이름
		for (int i = 0; i < doctorList.size(); i++) {
			// 의료진 이름
			txtDoctorName.setText(doctorList.get(0).getName());

			// 의료진 대표사진
			int doctorNum = (doctorList.get(0).getId()) % 10;
			int doctorFaceId = getResources().getIdentifier(
					doctorFace[doctorNum], "drawable", this.getPackageName());
			kmClinicDoctorFace.setImageResource(doctorFaceId); // 사진지정
			// clinicNumber

			// 대표유저 리뷰 키워드 지정
			txtReviewKeyword.setText(reviewKeyword[doctorNum]);
			if(KmClinicview.getId() == 12){
				txtReviewKeyword.setText(reviewKeyword[13]);
			}
			// Log.d("kim5",doctorList.get(1).getAcademy());
			// 학력정보
			txtDoctorAcademy.setText(doctorList.get(0).getAcademy());
			if (i == 0)
				break;
		}

		// 의료진 이름
		// txtDoctorName.setText(doctorList.get(0).getName());
		// txtDoctorAcademy.setText(doctorList.get(0).getAcademy());

		// 의료진 설명
		txtDoctorCommnet.setText(KmClinicview.getDetails());

		/*
		 * //의료진 대표사진 int doctorNum = (doctorList.get(0).getId())%10; int
		 * doctorFaceId = getResources().getIdentifier(doctorFace[doctorNum],
		 * "drawable", this.getPackageName());
		 * kmClinicDoctorFace.setImageResource(doctorFaceId); //사진지정
		 * //clinicNumber
		 * 
		 * //Log.d("kim5",doctorList.get(1).getAcademy()); //학력정보
		 * txtDoctorAcademy.setText(doctorList.get(0).getAcademy());
		 */

		/*
		 * int clinicId = getIntent().getIntExtra("clinicId", -1);
		 * ClinicDetailThread mThread = new
		 * ClinicDetailThread("getDetailKmClinic", this, clinicId);
		 * mThread.start();
		 */

	}

	private void setFavorImage(int type) {
		int favorId;
		Log.d("kim2", "type:" + type);
		if (type == 1) {
			favorId = getResources().getIdentifier("img_good_on","drawable", this.getPackageName());
			Log.d("kim2", "" + favorId);
			kmClinicEmotionImg1.setImageResource(favorId); // 리뷰 평가 이미지 지정
			favoriteGoodText.setTextColor(getResources().getColor(R.color.text_red));
			favoriteSoSoText.setTextColor(getResources().getColor(R.color.text_black));
			favoriteBadText.setTextColor(getResources().getColor(R.color.text_black));
		} else if (type == 2) {
			favorId = getResources().getIdentifier("img_notbad_on","drawable", this.getPackageName());
			kmClinicEmotionImg2.setImageResource(favorId); // 리뷰 평가 이미지 지정
			favoriteGoodText.setTextColor(getResources().getColor(R.color.text_black));
			favoriteSoSoText.setTextColor(getResources().getColor(R.color.text_red));
			favoriteBadText.setTextColor(getResources().getColor(R.color.text_black));
		} else if (type == 3) {
			favorId = getResources().getIdentifier("img_bad_on","drawable", this.getPackageName());
			kmClinicEmotionImg3.setImageResource(favorId); // 리뷰 평가 이미지 지정
			favoriteGoodText.setTextColor(getResources().getColor(R.color.text_black));
			favoriteSoSoText.setTextColor(getResources().getColor(R.color.text_black));
			favoriteBadText.setTextColor(getResources().getColor(R.color.text_red));
		}

	}

	public void setLayout() {
		// detailRelatives = (ImageView)
		// findViewById(R.id.kmclinic_detail_relatives);
		detailAllreview = (Button) findViewById(R.id.kmclinic_detail_review);
		detailClinicMoreInfo = (Button) findViewById(R.id.kmclinic_detail_moreinfo);
		detailClinicMoreDoctor = (Button) findViewById(R.id.kmclinic_detail_moredoctor);
		detailClinicVisited = (ImageView) findViewById(R.id.btn_activity_km_clilic_detail_visited);
		detailClinicCall = (ImageView) findViewById(R.id.btn_activity_km_clinic_detail_call);
		txtKmclinicMap = (TextView) findViewById(R.id.txt_kmclinic_map);
		txtReviewKeyword = (TextView) findViewById(R.id.txtReviewKeyword);
		txtKmclinicComment = (TextView) findViewById(R.id.txtKmclinicComment);
		liniearLikeUsers = (LinearLayout) findViewById(R.id.like_users);
		txtUserReview = (TextView) findViewById(R.id.txt_user_review);
		txtDoctorName = (TextView) findViewById(R.id.txt_doctor_name);
		txtDoctorCommnet = (TextView) findViewById(R.id.txt_doctor_comment);
		txtDoctorAcademy = (TextView) findViewById(R.id.txt_doctor_academy);
		kmUserImage = (ImageView) findViewById(R.id.km_detail_user_picture);
		kmClinicDoctorFace = (ImageView) findViewById(R.id.kmDetailDoctorImg);
		txtUserReviewDate = (TextView) findViewById(R.id.txtReviewDate);
		kmClinicEmotionImg1 = (ImageView) findViewById(R.id.review_user_favorImg1);
		kmClinicEmotionImg2 = (ImageView) findViewById(R.id.review_user_favorImg2);
		kmClinicEmotionImg3 = (ImageView) findViewById(R.id.review_user_favorImg3);

		txtMoreInfoWeekDay = (TextView) findViewById(R.id.txt_kmclin_time);
		txtMoreInfoWeekend = (TextView) findViewById(R.id.txt_kmclin_time2);

		//Drawable alphaVisited = ((Button) findViewById(R.id.btn_activity_km_clilic_detail_visited)).getBackground();
		// alphaVisited.setAlpha(99);
		//Drawable alphaCall = ((Button) findViewById(R.id.btn_activity_km_clinic_detail_call)).getBackground();
		// alphaCall.setAlpha(99);
	}

	public void setListener() {
		// detailRelatives.setOnClickListener(this);
		detailAllreview.setOnClickListener(this);
		detailClinicMoreInfo.setOnClickListener(this);
		detailClinicMoreDoctor.setOnClickListener(this);
		detailClinicVisited.setOnClickListener(this);
		detailClinicCall.setOnClickListener(this);
		txtKmclinicMap.setOnClickListener(this);
		liniearLikeUsers.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.like_users:
			Intent intentRelatives = new Intent(this, RelativesActivity.class);
			intentRelatives.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intentRelatives.putExtra("clinicNumber", clinicNumber);
			startActivity(intentRelatives);
			break;

		case R.id.kmclinic_detail_review:
			Intent intentReview = new Intent(this,
					KmClinicAllreviewActivity.class);
			intentReview.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intentReview.putExtra("clinicNumber", clinicNumber);
			startActivity(intentReview);
			break;

		case R.id.kmclinic_detail_moreinfo:
			Intent intentMoreinfo = new Intent(this,
					KmClinicMoreInfoActivity.class);
			intentMoreinfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intentMoreinfo.putExtra("clinicNumber", clinicNumber);
			startActivity(intentMoreinfo);
			break;

		case R.id.kmclinic_detail_moredoctor:
			Intent intentMoredoctor = new Intent(this,
					KmClinicMoreDoctorActivity.class);
			intentMoredoctor.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intentMoredoctor.putExtra("clinicNumber", clinicNumber);
			startActivity(intentMoredoctor);
			break;

		case R.id.btn_activity_km_clilic_detail_visited:
			Intent intentVisited = new Intent(this, VisitedActivity.class);
			intentVisited.putExtra("clinicNumber", clinicNumber);
			intentVisited.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentVisited);
			break;

		case R.id.txt_kmclinic_map:
			Intent intentMap = new Intent(this, MapActivity.class);
			intentMap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intentMap.putExtra("clinicNumber", clinicNumber);
			startActivity(intentMap);
			break;

		}
	}
}
