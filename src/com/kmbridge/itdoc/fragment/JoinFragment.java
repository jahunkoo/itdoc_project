package com.kmbridge.itdoc.fragment;

import java.util.Properties;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.ProfilePictureActivity;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.User;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.thread.ProfilePictureConnectThread;
import com.kmbridge.itdoc.thread.JoinConnectThread;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class JoinFragment extends Fragment implements View.OnClickListener {

	// 회원가입 입력 상자(사용자 이메일, 비밀번호, 이름, 성, 회원가입 버튼 순)
	public EditText edittxt_activity_join_join_email;
	public EditText edittxt_activity_join_join_password;
	public EditText edittxt_activity_join_join_lastname;
	public EditText edittxt_activity_join_join_firstname;
	public Button btn_activity_join_join_submit;

	// 회원가입 입력 상자 체크 변수
	private boolean check = false;
	private boolean isEmailInput;
	private boolean isPwdInput;
	private boolean isNameInput;
	private final int SUCCESS_NUM = 3;
	public String firstname;
	public String lastname;
	public boolean isNotFirst;
	private User user = new User();

	private String methodUrl;
	
	public Properties prop;

	public InputMethodManager imm;
	
	public String message;
	public String joinResult;
	//SharedPreferenceUtil userInfo = new SharedPreferenceUtil();

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * prop = new Properties(); setLayoutElement(); }
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		ActionBar actionbar = getActivity().getActionBar();
	    actionbar.show();
	    actionbar.setTitle(R.string.title_fragment_join);
	    
		//*****************************actionbar title setting ***********************
		//getActivity().getActionBar().setTitle(R.string.title_fragment_join);
		//****************************************************************************
		Log.d("debug","check3");
		prop = new Properties();
		// view가져오고
		View rootView = inflater.inflate(R.layout.fragment_join, container,	false);
		edittxt_activity_join_join_email = (EditText) rootView.findViewById(R.id.join_email);
		edittxt_activity_join_join_password = (EditText) rootView.findViewById(R.id.join_password);
		edittxt_activity_join_join_firstname = (EditText) rootView.findViewById(R.id.join_firstname);
		edittxt_activity_join_join_lastname = (EditText) rootView.findViewById(R.id.join_lastname);
		btn_activity_join_join_submit = (Button) rootView.findViewById(R.id.join_submit);
		btn_activity_join_join_submit.setOnClickListener(this);
		return rootView;
	}

	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.join_submit:
			if (!getTextInformation())
				return;
			if (isAllInfoInput() == true) {
				//methodUrl = "register";
				//Log.d("bridge",message);
				
				JoinConnectThread thread = new JoinConnectThread("register",prop, getActivity());
				thread.start();
				
				message=joinResult;
				Log.d("kim","join_submit: "+message);
				
				/*try{
					thread.join();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				message=thread.getResult();
				
				Log.d("kim","join_submit: "+message);
				
				if (message.equals("exist")) {
					Toast.makeText(getActivity(), Sentence.existEmail,Toast.LENGTH_SHORT).show();
				} else if (message.equals("error")) {
					Toast.makeText(getActivity(), Sentence.joinFail,Toast.LENGTH_SHORT).show();
				} else if (message.equals("success")) {
					// 회원가입 성공
					// phoneBook();
					
					SharedPreferenceUtil.setData(getActivity(),ItDocConstants.SHARED_KEY_EMAIL, edittxt_activity_join_join_email.getText().toString());
					SharedPreferenceUtil.setData(getActivity(),ItDocConstants.SHARED_KEY_PASSWORD, edittxt_activity_join_join_password.getText().toString());
					SharedPreferenceUtil.setData(getActivity(),ItDocConstants.SHARED_KEY_NAME,lastname+firstname);
					
					//처음 설치 여부를 확인
			    	isNotFirst=SharedPreferenceUtil.isExist(getActivity(), ItDocConstants.SHARED_KEY_FIRST_CHECK);
			    	//Log.d("check_kim","join_check = "+isNotFirst);
			    	if(isNotFirst==true)
			    	{
			    		//처음 실행 여부 체크
			    		SharedPreferenceUtil.setData(getActivity(), ItDocConstants.SHARED_KEY_FIRST_CHECK, "notfirst");
			    	}
			    	else
			    	{
			    		//처음 실행 여부 체크
			    		SharedPreferenceUtil.setData(getActivity(), ItDocConstants.SHARED_KEY_FIRST_CHECK, null);
			    	}
					
					
					Toast.makeText(getActivity(), Sentence.successJoin,Toast.LENGTH_SHORT).show();
					
					
					Intent intent = new Intent(getActivity(),ProfilePictureActivity.class);
					//intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(intent);
					getActivity().finish();
					//************************************************add koo*************************************************
					//프로필사진 올리는 액티비티로 갔을 때, 백버튼 눌렀을때 상황 
					//1. 회원가입 프래그먼트 뗴어냄
					//2. UserManagerActivity 화면 보이게 함.
					//3. login fragment보이게 함
					//메서드 화 하셈
					FragmentManager fm = getActivity().getSupportFragmentManager();
					fm.beginTransaction().detach(this).commit();
					getActivity().findViewById(R.id.linearLayout_pager).setVisibility(View.VISIBLE);
					getActivity().findViewById(R.id.linearLayout_screen_slide_for_join).setVisibility(View.GONE);
					getActivity().findViewById(R.id.linearLayout_screen_slide_for_login).setVisibility(View.GONE);
					ActionBar actionbar = getActivity().getActionBar();
				    actionbar.hide();
					//getActivity().findViewById(R.id.linearLayout_user_manager).setVisibility(View.VISIBLE);
					//getActivity().findViewById(R.id.linearLayout_user_manager_for_login_fragment).setVisibility(View.VISIBLE);
					//********************************************************************************************************
					// 회원가입 후 로그인을 할 때, 에디트 텍스트에 hint로   사용자가 가입한 이메일을 띄워주도록 하자. 
					//
				}
			*/} else {
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
		isNameInput = false;

		String email = edittxt_activity_join_join_email.getText().toString();
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

		String password = edittxt_activity_join_join_password.getText()
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
		firstname = edittxt_activity_join_join_firstname.getText().toString();
		lastname = edittxt_activity_join_join_lastname.getText().toString();
		String name = firstname + "_" + lastname;
		if (name.trim().length() != 0) {
			prop.put("name", name);
			isNameInput = true;
		} else {
			Toast.makeText(getActivity(), Sentence.noFirstNameMessage,
					Toast.LENGTH_SHORT).show();
		}

		return true;
	}

	private boolean isAllInfoInput() {
		// TODO Auto-generated method stub
		check = false;

		int count = 0;
		if (isEmailInput == true)
			count++;
		if (isPwdInput == true)
			count++;
		if (isNameInput == true)
			count++;
		/*
		 * if (isCellPhoneInput == true) count++; if (isBirthYearInput == true)
		 * count++; if (isGenderInput == true) count++;
		 */
		if (count == SUCCESS_NUM)
			check = true;

		return check;
	}
	
}
