package com.kmbridge.itdoc.thread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.MainDrawerActivity;
import com.kmbridge.itdoc.fragment.LoginFragment;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class LoginConnectHandler extends Handler {
	
	public static final int SHOW_LOADING_LAYOUT = 0;
	public static final int END_LOADING_LAYOUT = 1;
	public static final int SHOW_LOGIN = 2;
	public static final int SHOW_ERROR = 3;
	
	private View loadingView;
	private Activity activity;
	private String result;
	
	public String firstname;
	public String lastname;
	public boolean isNotFirst;
	public LoginFragment loginFragment;

	public LoginConnectHandler(Context context, Fragment fragment) {
		//Log.d("kim","Handler create : ");
		activity = (Activity) context;
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadingView = inflator.inflate(R.layout.connection_loading, null);
		//activity.addContentView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		activity.addContentView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		loginFragment = ((LoginFragment)fragment);
	}
	
	@Override
	public void handleMessage(Message msg) {
		Log.d("kim","Handler : ");
		super.handleMessage(msg);
		switch (msg.what) {
		case SHOW_LOADING_LAYOUT:
			//*************버튼 비활성화
			//joinFragment.setButtonEnable(false);
			loadingView.bringToFront();
			loadingView.setVisibility(View.VISIBLE);
			Log.d("koo", "loading page start");
			break;
		case END_LOADING_LAYOUT:
			loadingView.setVisibility(View.GONE);
			//*************버튼 활성화
			//profilePictureActivity.setButtonEnable(true);
			Log.d("koo", "loading page end");
			break;
		case SHOW_LOGIN:
			Log.d("koo", "loading end imageview_show start");
			EditText edittxt_activity_login_login_email = (EditText) activity.findViewById(R.id.login_email);
			
			SharedPreferenceUtil.setData(activity,ItDocConstants.SHARED_KEY_EMAIL, edittxt_activity_login_login_email.getText().toString());
			SharedPreferenceUtil.setData(activity, ItDocConstants.SHARED_KEY_FIRST_CHECK, "notfirst");
			
			Toast.makeText(activity, Sentence.successLogin,Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(activity,MainDrawerActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //기존 설치 -> 로그인 -> 메인드로워만 남김 -> 뒤로가기 -> 종료
			activity.startActivity(intent);
			activity.finish(); //처음 설치 ->로그인->뒤로가기->스크린 슬라이드로 이동하게함
			
			break;
		case SHOW_ERROR:
			Toast.makeText(activity, Sentence.errorLogin,Toast.LENGTH_SHORT).show();
			break;
			
		default:
			break;
		}
	}

}
