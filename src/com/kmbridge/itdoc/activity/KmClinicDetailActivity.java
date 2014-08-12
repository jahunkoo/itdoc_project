package com.kmbridge.itdoc.activity;

import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.ReviewKeyword;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.hardcoding.LoadData;
import com.kmbridge.itdoc.thread.ClinicDetailThread;
public class KmClinicDetailActivity extends FragmentActivity implements	OnClickListener {
	
	ImageView detailRelatives; // 추천한 이웃들 이미지
	Button detailAllreview; // 리뷰 모두 보기
	Button detailClinicMoreInfo;
	Button detailClinicMoreDoctor;
	Button detailClinicVisited;
	Button detailClinicCall;
	
	TextView kmName;
	TextView kmLoacation;
	TextView kmLoacationRemain;
	TextView kmUserName;
	TextView txtKmclinicMap;
	ImageView kmUserImage;
	ImageView kmClinicImage;

	GoogleMap HaniMap;

	TextView name;
	Button gotoFollower;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_detail);

		setLayout();
		setListener();
		
		//json파서 로드
		LoadData load = new LoadData(this);
		
		//한의원 객체를 가져옴
		KmClinicDetailView view = load.getKmClinicDetailView(2);
		
		//리뷰 객체를 가져옴
		
		List<ReviewView> list = load.getAllReviewView();
//		List<ReviewKeyword> listKeyword = load.getAllReviewView().get(2).getReviewKeywordList();
		
		
		//병원 사진 지정
		kmClinicImage = (ImageView) findViewById(R.id.kmclinic_detail_picture);
		//String kmclinicImagePath = list.get(2).getKmClinicPicturePath();
		//Log.d("kim",kmclinicImagePath);
		//kmClinicImage.setImageResource(list.get(2).getKmClinicPicturePath());
		
		
		//병원 이름 지정
		kmName = (TextView) findViewById(R.id.kmclinic_detail_kmname);
		kmName.setText(view.getName());
		
		//병원 주소 지정
		kmLoacation = (TextView) findViewById(R.id.kmclinic_detail_kmlocation);
		kmLoacationRemain = (TextView) findViewById(R.id.kmclinic_detail_kmlocation_remain);
		kmLoacation.setText(view.getBigRegionName()+view.getMiddleRegionName());
		kmLoacationRemain.setText(view.getRemainRegion());
		
		//사용자 사진 지정
		
		//사용자 이름 지정
		kmUserName = (TextView) findViewById(R.id.km_detail_user_name);
		kmUserName.setText(list.get(0).getUserName());
		
		// LatLng loc = new LatLng(Double.parseDouble(latitude),
		// Double.parseDouble(longitude));
		LatLng loc = new LatLng(37.49927, 127.03748);
		CameraPosition cp = new CameraPosition.Builder().target((loc)).zoom(16)
				.build();
		// MarkerOptions marker = new MarkerOptions().position(loc);

		/*HaniMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		HaniMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

		MarkerOptions hani_1 = new MarkerOptions();
		// hani_1.position(new LatLng(Double.parseDouble(latitude),
		// Double.parseDouble(longitude)));
		hani_1.position(new LatLng(37.49927, 127.03748));
		hani_1.title("OO한의원");
		HaniMap.addMarker(hani_1).showInfoWindow();*/

		int clinicId = getIntent().getIntExtra("clinicId", -1);
		ClinicDetailThread mThread = new ClinicDetailThread(
				"getDetailKmClinic", this, clinicId);
		mThread.start();

	}

	public void setLayout() {
		detailRelatives = (ImageView) findViewById(R.id.kmclinic_detail_relatives);
		detailAllreview = (Button) findViewById(R.id.kmclinic_detail_review);
		detailClinicMoreInfo = (Button) findViewById(R.id.kmclinic_detail_moreinfo);
		detailClinicMoreDoctor = (Button) findViewById(R.id.kmclinic_detail_moredoctor);
		detailClinicVisited = (Button) findViewById(R.id.btn_activity_km_clilic_detail_visited);
		detailClinicCall = (Button) findViewById(R.id.btn_activity_km_clinic_detail_call);
		txtKmclinicMap = (TextView) findViewById(R.id.txt_kmclinic_map);
		
		Drawable alphaVisited = ((Button)findViewById(R.id.btn_activity_km_clilic_detail_visited)).getBackground();
		alphaVisited.setAlpha(99);
		Drawable alphaCall = ((Button)findViewById(R.id.btn_activity_km_clinic_detail_call)).getBackground();
		alphaCall.setAlpha(99);
	}

	public void setListener() {
		detailRelatives.setOnClickListener(this);
		detailAllreview.setOnClickListener(this);
		detailClinicMoreInfo.setOnClickListener(this);
		detailClinicMoreDoctor.setOnClickListener(this);
		detailClinicVisited.setOnClickListener(this);
		detailClinicCall.setOnClickListener(this);
		txtKmclinicMap.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.kmclinic_detail_relatives:
			Intent intentRelatives = new Intent(this, RelativesActivity.class);
   			intentRelatives.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentRelatives);
			break;

		case R.id.kmclinic_detail_review:
			Intent intentReview = new Intent(this, KmClinicAllreviewActivity.class);
			intentReview.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentReview);
			break;
			
		case R.id.kmclinic_detail_moreinfo:
			Intent intentMoreinfo = new Intent(this, KmClinicMoreInfoActivity.class);
			intentMoreinfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentMoreinfo);
			break;
			
		case R.id.kmclinic_detail_moredoctor:
			Intent intentMoredoctor = new Intent(this, KmClinicMoreDoctorActivity.class);
			intentMoredoctor.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentMoredoctor);
			break;
		
		case R.id.btn_activity_km_clilic_detail_visited:
			Intent intentVisited = new Intent(this, VisitedActivity.class);
			intentVisited.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentVisited);
			break;
			
		case R.id.txt_kmclinic_map:
			Intent intentMap = new Intent(this, MapActivity.class);
			intentMap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentMap);
			break;
			
		}
	}
}
