package com.kmbridge.itdoc.fragment;

import java.util.Properties;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.MainDrawerActivity;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.User;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private EditText edittxt_activity_login_login_email;
	private EditText edittxt_activity_login_login_password; 
	private Button btn_activity_login_login_submit;
	
	// 로그인 입력 상자 변수
	private boolean isLoginCheck = false;
	private boolean check = false;
	private boolean isEmailInput;
	private boolean isPwdInput;
	private final int SUCCESS_NUM = 2;
	
	private ConnectionBridge bridge = new ConnectionBridge();
	private User user = new User();
	
	private String methodUrl;
	private String message;
	private Properties prop;
	/**
	 * 프래그먼트가 생성되면 우선 타이틀부터 바꾸자.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//*****************************actionbar title setting ***********************
		getActivity().getActionBar().setTitle(R.string.title_fragment_login);
		//****************************************************************************
		prop = new Properties();
		// view가져오고
		View rootView = inflater.inflate(R.layout.fragment_login, container,false);
		
		edittxt_activity_login_login_email = (EditText) rootView.findViewById(R.id.login_email);
		edittxt_activity_login_login_password = (EditText) rootView.findViewById(R.id.login_password);
		btn_activity_login_login_submit = (Button) rootView.findViewById(R.id.login_submit);
		btn_activity_login_login_submit.setOnClickListener(this);
		
		return rootView;
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.login_submit:
			if (!getTextInformation())
				return;
			if (isAllInfoInput() == true) {             
				methodUrl = "login";
				Log.d("koo", prop.toString());
				//message = bridge.login(methodUrl, prop, getActivity());
				isLoginCheck = bridge.login(methodUrl, prop, getActivity());
				//Log.d("login",message);
				//if (message.equals("error")) {
				if(isLoginCheck==false){
					Toast.makeText(getActivity(), Sentence.errorLogin,Toast.LENGTH_SHORT).show();
				} else if (isLoginCheck==true) { //로그인 성공
					// 회원가입 성공
					// phoneBook();
					
					//SharedPreferenceUtil user_info = new SharedPreferenceUtil();
					
					//user_info.setData(getActivity(),"user_email", edittxt_activity_join_join_email.getText().toString());
					//user_info.setData(getActivity(), "user_pwd", edittxt_activity_join_join_password.getText().toString());
					/*
					SharedPreferences shared_user_info = getActivity().getSharedPreferences("user_info", 0);
					SharedPreferences.Editor editor = shared_user_info.edit();
					
					editor.putString("user_email", edittxt_activity_join_join_email.getText().toString());
					editor.putString("user_pwd", edittxt_activity_join_join_password.getText().toString());
					
					editor.commit();*/
					SharedPreferenceUtil user_info = new SharedPreferenceUtil();
					user_info.setData(getActivity(),ItDocConstants.SHARED_KEY_EMAIL, edittxt_activity_login_login_email.getText().toString());
					
					Toast.makeText(getActivity(), Sentence.successLogin,Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getActivity(),MainDrawerActivity.class);
					//intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(intent);
					getActivity().finish();
				}
			} else {
				Toast.makeText(getActivity(), Sentence.noInfomationMessage,
						Toast.LENGTH_LONG).show();
			}
			break;

		}
	}
	
	private boolean getTextInformation() {
		// TODO Auto-generated method stub
		isEmailInput = false;
		isPwdInput = false;

		String email = edittxt_activity_login_login_email.getText().toString();
		// 테스트 해보니 EditText는 입력을 안해도 공백 자체가 값으로 인식됨. 그래서 공백 여부 check
		if (email.trim().length() != 0) {
			if (!user.isEmailAddress(email)) {
				Toast.makeText(getActivity(), Sentence.notEmailType,Toast.LENGTH_SHORT).show();
			} else {
				prop.put("email", email);
				isEmailInput = true;
			}
		} else {
			Toast.makeText(getActivity(), Sentence.noEmailMessage,Toast.LENGTH_SHORT).show();
		}

		String password = edittxt_activity_login_login_password.getText()
				.toString();
		if (password.trim().length() != 0) {
			if (password.trim().length() <= 5) {
				Toast.makeText(getActivity(), Sentence.notPwdType,Toast.LENGTH_SHORT).show();
			} else {
				prop.put("password", password);
				isPwdInput = true;
			}
		} else {
			Toast.makeText(getActivity(), Sentence.noPwdMessage,Toast.LENGTH_SHORT).show();
		}

		return true;
	}
	
	private boolean isAllInfoInput() {
		// TODO Auto-generated method stub
		check = false;

		int count = 0;
		if (isEmailInput == true) count++;
		if (isPwdInput == true)	count++;
		
		/*
		 * if (isCellPhoneInput == true) count++; if (isBirthYearInput == true)
		 * count++; if (isGenderInput == true) count++;
		 */
		if (count == SUCCESS_NUM)
			check = true;

		return check;
	}

}
