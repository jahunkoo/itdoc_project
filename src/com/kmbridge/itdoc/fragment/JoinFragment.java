package com.kmbridge.itdoc.fragment;

import java.util.Properties;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class JoinFragment extends Fragment implements View.OnClickListener {

	// 회원가입 입력 상자(사용자 이메일, 비밀번호, 이름, 성, 회원가입 버튼 순)
	private EditText edittxt_activity_join_join_email;
	private EditText edittxt_activity_join_join_password;
	private EditText edittxt_activity_join_join_lastname;
	private EditText edittxt_activity_join_join_firstname;
	private Button btn_activity_join_join_submit;

	// 회원가입 입력 상자 체크 변수
	private boolean check = false;
	private boolean isEmailInput;
	private boolean isPwdInput;
	private boolean isNameInput;
	private final int SUCCESS_NUM = 3;

	private ConnectionBridge bridge = new ConnectionBridge();
	private User user = new User();

	private String methodUrl;
	private String message;
	private Properties prop;

	public InputMethodManager imm;

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * prop = new Properties(); setLayoutElement(); }
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		prop = new Properties();
		// view가져오고
		View rootView = inflater.inflate(R.layout.fragment_join, container,
				false);
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
				methodUrl = "register";
				Log.d("koo", prop.toString());
				message = bridge.register(methodUrl, prop, getActivity());
				Log.d("bridge",message);
				if (message.equals("exist")) {
					Toast.makeText(getActivity(), Sentence.existEmail,
							Toast.LENGTH_SHORT).show();
				} else if (message.equals("error")) {
					Toast.makeText(getActivity(), Sentence.joinFail,
							Toast.LENGTH_SHORT).show();
				} else if (message.equals("success")) {
					// 회원가입 성공
					// phoneBook();
					
					SharedPreferenceUtil user_info = new SharedPreferenceUtil();
					
					user_info.setData(getActivity(),ItDocConstants.SHARED_KEY_EMAIL, edittxt_activity_join_join_email.getText().toString());
					user_info.setData(getActivity(),ItDocConstants.SHARED_KEY_PASSWORD, edittxt_activity_join_join_password.getText().toString());
					/*
					SharedPreferences shared_user_info = getActivity().getSharedPreferences("user_info", 0);
					SharedPreferences.Editor editor = shared_user_info.edit();
					
					editor.putString("user_email", edittxt_activity_join_join_email.getText().toString());
					editor.putString("user_pwd", edittxt_activity_join_join_password.getText().toString());
					
					editor.commit();*/
					
					Toast.makeText(getActivity(), Sentence.successJoin,Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getActivity(),ProfilePictureActivity.class);
					startActivity(intent);
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
		isNameInput = false;

		String email = edittxt_activity_join_join_email.getText().toString();
		// 테스트 해보니 EditText는 입력을 안해도 공백 자체가 값으로 인식됨. 그래서 공백 여부 check
		if (email.trim().length() != 0) {
			if (!user.isEmailAddress(email)) {
				Toast.makeText(getActivity(), Sentence.notEmailType,
						Toast.LENGTH_SHORT).show();
			} else {
				prop.put("email", email);
				isEmailInput = true;
			}
		} else {
			Toast.makeText(getActivity(), Sentence.noEmailMessage,
					Toast.LENGTH_SHORT).show();
		}

		String password = edittxt_activity_join_join_password.getText()
				.toString();
		if (password.trim().length() != 0) {
			if (password.trim().length() <= 5) {
				Toast.makeText(getActivity(), Sentence.notPwdType,
						Toast.LENGTH_SHORT).show();
			} else {
				prop.put("password", password);
				isPwdInput = true;
			}
		} else {
			Toast.makeText(getActivity(), Sentence.noPwdMessage,
					Toast.LENGTH_SHORT).show();
		}
		String firstname = edittxt_activity_join_join_firstname.getText()
				.toString();
		String lastname = edittxt_activity_join_join_lastname.getText()
				.toString();
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
