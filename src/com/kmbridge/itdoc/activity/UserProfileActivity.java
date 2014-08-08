package com.kmbridge.itdoc.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import lazyList.ImageLoader;

import org.json.JSONException;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ReviewAdapter;
import com.kmbridge.itdoc.connect.JsonParser;
import com.kmbridge.itdoc.dto.ReviewKeyword;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.thread.UserProfileConnectionThread;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;
/**
 * 이 클래스를 호출할 때, 반드시  Intent에 ItDocConstants.EMAIL를 담아서 email값을 보내자
 * @author	: jahun koo
 * @date	: 2014. 8. 4.
 * @desc	:
 */
public class UserProfileActivity extends FragmentActivity implements OnClickListener{

	public static String testJSONString;
	
	private UserView userView; 
	private LayoutInflater inflator;
	
	private ScrollView rootScrollView;	
	private ImageView userProfileImgView; 		
	private TextView userNameTextView; 			
	private TextView reviewNumTextView; 			
	private TextView followNumTextView;			
	private TextView followingNumTextView; 		
	private Button followButton; 				
	private TextView certiMessageTextView; 		
	private LinearLayout reviewContainLayout;
	private TextView noReviewMessageTextView;
	private ImageButton certiEmailImgButton; 	
	private ImageButton certiPhoneImgButton; 	
	private ImageButton certiFacebookImgButton; 
	private LinearLayout actionCertiLayout;
	public ImageLoader imageLoader;
	//기능을 위해 만든 맴버변수들
	private boolean isMyPage; 
	private boolean isLogin;
	private String userEmail;
	private String myEmail;
	
	public ImageView getUserProfileImgView(){
		return userProfileImgView;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		setElements();
		setListner();
		try {
		//**************현재 들어온 사람이 본인인지 아니면 다른 사람인지 판별 **************************
		Bundle bundle = getIntent().getExtras();
		
		if(bundle.containsKey(ItDocConstants.EMAIL)){
			userEmail = bundle.getString(ItDocConstants.EMAIL);
		}else{
			Log.d("koo", "Intent에 ItDocConstants.EMAIL를 담아서 email값을 보내야 됨 ");
			finish();
		}
		myEmail = new SharedPreferenceUtil().getData(this, ItDocConstants.SHARED_KEY_EMAIL);
		isLogin = true;	
		
		if(myEmail.equals(userEmail)) 	isMyPage = true;
		else 							isMyPage = false;
		
		} catch (RecordNotFoundException e2) {
			isLogin = false;
		}
		//if(myEmail.equals(userEmail)) 	isMyPage = true;
		//else 							isMyPage = false;
		//*********************************************************************		
		
			
		//*******************************  통신    ********************************
//		UserProfileConnectionThread thread = new UserProfileConnectionThread(this, ItDocConstants.METHOD_URL_GET_USERVIEW_BY_EMAIL, myEmail, userEmail);
//		thread.start();
		//*********************************************************************
	}

	private void setListner() {
		followButton.setOnClickListener(this);
	}
	
	
	private void setElements() {
		inflator = getLayoutInflater();
		imageLoader = new ImageLoader(this);
		rootScrollView 			= (ScrollView) findViewById(R.id.scrollview_activity_user_profile);
		userProfileImgView 		= (ImageView) findViewById(R.id.imageview_activity_user_profile_user_profile);
		userNameTextView 		= (TextView) findViewById(R.id.textview_activity_user_profile_user_name);
		reviewNumTextView 		= (TextView) findViewById(R.id.textview_activity_user_profile_review_num);
		followNumTextView 		= (TextView) findViewById(R.id.textview_activity_user_profile_follow_num);
		followingNumTextView 	= (TextView) findViewById(R.id.textview_activity_user_profile_following_num);
		followButton 			= (Button) findViewById(R.id.button_activity_user_profile_follow);
		certiMessageTextView 	= (TextView) findViewById(R.id.textview_activity_user_certification);
		reviewContainLayout 	= (LinearLayout) findViewById(R.id.linearlayout_activity_user_profile_contain_review);
		noReviewMessageTextView = (TextView) findViewById(R.id.textview_activity_user_profile_no_review);
		certiEmailImgButton 	= (ImageButton) findViewById(R.id.imagebutton_activity_user_profile_certification_email);
		certiPhoneImgButton 	= (ImageButton) findViewById(R.id.imagebutton_activity_user_profile_certification_phone);
		certiFacebookImgButton 	= (ImageButton) findViewById(R.id.imagebutton_activity_user_profile_certification_facebook);
		actionCertiLayout 		= (LinearLayout) findViewById(R.id.linearlayout_activity_user_profile_action_my_certi);
	}
	
	
	
	public void setDownloadData(UserView downloadedUserView){
		userNameTextView.setText(downloadedUserView.getName());
		int reviewSize = downloadedUserView.getReviewViewList().size();
		reviewNumTextView.setText(""+reviewSize);
		followNumTextView.setText(""+downloadedUserView.getFollowNum());
		followingNumTextView.setText(""+downloadedUserView.getFollowingNum());
		if(isMyPage){
			followButton.setText(R.string.button_user_profile_update_my_profile);
			followButton.setTag(ItDocConstants.UPDATE_PROFILE);
			certiMessageTextView.setVisibility(View.INVISIBLE);
			actionCertiLayout.setVisibility(View.VISIBLE);
			
		}else{
			actionCertiLayout.setVisibility(View.GONE);
			
			if(downloadedUserView.isFollow()){
				followButton.setText(R.string.button_user_profile_followed);
				followButton.setTag(ItDocConstants.FOLLOWED);
			}else{
				followButton.setText(R.string.button_user_profile_let_follow);
				followButton.setTag(ItDocConstants.NOT_FOLLOW);
			}
		}
		//List<ReviewView> reviewViewList = userView.getReviewViewList();
		//createReviewLayout(reviewViewList);
	}
	
	
	private void setScrollViewStyle(ScrollView rootScrollView){
		rootScrollView.setVerticalScrollBarEnabled(false);
	}
	
	
	@Override
	public void onClick(View v) {
		int viewId = v.getId();

		if(viewId == followButton.getId()){
			Log.d("koo", "thread update num");
		}
	}

	
}
