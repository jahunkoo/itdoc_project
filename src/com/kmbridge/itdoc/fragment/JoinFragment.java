package com.kmbridge.itdoc.fragment;

import java.util.Properties;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.User;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class JoinFragment extends Fragment {
	
	//회원가입 입력 상자(사용자 이메일, 비밀번호, 이름, 성, 회원가입 버튼 순)
	private EditText edittxt_activity_join_join_email; 
	private EditText edittxt_activity_join_join_password;
	private EditText edittxt_activity_join_join_lasttname;
	private EditText edittxt_activity_join_join_firstname;
	private Button btn_activity_join_join_submit;
	
	//회원가입 입력 상자 체크 변수
	private boolean check = false;
	private boolean isEmailInput;
	private boolean isPwdInput;
	private boolean isNameInput;
	private final int SUCCESS_NUM = 3;

	private String methodUrl;
	private String message;
	private Properties prop;

	public InputMethodManager imm;
	
	private ConnectionBridge bridge = new ConnectionBridge();
	private User user = new User();
	
	/*@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prop = new Properties();
	//	setLayoutElement();
	}*/
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		prop = new Properties();
		//setLayoutElement();
		
		//view가져오고 
        View rootView = inflater.inflate(R.layout.fragment_join, container, false);
        return rootView;
	}
	
	
/*	private void setLayoutElement() {
		// TODO Auto-generated method stub
		edittxt_activity_join_join_email = (EditText) findViewById(R.id.join_email);
		edittxt_activity_join_join_password = (EditText) findViewById(R.id.join_password);
		edittxt_activity_join_join_firstname = (EditText) findViewById(R.id.join_firstname);
		edittxt_activity_join_join_lasttname = (EditText) findViewById(R.id.join_lastname);
		btn_activity_join_join_submit = (Button) find
	}*/
}
