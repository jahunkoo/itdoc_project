package com.kmbridge.itdoc.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.fragment.JoinFragment;
import com.kmbridge.itdoc.fragment.LoginFragment;

public class UserManagerActivity extends BasicActivity implements OnClickListener {
	Button btn_activity_user_manager_email_join;
	Button btn_activity_user_manager_email_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_manager);

		//회원가입
		btn_activity_user_manager_email_join = (Button) findViewById(R.id.email_join);
		btn_activity_user_manager_email_join.setOnClickListener(this);
		//로그인
		btn_activity_user_manager_email_login = (Button) findViewById(R.id.email_login);
		btn_activity_user_manager_email_login.setOnClickListener(this);

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

	public void joinLayoutElement() {
		// TODO Auto-generated method stub
		Fragment joinFragment = new JoinFragment();
		//Bundle args = new Bundle();
		//args.putInt(PlanetFragment.ARG_PLANET_NUMBER, 0);
		//join_fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().add(R.id.linearLayout_user_manager_for_join_fragment, joinFragment).commit();
		findViewById(R.id.linearLayout_user_manager).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_user_manager_for_login_fragment).setVisibility(View.GONE);
	}
	
	public void loginLayoutElement() {
		// TODO Auto-generated method stub
		Fragment loginFragment = new LoginFragment();
		//Bundle args = new Bundle();
		//args.putInt(PlanetFragment.ARG_PLANET_NUMBER, 0);
		//join_fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().add(R.id.linearLayout_user_manager_for_login_fragment, loginFragment).commit();
		findViewById(R.id.linearLayout_user_manager).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_user_manager_for_join_fragment).setVisibility(View.GONE);
	}
}
