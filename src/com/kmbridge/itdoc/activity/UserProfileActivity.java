package com.kmbridge.itdoc.activity;

import lazyList.ImageLoader;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.hardcoding.LoadData;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

/**
 * 이 클래스를 호출할 때, 반드시 Intent에 ItDocConstants.EMAIL를 담아서 email값을 보내자
 * 
 * @author : jahun koo
 * @date : 2014. 8. 4.
 * @desc :
 */
public class UserProfileActivity extends ActionBarActivity implements OnClickListener {

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
	private ImageView userFace;
	
	private LinearLayout lyt_follower;
	private LinearLayout lyt_following;

	// 기능을 위해 만든 맴버변수들
	private boolean isMyPage;
	private boolean isLogin;
	private String userEmail;
	private String myEmail;

	// 하드 코딩
	private TextView seeAllReview;

	private LoadData loadData;

	Boolean followToggle;
	
	public ImageView getUserProfileImgView() {
		return userProfileImgView;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		setElements();
		setListner();
		
		// **************현재 들어온 사람이 본인인지 아니면 다른 사람인지 판별
		// **************************
		Bundle bundle = getIntent().getExtras();

		if (bundle.containsKey(ItDocConstants.EMAIL)) {
			userEmail = bundle.getString(ItDocConstants.EMAIL);

		} else {
			Log.d("koo", "Intent에 ItDocConstants.EMAIL를 담아서 email값을 보내야 됨 ");
			finish();
		}
		// 하드코딩 위해 임시로 주석처리. 어차피 받아 올 필요 없음. 내 이메일은 무조건 test@gmail.com
		// myEmail = new SharedPreferenceUtil().getData(this,
		// ItDocConstants.SHARED_KEY_EMAIL);

		myEmail = "test@gmail.com";
		isLogin = true;

		if (myEmail.equalsIgnoreCase(userEmail))
			isMyPage = true;
		else
			isMyPage = false;

		// if(myEmail.equals(userEmail)) isMyPage = true;
		// else isMyPage = false;
		// *********************************************************************

		// ******************************* 통신 ********************************
		// UserProfileConnectionThread thread = new
		// UserProfileConnectionThread(this,
		// ItDocConstants.METHOD_URL_GET_USERVIEW_BY_EMAIL, myEmail, userEmail);
		// thread.start();
		// *********************************************************************

		// 하드코딩
		loadData = new LoadData(this);

		setDownloadData(loadData.getUserView(userEmail));

		followToggle = true;
		
	}

	private void setListner() {
		followButton.setOnClickListener(this);
		seeAllReview.setOnClickListener(this);

		lyt_follower.setOnClickListener(this);
		lyt_following.setOnClickListener(this);
	}

	private void setElements() {
		inflator = getLayoutInflater();
		imageLoader = new ImageLoader(this);
		rootScrollView = (ScrollView) findViewById(R.id.scrollview_activity_user_profile);
		userProfileImgView = (ImageView) findViewById(R.id.imageview_activity_user_profile_user_profile);
		userNameTextView = (TextView) findViewById(R.id.textview_activity_user_profile_user_name);
		reviewNumTextView = (TextView) findViewById(R.id.textview_activity_user_profile_review_num);
		followNumTextView = (TextView) findViewById(R.id.textview_activity_user_profile_follow_num);
		followingNumTextView = (TextView) findViewById(R.id.textview_activity_user_profile_following_num);
		followButton = (Button) findViewById(R.id.button_activity_user_profile_follow);
		certiMessageTextView = (TextView) findViewById(R.id.textview_activity_user_certification);
		reviewContainLayout = (LinearLayout) findViewById(R.id.linearlayout_activity_user_profile_contain_review);
		noReviewMessageTextView = (TextView) findViewById(R.id.textview_activity_user_profile_no_review);
		certiEmailImgButton = (ImageButton) findViewById(R.id.imagebutton_activity_user_profile_certification_email);
		certiPhoneImgButton = (ImageButton) findViewById(R.id.imagebutton_activity_user_profile_certification_phone);
		certiFacebookImgButton = (ImageButton) findViewById(R.id.imagebutton_activity_user_profile_certification_facebook);
		actionCertiLayout = (LinearLayout) findViewById(R.id.linearlayout_activity_user_profile_action_my_certi);
		lyt_follower = (LinearLayout) findViewById(R.id.linearlayout_user_profile_follower);
		lyt_following = (LinearLayout) findViewById(R.id.linearlayout_user_profile_following);
		userFace = (ImageView) findViewById(R.id.imageview_activity_user_profile_user_profile);
		
		seeAllReview = (TextView) findViewById(R.id.textview_user_profile_see_all_review);
	}

	public void setDownloadData(UserView downloadedUserView) {
		userNameTextView.setText(downloadedUserView.getName());
		int reviewSize = downloadedUserView.getReviewViewList().size();
		reviewNumTextView.setText("" + reviewSize);
		followNumTextView.setText("" + downloadedUserView.getFollowNum());
		followingNumTextView.setText("" + downloadedUserView.getFollowingNum());
		
		
		
		if (isMyPage) {
			followButton.setText(R.string.button_user_profile_update_my_profile);
			followButton.setTag(ItDocConstants.UPDATE_PROFILE);
			certiMessageTextView.setVisibility(View.INVISIBLE);
			actionCertiLayout.setVisibility(View.VISIBLE);

		} else {
			actionCertiLayout.setVisibility(View.GONE);

			int pictureId = getResources().getIdentifier(downloadedUserView.getPicturePath(), "drawable", getPackageName());

			userFace.setImageResource(pictureId);

			
			if (downloadedUserView.isFollow()) {
				followButton.setText(R.string.button_user_profile_followed);
				followButton.setTag(ItDocConstants.FOLLOWED);
			} else {
				followButton.setText(R.string.button_user_profile_let_follow);
				followButton.setTag(ItDocConstants.NOT_FOLLOW);
			}
		}
		// List<ReviewView> reviewViewList = userView.getReviewViewList();
		// createReviewLayout(reviewViewList);
	}

	private void setScrollViewStyle(ScrollView rootScrollView) {
		rootScrollView.setVerticalScrollBarEnabled(false);
	}

	@Override
	public void onClick(View v) {

		Intent intent;

		switch (v.getId()) {

		case R.id.textview_user_profile_see_all_review:

			intent = new Intent(this, SeeAllReviewListActivity.class);
			startActivity(intent);

			break;

		case R.id.button_activity_user_profile_follow:

			if (this.isMyPage) {
				intent = new Intent(this, UserProfileEditActivity.class);
				startActivity(intent);
			} else {
				if(followToggle) {
					followButton.setText(R.string.button_user_profile_let_follow);
					followButton.setTextColor(Color.parseColor("#FF0000"));
					followButton.setBackgroundColor(Color.parseColor("#DCDCDC"));
					followToggle = false;
				} else {
					followButton.setText(R.string.button_user_profile_followed);
					followButton.setTextColor(Color.parseColor("#FFFFFF"));
					followButton.setBackgroundColor(Color.parseColor("#FF0000"));
					followToggle = true;
				}
			}

			break;

		case R.id.linearlayout_user_profile_follower:
			intent = new Intent(this, UserFollowerActivity.class);
			intent.putExtra("followType", 1);
			startActivity(intent);

			break;

		case R.id.linearlayout_user_profile_following:
			intent = new Intent(this, UserFollowerActivity.class);
			intent.putExtra("followType", 0);
			startActivity(intent);

			break;
		default:
			break;

		}

	}

}
