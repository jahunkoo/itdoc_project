package com.kmbridge.itdoc.activity;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.BigRegion;
import com.kmbridge.itdoc.dto.Grade;
import com.kmbridge.itdoc.dto.MiddleRegion;
import com.kmbridge.itdoc.dto.Time;
import com.kmbridge.itdoc.dto.Week;
import com.kmbridge.itdoc.fragment.IntroFragment;
import com.kmbridge.itdoc.fragment.PlanetFragment;
import com.kmbridge.itdoc.util.ItDocConstants;

public class IntroActivity extends FragmentActivity {

	// 지역정보 및 데이터를 저장할 리스트 생성
	public static ArrayList<BigRegion> bigReionList;
	public static ArrayList<MiddleRegion> middleReionList;
	public static ArrayList<Grade> gradeList;
	public static ArrayList<Week> weekList;
	public static ArrayList<Time> timeList;

	// 핸들러 선언 안드로이드 UI 접근은 쓰레드->핸들로->UI를 통해서만 가능함
	public Handler bigRegionHandler = new Handler();
	public Handler middleRegionHandler = new Handler();
	public Handler gradeHandler = new Handler();
	public Handler timeHandler = new Handler();
	public Handler weekHandler = new Handler();

	// 성공할 경우와 실패할 경우를 고려해야함 성공하면 1 실패하면 2 (다시 보내야 함)
	public Thread RegionThread = new Thread(new Runnable() {

		@Override
		public void run() {
			bigRegionHandler.post(bigRegionRunnable);
			middleRegionHandler.post(middleRegionRunnable);
			gradeHandler.post(gradeRunnable);
			timeHandler.post(timeRunnable);
			weekHandler.post(weekRunnable);
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);

		setLayoutElement();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000); // 3�ʰ� �ΰ? �����ش�. ��Ʈ�� �����̺κ�
					RegionThread.start();
					isIntro();
				}

				catch (Exception e) {
				}

			}

		}).start();
	}

	private void setLayoutElement() {
		// TODO Auto-generated method stub
		Fragment intro_fragment = new IntroFragment();
		//Bundle args = new Bundle();
		//args.putInt(PlanetFragment.ARG_PLANET_NUMBER, 0);
		//intro_fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.relativelayout_intro, intro_fragment).commit();
	}
	
	Runnable bigRegionRunnable = new Runnable() {
		public void run() {
			bigReionList = new ConnectionBridge()
			.getBigRegionList(
					ItDocConstants.METHOD_URL_GET_BIG_REGION_LIST,
					IntroActivity.this);
	Log.d("Big","Success");
		}
	};
	
	Runnable middleRegionRunnable = new Runnable() {
		public void run() {
			middleReionList = new ConnectionBridge()
					.getMiddleRegionList(
							ItDocConstants.METHOD_URL_GET_MIDDLE_REGION_LIST,
							IntroActivity.this);
		}
	};
	

	Runnable gradeRunnable = new Runnable() {
		public void run() {
			gradeList = new ConnectionBridge()
					.getGradeList(
							ItDocConstants.METHOD_URL_GET_GRADE_LIST,
							IntroActivity.this);
		}
	};
	
	Runnable weekRunnable = new Runnable() {
		public void run() {
			weekList = new ConnectionBridge()
					.getWeekList(
							ItDocConstants.METHOD_URL_GET_WEEK_LIST,
							IntroActivity.this);
		}
	};
	
	Runnable timeRunnable = new Runnable() {
		public void run() {
			timeList = new ConnectionBridge()
					.getTimeList(
							ItDocConstants.METHOD_URL_GET_TIME_LIST,
							IntroActivity.this);
		}
	};

	private void isIntro() {
		//RegionThread.start();
		Intent intent = new Intent(this, TestUserActivity.class);
		startActivity(intent);
		finish();
	}

}
