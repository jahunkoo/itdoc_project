package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.TimeTable;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class KmClinicMoreInfoActivity extends Activity {
	TextView txtMoreInfoDetail;
	TextView txtMoreInfoWeekDay;
	TextView txtMoreInfoWeekend;
	ImageView kmClinicPicture;

	// 추천한 이웃의 사진을 저장할 배열
	ImageView likeUser[] = new ImageView[5];
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_km_clinic_more_info);
		
		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		int clinicNumber = intent.getExtras().getInt("clinicNumber");

		setLayout();
		setListener();
		
		// json파서 로드
		LoadData load = new LoadData(this);
		// 한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicNumber);

		// 병원 이름 지정
		getActionBar().setTitle(KmClinicview.getName());
		
		// 병원 사진지정
		String ClinicPictureName = KmClinicview.getPicturePath();
		String []ClinicArr = ClinicPictureName.split(".png");
		String resClinName = ClinicArr[0];
		//Log.d("kim4","Path : "+resName);
		int clinicPictureId = getResources().getIdentifier(resClinName, "drawable", this.getPackageName());
		//Log.d("kim4","Resource :"+pictureId);
		kmClinicPicture.setImageResource(clinicPictureId);
		
		
		// 병원 설명 지정
		String kmClinicComment = KmClinicview.getDetails();
		txtMoreInfoDetail.setText(kmClinicComment);

		List<TimeTable> timeList = KmClinicview.getTimeTableList();
		
		for(int i=0; i<2 ; i++)
		{
			String infoDay = timeList.get(i).getWeekDay();
			String startTime = timeList.get(i).getStartTime();
			String endTime =timeList.get(i).getEndTime();
			String lunchStartTime = timeList.get(i).getLunchStartTime();
			String lucnhEndtime = timeList.get(i).getLunchEndTime();
			
			//평일 진료 시간 지정
			String moreInfoDay = infoDay + " " + startTime + "~" + endTime + "(점심시간 " + lunchStartTime + "~" + lucnhEndtime + ")";
			if(i==0)
			{
				txtMoreInfoWeekDay.setText(moreInfoDay);
			}
			else if(i==1)
			{
				txtMoreInfoWeekend.setText(moreInfoDay);
			}
		}
		
		//키워드 별 추천 사용자 사진지정
		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		simpleList = load.getRandomUserSimpleInfoList(clinicNumber);

		for(int j=0;j<simpleList.size();j++)
		{
			String pictureName = simpleList.get(j).getPicturePath();
			String []arr = pictureName.split(".png");
			String resName = arr[0];
			int pictureId = getResources().getIdentifier(resName, "drawable", this.getPackageName());
			
			likeUser[j].setImageResource(pictureId); //사진지정
		}
	}
		

	private void setLayout() {
		txtMoreInfoDetail = (TextView) findViewById(R.id.txt_moreinfo_clinicment);
		txtMoreInfoWeekDay = (TextView) findViewById(R.id.txt_moreinfo_week_day);
		txtMoreInfoWeekend = (TextView) findViewById(R.id.txt_moreinfo_weeked);
		kmClinicPicture = (ImageView) findViewById(R.id.kmclinic_detail_picture);
		
		// 추천한 이웃의 사진을 저장할 배열
		likeUser[0] = (ImageView) findViewById(R.id.more_info_user1);
		likeUser[1] = (ImageView) findViewById(R.id.more_info_user2);
		likeUser[2] = (ImageView) findViewById(R.id.more_info_user3);
		likeUser[3] = (ImageView) findViewById(R.id.more_info_user4);
		likeUser[4] = (ImageView) findViewById(R.id.more_info_user5);
	}
	
	private void setListener() {
		// TODO Auto-generated method stub
		
	}

	
}
