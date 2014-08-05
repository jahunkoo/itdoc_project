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
import com.kmbridge.itdoc.activity.ProfilePictureActivity;
import com.kmbridge.itdoc.fragment.JoinFragment;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class JoinConnectHandler extends Handler {
	
	public static final int SHOW_LOADING_LAYOUT = 0;
	public static final int END_LOADING_LAYOUT = 1;
	public static final int SHOW_JOIN = 2;
	public static final int SHOW_EXIT = 3;
	public static final int SHOW_ERROR = 4;
	
	private View loadingView;
	private Activity activity;
	private String result;
	
	public String firstname;
	public String lastname;
	public boolean isNotFirst;
	public JoinFragment joinFragment;

	public JoinConnectHandler(Context context, Fragment fragment) {
		//Log.d("kim","Handler create : ");
		activity = (Activity) context;
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadingView = inflator.inflate(R.layout.connection_loading, null);
		//activity.addContentView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		activity.addContentView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		joinFragment = ((JoinFragment)fragment);
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
		case SHOW_JOIN:
			Log.d("koo", "loading end imageview_show start");
			Toast.makeText(activity, Sentence.successJoin,Toast.LENGTH_SHORT).show();
			result =  (String) msg.obj;
			//joinFragment.JoinResult(result);
			EditText edittxt_activity_join_join_email = (EditText) activity.findViewById(R.id.join_email);
			EditText edittxt_activity_join_join_password = (EditText) activity.findViewById(R.id.join_password);
			EditText edittxt_activity_join_join_firstname = (EditText) activity.findViewById(R.id.join_firstname);
			EditText edittxt_activity_join_join_lastname = (EditText) activity.findViewById(R.id.join_lastname);
			
			firstname = edittxt_activity_join_join_firstname.getText().toString();
			lastname = edittxt_activity_join_join_lastname.getText().toString();

			SharedPreferenceUtil.setData(activity,ItDocConstants.SHARED_KEY_EMAIL, edittxt_activity_join_join_email.getText().toString());
			SharedPreferenceUtil.setData(activity,ItDocConstants.SHARED_KEY_PASSWORD, edittxt_activity_join_join_password.getText().toString());
			SharedPreferenceUtil.setData(activity,ItDocConstants.SHARED_KEY_NAME,lastname+firstname);
			
			//처음 설치 여부를 확인
	    	isNotFirst=SharedPreferenceUtil.isExist(activity, ItDocConstants.SHARED_KEY_FIRST_CHECK);
	    	Log.d("kim","join_check = "+isNotFirst);
	    	if(isNotFirst==true)
	    	{
	    		//처음 실행 여부 체크
	    		SharedPreferenceUtil.setData(activity, ItDocConstants.SHARED_KEY_FIRST_CHECK, "notfirst");
	    		Log.d("kim","join_check = "+isNotFirst);
	    	}
	    	else
	    	{
	    		//처음 실행 여부 체크
	    		SharedPreferenceUtil.setData(activity, ItDocConstants.SHARED_KEY_FIRST_CHECK, null);
	    		Log.d("kim","join_check = "+isNotFirst);
	    	}
	    	
			Intent intent = new Intent(activity,ProfilePictureActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(intent);
			//activity.finish();
			
			
			
			//Log.d("kim","Handler : "+result);
			break;
		case SHOW_EXIT:
			Toast.makeText(activity, Sentence.existEmail,Toast.LENGTH_SHORT).show();
			break;
		case SHOW_ERROR:
			Toast.makeText(activity, Sentence.joinFail,Toast.LENGTH_SHORT).show();
			break;
			
		default:
			break;
		}
	}

}
