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

public class UserManagerActivity extends BasicActivity implements
		OnClickListener {
	Button btn_activity_user_manager_email_join;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_manager);

		btn_activity_user_manager_email_join = (Button) findViewById(R.id.email_join);
		btn_activity_user_manager_email_join.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// 회원가입
		case R.id.email_join:
			joinLayoutElement();
			Toast.makeText(this, "이메일로그인", Toast.LENGTH_SHORT)
			.show();
			break;
		}

	}

	private void joinLayoutElement() {
		// TODO Auto-generated method stub
		Fragment joinFragment = new JoinFragment();
		//Bundle args = new Bundle();
		//args.putInt(PlanetFragment.ARG_PLANET_NUMBER, 0);
		//join_fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.add(R.id.linearLayout_user_manager_for_join_fragment, joinFragment)
				.commit();
		findViewById(R.id.linearLayout_user_manager).setVisibility(View.GONE);
	}
}
