package com.kmbridge.itdoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.fragment.LoginFragment;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class ConfigActivity extends FragmentActivity implements OnClickListener {
	private Button btn_activity_config_push;
	private Button btn_activity_config_alarm;
	private Button btn_activity_config_helpcomment;
	private Button btn_activity_config_helpdesk;
	private Button btn_activity_config_evaluation;
	private Button btn_activity_config_logout;
	private Button btn_activity_config_login;
	
	
	private String TAG_FRAGMENT;
	
	Fragment loginFragment = new LoginFragment();
	FragmentManager fragmentManager = getSupportFragmentManager();
	SharedPreferenceUtil user_info = new SharedPreferenceUtil();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);

		if(!user_info.isExist(this, ItDocConstants.SHARED_KEY_EMAIL))
		{
			TAG_FRAGMENT=ItDocConstants.TAG_FRAGMENT_JOIN;
		}
		else
		{
			TAG_FRAGMENT=ItDocConstants.TAG_FRAGMENT_LOGIN;
		}
		
		setLayout();
		setListener();
	}

	private void setLayout() {
		// TODO Auto-generated method stub
		btn_activity_config_push = (Button) findViewById(R.id.btn_view_push);
		btn_activity_config_alarm = (Button) findViewById(R.id.btn_view_alarm);
		btn_activity_config_helpcomment = (Button) findViewById(R.id.btn_view_helpcomment);
		btn_activity_config_helpdesk = (Button) findViewById(R.id.btn_view_helpdesk);
		btn_activity_config_evaluation = (Button) findViewById(R.id.btn_view_evalution);
		btn_activity_config_logout = (Button) findViewById(R.id.btn_view_logout);
		btn_activity_config_login = (Button) findViewById(R.id.btn_view_login);
	}

	private void setListener() {
		// TODO Auto-generated method stub
		btn_activity_config_push.setOnClickListener(this);
		btn_activity_config_alarm.setOnClickListener(this);
		btn_activity_config_helpcomment.setOnClickListener(this);
		btn_activity_config_helpdesk.setOnClickListener(this);
		btn_activity_config_evaluation.setOnClickListener(this);
		btn_activity_config_logout.setOnClickListener(this);
		btn_activity_config_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_view_push:
			Toast.makeText(this, "푸시 알림", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn_view_alarm:
			Toast.makeText(this, "알림 소리", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn_view_helpcomment:
			Toast.makeText(this, "도움말", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn_view_helpdesk:
			Toast.makeText(this, "고객센터", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn_view_evalution:
			Toast.makeText(this, "잇닷(it doc) 평가하기", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn_view_logout:
			Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
			btn_activity_config_logout.setVisibility(View.GONE);
			btn_activity_config_login.setVisibility(View.VISIBLE);
			
			SharedPreferenceUtil.setData(this, ItDocConstants.SHARED_KEY_EMAIL, null);
			
			TAG_FRAGMENT = ItDocConstants.TAG_FRAGMENT_JOIN;
			//fragmentManager.beginTransaction().add(R.id.relativeLayout_activity_config, loginFragment,TAG_FRAGMENT).addToBackStack(null).commit();
			Log.d("tag",TAG_FRAGMENT);
			
			break;
			
		case R.id.btn_view_login:
			Toast.makeText(this, "로그인 페이지 이동.", Toast.LENGTH_SHORT).show();
			
			//login크래그먼트 호출
			TAG_FRAGMENT = ItDocConstants.TAG_FRAGMENT_LOGIN;
			Log.d("tag",TAG_FRAGMENT);
			fragmentManager.beginTransaction().add(R.id.relativeLayout_activity_config, loginFragment,TAG_FRAGMENT).addToBackStack(null).commit();
			//기존 액티비티에 달린 레이아웃 뷰 gone 시킴
			findViewById(R.id.linearLayout_config).setVisibility(View.GONE);
			
			//Intent intent = new Intent(this,UserManagerActivity.class);
			//intent.putExtra(ItDocConstants.TAG_FRAGMENT_LOGIN, 2);
			//startActivity(intent);
			break;
		}
		
	
	}
	
	
	
	@Override
	public void onBackPressed() {
		//Fragment fragment =  getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
		
	/*	//if(fragment!=null){
			boolean isLogin = user_info.isExist(this, ItDocConstants.SHARED_KEY_EMAIL);
			Intent intent = new Intent();
			intent.putExtra("isLogin", isLogin);
			intent.putExtra("data", TAG_FRAGMENT);
			setResult(RESULT_OK,intent);
			finish();*/
			//기존 액티비티에 달린 레이아웃 뷰  visible 시킴 프래그먼크 view는 gone
			if(TAG_FRAGMENT.equals(ItDocConstants.TAG_FRAGMENT_JOIN)){			// join fragmnet에서 돌아왔을 때, 다시 나머지 뷰들 보여줌
				//findViewById(R.id.relativeLayout_activity_config).setVisibility(View.GONE);
				Log.d("tag",TAG_FRAGMENT);
				Intent intent = new Intent(this, MainDrawerActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			//	main_drawer_activity.setDrawerLeft();
				
				
			}else if(TAG_FRAGMENT.equals(ItDocConstants.TAG_FRAGMENT_LOGIN)){	// login fragmnet에서 돌아왔을 때, 다시 나머지 뷰들 보여줌
				//findViewById(R.id.relativeLayout_activity_config).setVisibility(View.GONE);
				Log.d("tag",TAG_FRAGMENT);
				//main_drawer_activity.setDrawerLeft();
				//findViewById(R.id.relativeLayout_activity_config).setVisibility(View.VISIBLE);
				//findViewById(R.id.linearLayout_config).setVisibility(View.VISIBLE);
				Intent intent = new Intent(this, MainDrawerActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
				
				//fragmentManager.beginTransaction().add(R.id.relativeLayout_activity_config, loginFragment,TAG_FRAGMENT).addToBackStack(null).commit();
				//Intent intent = new Intent(this, MainDrawerActivity.class);
				//startActivity(intent);
				
			}
	//	}
		
		super.onBackPressed();
	}
}
