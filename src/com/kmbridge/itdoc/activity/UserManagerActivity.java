package com.kmbridge.itdoc.activity;



import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.fragment.JoinFragment;
import com.kmbridge.itdoc.fragment.LoginFragment;
import com.kmbridge.itdoc.util.ItDocConstants;

public class UserManagerActivity extends ActionBarActivity implements OnClickListener,CommonMethods {
	
	Button btn_activity_user_manager_email_join;
	Button btn_activity_user_manager_email_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_manager);
		setActionBar(getSupportActionBar());
		//회원가입
		btn_activity_user_manager_email_join = (Button) findViewById(R.id.email_join);
		btn_activity_user_manager_email_join.setOnClickListener(this);
		//로그인
		btn_activity_user_manager_email_login = (Button) findViewById(R.id.email_login);
		btn_activity_user_manager_email_login.setOnClickListener(this);
		
		Bundle bundle = getIntent().getExtras();
		if(!(bundle==null)){
			if(bundle.containsKey("callLogin")){
				int code = bundle.getInt("callLogin");
				loginLayoutElement();
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// 회원가입
		case R.id.email_join:
			joinLayoutElement();
			//Toast.makeText(this, "이메일로그인", Toast.LENGTH_SHORT).show();
			break;
		
		//
		case R.id.email_login:
			loginLayoutElement();
			break;
		}

	}
	/**
	 * 프래그먼트에서 백버튼 눌렀을 때 원래 액티비티로 돌아오게 하는 방법 
	 * 1. 프래그먼트를 add하거나 replace할 떄 , .addToBackStack(null)을 넣어준다. 
	 * 2. 액티비티에서  onBackPressed()메서드를 오버라이딩하여 super.onBackPressed(); 하기 전까지 원하는 코드를 작성한다. 
	 * http://stackoverflow.com/questions/7992216/android-fragment-handle-back-button-press
	 */
	private String TAG_FRAGMENT;
	public void joinLayoutElement() {
		// TODO Auto-generated method stub
		Fragment joinFragment = new JoinFragment();
		//Bundle args = new Bundle();
		//args.putInt(PlanetFragment.ARG_PLANET_NUMBER, 0);
		//join_fragment.setArguments(args);
		TAG_FRAGMENT = ItDocConstants.TAG_FRAGMENT_JOIN;
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.linearLayout_user_manager_for_join_fragment, joinFragment,TAG_FRAGMENT).addToBackStack(null).commit();
		findViewById(R.id.linearLayout_user_manager).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_user_manager_for_login_fragment).setVisibility(View.GONE);
	}
	
	public void loginLayoutElement() {
		// TODO Auto-generated method stub
		Fragment loginFragment = new LoginFragment();
		//Bundle args = new Bundle();
		//args.putInt(PlanetFragment.ARG_PLANET_NUMBER, 0);
		//join_fragment.setArguments(args);
		TAG_FRAGMENT = ItDocConstants.TAG_FRAGMENT_LOGIN;
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.linearLayout_user_manager_for_login_fragment, loginFragment,TAG_FRAGMENT).addToBackStack(null).commit();
		findViewById(R.id.linearLayout_user_manager).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_user_manager_for_join_fragment).setVisibility(View.GONE);
	}

	// back버튼을 두번 누를 때 앱이 종료되도록 하자.
	// 마찬가지로 MainDrawerActivity에도 back 버튼 눌렀을 때, 두번눌러야 종료되도록 하자  
	@Override
	public void onBackPressed() {
		Fragment fragment =  getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
		Log.d("koo", "onBackPressed");
		if(fragment!=null){
			Log.d("koo", "onBackPressed not null");
			String tag = fragment.getTag();
			Log.d("koo", "onBackPressed not null tag:"+tag);
			if(tag.equals(ItDocConstants.TAG_FRAGMENT_JOIN)){			// join fragmnet에서 돌아왔을 때, 다시 나머지 뷰들 보여줌
				findViewById(R.id.linearLayout_user_manager).setVisibility(View.VISIBLE);
				findViewById(R.id.linearLayout_user_manager_for_login_fragment).setVisibility(View.VISIBLE);
			}else if(tag.equals(ItDocConstants.TAG_FRAGMENT_LOGIN)){	// login fragmnet에서 돌아왔을 때, 다시 나머지 뷰들 보여줌
				findViewById(R.id.linearLayout_user_manager).setVisibility(View.VISIBLE);
				findViewById(R.id.linearLayout_user_manager_for_join_fragment).setVisibility(View.VISIBLE);
			}
		}
		
		//밑에 코드에 의해서 실제 백버튼 기능이 수행됨.
		super.onBackPressed();
	    
	}
	
	
	//액션바 세팅하는 부분
	@Override
	public void setActionBar(ActionBar actionBar) {
		//actionBar.setTitle(R.string.a)
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
	}
	
	
	
}
