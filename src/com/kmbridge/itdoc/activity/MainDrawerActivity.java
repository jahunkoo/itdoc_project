package com.kmbridge.itdoc.activity;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import lazyList.ImageLoader;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.DrawerTitleAdapter;
import com.kmbridge.itdoc.dto.ItemTitle;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.dto.SectionTitle;
import com.kmbridge.itdoc.dto.Title;
import com.kmbridge.itdoc.fragment.HanbangInfoFragment;
import com.kmbridge.itdoc.fragment.MyKokListFragment;
import com.kmbridge.itdoc.fragment.SearchResultClinicListFragment;
import com.kmbridge.itdoc.fragment.SuppotersFragment;
import com.kmbridge.itdoc.hardcoding.ClinicListFragment;
import com.kmbridge.itdoc.hardcoding.HardSearchFragment;
import com.kmbridge.itdoc.hardcoding.LoadData;
import com.kmbridge.itdoc.image.ImageManager;
import com.kmbridge.itdoc.image.RoundedImageView;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class MainDrawerActivity extends FragmentActivity implements OnClickListener, FragmentManager.OnBackStackChangedListener {

	public ImageLoader imageLoader;
	public boolean closeApplication;
	protected DrawerLayout mDrawerLayout;
	protected RelativeLayout mDrawerRelativeLayout;
	public FrameLayout mDrawerContentFrame;
	protected LinearLayout leftDrawerBottomLayout;
	protected ListView mDrawerList;
	protected ActionBarDrawerToggle mDrawerToggle;

	// protected String[] mDrawerMenuTitles;
	protected CharSequence mDrawerTitle;
	protected CharSequence mTitle;

	private List<Title> mDrawerMenuTitleList;

	// For make fragment
	public Fragment fragment;
	public FragmentManager fragmentManager = getSupportFragmentManager();

	private final int POSITION_SEARCH_FRAGMENT = 0;
	private final int POSITION_KMCLINIC_LIST_FRAGMENT = 1;
	private final int POSITION_HANIKOK_SUPPOTERS = 2;
	private final int POSITION_HANIKOK_HANBANG_INFO = 3;
	private final int POSITION_KOK_LIST_FRAGMENT = 4;

	
	
	// private final int POSITION_SELLUP_CHOICE_FRAGMENT = 6;

	
	// action view 좀 돼라
	//private final int POSITION_Q_AND_A_FRAGMENT = 8;

	private int position = -1;
	private int lastPosition = POSITION_KMCLINIC_LIST_FRAGMENT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_drawer);

		imageLoader = new ImageLoader(this);

		mTitle = mDrawerTitle = getTitle();
		// ********** stringarray 받기 시작
		//setDrawerTitleList();
		setNewDrawerTitleList(); 
		// ********** end

		// ************************************ koo
		// *********************************************************
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		Log.d("koo", "dpi:" + displayMetrics.densityDpi);

		ImageManager.screenWidth = displayMetrics.widthPixels;
		ImageManager.screenHeight = displayMetrics.heightPixels;
		Log.d("koo", "screen size=width:" + ImageManager.screenWidth + ",height:" + ImageManager.screenHeight);
		// ************************************ koo
		// *********************************************************

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerContentFrame = (FrameLayout) findViewById(R.id.content_frame);
		mDrawerRelativeLayout = (RelativeLayout) findViewById(R.id.relativelayout_left_drawer);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		leftDrawerBottomLayout = (LinearLayout) mDrawerRelativeLayout.findViewById(R.id.linearlayout_left_drawer_bottom);
		// set a custom shadow that overlays the main content when the drawer
		// opens
		//mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// Set the adapter for the list view
		// mDrawerList.setAdapter(new
		// ArrayAdapter<String>(this,R.layout.main_drawer_list_item,
		// mDrawerMenuTitles));

		// mDrawerList.setAdapter(new
		// ArrayAdapter<String>(this,R.layout.main_drawer_list_item,
		// mDrawerMenuTitles));
		mDrawerList.setAdapter(new DrawerTitleAdapter(this, R.layout.main_drawer_list_item, mDrawerMenuTitleList));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		setDrawerLeft();

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			firstItem();
			// selectItem(POSITION_KMCLINIC_LIST_FRAGMENT);
			// DselectItem(POSITION_HANIKOK_SUPPOTERS);
		}

		LoadData load = new LoadData(this);

		fragmentManager.addOnBackStackChangedListener(this);

	}

	private void setNewDrawerTitleList() {
		String[] mDrawerItemTitles = getResources().getStringArray(R.array.drawer_menu_title_section_array_new);
		List<Title> titleList = new ArrayList<Title>();
		for(int i=0;i<mDrawerItemTitles.length;i++){
			ItemTitle itemTitle = new ItemTitle();
			itemTitle.setItemTitle(mDrawerItemTitles[i]);
			titleList.add(itemTitle);
		}
		
		mDrawerMenuTitleList = titleList;
	}

	private void firstItem() {
		createFirstKmClinicListFragment(fragmentManager, POSITION_KMCLINIC_LIST_FRAGMENT);
	}

	private String userEmail; // 없으면 null로 명시함
	private boolean isLogin;
	private LinearLayout leftBottomLayout;

	public void setDrawerLeft() {
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		try {
			userEmail = "test@gmail.com";
		} finally {
			if (userEmail == null) {
				Log.d("koo", "userEmail is null");
				isLogin = false;
				beforeLoginLayout = (LinearLayout) inflator.inflate(R.layout.main_drawer_item_bottom_before_login, null);
				leftBottomLayout = beforeLoginLayout;
				Button button = (Button) leftBottomLayout.findViewById(R.id.button_left_drawer_bottom_login_or_register);
				button.setOnClickListener(this);
				leftDrawerBottomLayout.addView(leftBottomLayout);
			} else {
				isLogin = true;
				afterLoginLayout = (LinearLayout) inflator.inflate(R.layout.main_drawer_item_bottom_after_login, null);
				leftBottomLayout = afterLoginLayout;

				// 환경설정 이미지 클릭시 컨피그(환경설정)액티비티로 이동하는 버튼
				ImageButton imgBtn = (ImageButton) leftBottomLayout.findViewById(R.id.imagebutton_left_drawer_bottom_setting);
				imgBtn.setOnClickListener(this);

				TextView nameTextView = (TextView) leftBottomLayout.findViewById(R.id.textview_left_drawer_bottom_name);
				nameTextView.setOnClickListener(this);
				nameTextView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NotoSansKRRegular.otf"));
				String profileName = "윤성수";
				nameTextView.setTextSize(16f);
				nameTextView.setText(profileName);

				ImageView img = null;// 사용자 사진 저장 객체
				img = (ImageView) leftBottomLayout.findViewById(R.id.imageview_left_drawer_bottom_profile);
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face3);
				bitmap = RoundedImageView.getRoundedBitmap(bitmap, 100);
				img.setImageBitmap(bitmap);

				// 사용자 이미지 클릭시 프로필픽쳐 액티비티로 이동하는 버튼
				ImageButton imgProfileBtn = (ImageButton) leftBottomLayout.findViewById(R.id.imageview_left_drawer_bottom_profile);
				imgProfileBtn.setOnClickListener(this);
				leftDrawerBottomLayout.addView(leftBottomLayout);
			}
			leftBottomLayout.setOnClickListener(this);
		}

	}

	private LinearLayout afterLoginLayout;
	private LinearLayout beforeLoginLayout;

	public void setLayoutVisible(boolean isShowAfterLayout, boolean isShowBeforeLayout) {
		if (isShowAfterLayout)
			afterLoginLayout.setVisibility(View.VISIBLE);
		else
			afterLoginLayout.setVisibility(View.GONE);

		if (isShowBeforeLayout)
			beforeLoginLayout.setVisibility(View.VISIBLE);
		else
			beforeLoginLayout.setVisibility(View.GONE);
	}

	private void setDrawerTitleList() {
		String[] mDrawerSectionTitles = getResources().getStringArray(R.array.drawer_menu_title_section_array);

		String[] mDrawerHonikokItemTitles = getResources().getStringArray(R.array.drawer_menu_title_item_array_honikok);
		String[] mDrawerSearchItemTitles = getResources().getStringArray(R.array.drawer_menu_title_item_array_search);
		String[] mDrawerPlusItemTitles = getResources().getStringArray(R.array.drawer_menu_title_item_array_plus);

		List<Title> titleList = new ArrayList<Title>();
		for (int i = 0; i < mDrawerSectionTitles.length; i++) {
			SectionTitle sectionTitle = new SectionTitle();
			sectionTitle.setSectionTitle(mDrawerSectionTitles[i]);
			titleList.add(sectionTitle);

			if (i == 0) {
				for (int j = 0; j < mDrawerHonikokItemTitles.length; j++) {
					ItemTitle itemTitle = new ItemTitle();
					itemTitle.setItemTitle(mDrawerHonikokItemTitles[j]);
					titleList.add(itemTitle);
				}

			} else if (i == 1) {
				for (int j = 0; j < mDrawerSearchItemTitles.length; j++) {
					ItemTitle itemTitle = new ItemTitle();
					itemTitle.setItemTitle(mDrawerSearchItemTitles[j]);
					titleList.add(itemTitle);
				}

			} else if (i == 2) {
				for (int j = 0; j < mDrawerPlusItemTitles.length; j++) {
					ItemTitle itemTitle = new ItemTitle();
					itemTitle.setItemTitle(mDrawerPlusItemTitles[j]);
					titleList.add(itemTitle);
				}

			}
		}

		mDrawerMenuTitleList = titleList;
	}

	// ***************************************** mDrawerToggle 부분
	// *******************************************
	// *********************************************************************************************************
	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	// ******************************************** end
	// ***************************************************

	// **************************************** actionBar 부분
	// ***************************************************

	public SearchView searchView;

	// onCreateOptionsMenu는 말그대로 actionBar에서 옵션메뉴를 추가할때 MenuInflater를 이용해서 생성하는
	// 부분
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.activity_basic_actions, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	// This is called right before the menu is shown, every time it is shown.
	// menu: The options menu as last shown or first initialized by
	// onCreateOptionsMenu().
	// onCreateOptionsMenu가 수행되고 바로 실행되는 메서드라 생각하자
	// 이 메서드는 drawer화면이 전환될 때마다 수행됨 -> 그 이유가 토글에서 invalidateOptionsMenu()를 실행했기
	// 때문임.
	public MenuItem searchItem;

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList); // 안에
		// 들어있는
		// listview가
		// 보여지는지로
		// 판단하네
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerRelativeLayout);
		searchItem = menu.findItem(R.id.action_search);
		menu.findItem(R.id.action_search).setVisible(!drawerOpen); // drawer가
																	// 닫혀있으면
																	// 안보이지
		if (FRAGMENT_TAG.equals("SEARCH"))
			searchItem.setVisible(false); // 만약 혀
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle action buttons
		// 아이템 아이디를 받아서 select가 되었을때 코드를 넣네
		switch (item.getItemId()) {
		case R.id.action_search:
			MenuInflater inflater = getMenuInflater();
			searchItem.setVisible(false);
			selectItem(POSITION_SEARCH_FRAGMENT);
			// create intent to perform web search for this planet
			/*
			 * Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
			 * intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
			 * // catch event that there's no activity to handle intent if
			 * (intent.resolveActivity(getPackageManager()) != null) {
			 * startActivity(intent); } else { Toast.makeText(this,
			 * R.string.app_not_available, Toast.LENGTH_LONG).show(); }
			 */
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	// ************************************************ end
	// *************************************************

	// ************************************** drawer에서 아이템 선택했을 때 수행되는 부분
	// ****************************************
	// *********************************************************************************************************
	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	/**
	 * private final int POSITION_KOK_LIST_FRAGMENT = 2; private final int
	 * POSITION_SELLUP_CHOICE_FRAGMENT = 6; private final int
	 * POSITION_Q_AND_A_FRAGMENT = 9;
	 * 
	 * @param position
	 */
	private void selectItem(int position) {
		
		this.position = position;

			switch (position) {

			case POSITION_KOK_LIST_FRAGMENT:
				// Toast.makeText(this, "준비중입니다. :)",
				// Toast.LENGTH_SHORT).show();
				createMyKokListFragment(fragmentManager, position);
				break;
				/*
			case POSITION_Q_AND_A_FRAGMENT:
				Toast.makeText(this, "준비중입니다. :)", Toast.LENGTH_SHORT).show();
				break;
				*/
			case POSITION_SEARCH_FRAGMENT:
				createSearchFragment(fragmentManager, position);
				break;
			case POSITION_KMCLINIC_LIST_FRAGMENT:
				createKmClinicListFragment(fragmentManager, position);
				break;
			case POSITION_HANIKOK_SUPPOTERS:
				createSupportersFragment(fragmentManager, position);
				break;
			case POSITION_HANIKOK_HANBANG_INFO:
				createHanbangInfoFragment(fragmentManager, position);
				break;
			}
			lastPosition = position;
			Log.d("kim", "FragmentList is " + FragmentTagList.toString());
		

	}

	// *********************************************end***************************************************

	// create fragment
	// ******************************************************************
	private String FRAGMENT_TAG;

	public static ArrayList<String> FragmentTagList = new ArrayList<String>();

	private void createHanbangInfoFragment(FragmentManager fragmentManager, int position) {
		Fragment fragment = HanbangInfoFragment.create(this);
		FRAGMENT_TAG = "HANBANG_INFO";
		FragmentTagList.add(FRAGMENT_TAG);
		fragmentManager.beginTransaction().add(R.id.content_frame, fragment, FRAGMENT_TAG).addToBackStack(null).commit();
		fragment = fragmentManager.findFragmentByTag("CLINIC_LIST");
		if (fragment == null) {

		} else {
			fragment.getView().setVisibility(View.GONE);
		}

		afterFragmentCreate(position);
	}

	private void createSupportersFragment(FragmentManager fragmentManager, int position) {
		Fragment fragment = SuppotersFragment.create(this);
		FRAGMENT_TAG = "SUPPORTERS";
		FragmentTagList.add(FRAGMENT_TAG);
		fragmentManager.beginTransaction().add(R.id.content_frame, fragment, FRAGMENT_TAG).addToBackStack(null).commit();

		fragment = fragmentManager.findFragmentByTag("CLINIC_LIST");

		if (fragment == null) {

		} else {
			fragment.getView().setVisibility(View.GONE);
		}
		afterFragmentCreate(position);
	}

	private void createKmClinicListFragment(FragmentManager fragmentManager, int position) {
		Fragment fragment = ClinicListFragment.create(this);
		FRAGMENT_TAG = "CLINIC_LIST";
		FragmentTagList.add(FRAGMENT_TAG);
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, FRAGMENT_TAG).addToBackStack(null).commit();

		fragment = fragmentManager.findFragmentByTag("CLINIC_LIST");

		if (fragment == null) {

		} else {
			fragment.getView().setVisibility(View.VISIBLE);
		}

		// fragment = fragmentManager.findFragmentByTag("CLINIC_LIST");

		afterFragmentCreate(position);
	}

	// 추가
	private void createMyKokListFragment(FragmentManager fragmentManager, int position) {
		/*
		 * Fragment fragment = MyKokListFragment.create(this); FRAGMENT_TAG =
		 * "CLINIC_LIST";
		 * fragmentManager.beginTransaction().replace(R.id.content_frame,
		 * fragment,FRAGMENT_TAG).addToBackStack(null).commit(); fragment =
		 * fragmentManager.findFragmentByTag("CLINIC_LIST");
		 * fragment.getView().setVisibility(View.VISIBLE);
		 * searchItem.setVisible(false);
		 * 
		 * afterFragmentCreate(position);
		 */

		SharedPreferenceUtil.setData(this, "follow", "check");
		LoadData load;
		load = new LoadData(this);
		ArrayList<KmClinicView> item1 = load.searchClinicListByKeyword("피부");

		Fragment fragment = SearchResultClinicListFragment.create(this, item1);
		FRAGMENT_TAG = "MYKOK_LIST";
		FragmentTagList.add(FRAGMENT_TAG);
		// FragmentManager fragmentManager1 = this.getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.content_frame, fragment, FRAGMENT_TAG).addToBackStack(null).commit();
		// Fragment fragment = MyKokListFragment.create(this);
		fragment = fragmentManager.findFragmentByTag("CLINIC_LIST");

		// fragmentManager.beginTransaction().add(R.id.content_frame,
		// fragment,FRAGMENT_TAG).addToBackStack(null).commit();
		if (fragment == null) {

		} else {
			fragment.getView().setVisibility(View.GONE);
		}

		afterFragmentCreate(position);
	}

	private void createFirstKmClinicListFragment(FragmentManager fragmentManager, int position) {
		Fragment fragment = ClinicListFragment.create(this);
		FRAGMENT_TAG = "FIRST_CLINIC_LIST";
		FragmentTagList.add(FRAGMENT_TAG);
		fragmentManager.beginTransaction().add(R.id.content_frame, fragment, FRAGMENT_TAG).disallowAddToBackStack().commit();

		afterFragmentCreate(position);
	}

	private void createSearchFragment(FragmentManager fragmentManager, int position) {
		Fragment fragment = HardSearchFragment.create(this);
		FRAGMENT_TAG = "SEARCH";
		FragmentTagList.add(FRAGMENT_TAG);
		fragmentManager.beginTransaction().add(R.id.content_frame, fragment, FRAGMENT_TAG).addToBackStack(null).commit();
		fragment = fragmentManager.findFragmentByTag("CLINIC_LIST");
		if (fragment == null) {

		} else {
			fragment.getView().setVisibility(View.GONE);
		}
		searchItem.setVisible(false);

		afterFragmentCreate(position);
	}

	private void afterFragmentCreate(int position) {
		mDrawerList.setItemChecked(position, true);
		ItemTitle title = (ItemTitle) mDrawerMenuTitleList.get(position);
		setTitle(title.getItemTitle());
		mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
	}

	// end **********************************************************

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_left_drawer_bottom_login_or_register:
			Intent intent_register = new Intent(this, UserManagerActivity.class);
			// intent_register.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent_register);
			break;

		case R.id.imageview_left_drawer_bottom_profile:
			Intent intent_profile_picture = new Intent(this, ProfilePictureActivity.class);
			// intent_setting.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			// startActivityForResult(intent_setting,0);
			startActivity(intent_profile_picture);
			break;

		case R.id.textview_left_drawer_bottom_name:
			Intent intentUserProfile = new Intent(this, UserProfileActivity.class);
			intentUserProfile.putExtra(ItDocConstants.EMAIL, "test@gmail.com");
			startActivity(intentUserProfile);
			break;

		case R.id.imagebutton_left_drawer_bottom_setting:
			Log.d("kim", "config_click!");
			Intent intent_setting = new Intent(this, ConfigActivity.class);
			// intent_setting.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			// startActivityForResult(intent_setting,0);
			startActivity(intent_setting);
			break;
		}
	}

	@Override
	public void onBackPressed() {
		Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);

		// FragmentManager manager = getSupportFragmentManager();
		if (isLastFragment) {
			if (!isPressed) {
				Toast.makeText(this, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
				isPressed = true;
				mHandler.sendEmptyMessageDelayed(0, 2000);
			} else {
				super.onBackPressed();
			}
		} else {
			FragmentTagList.remove(FragmentTagList.size() - 1);
			String tag = FragmentTagList.get(FragmentTagList.size() - 1);
			if (FragmentTagList.size() != 0) {
				Log.d("kim", "FragmentList is " + FragmentTagList.toString());
				if (tag.equals("SEARCH")) {
					// fragment =
					// getSupportFragmentManager().findFragmentByTag("CLINIC_LIST");
					// fragment.getView().setVisibility(View.VISIBLE);
					// *****************************actionbar title setting
					// ***********************
					getActionBar().setTitle("한의원 검색하기");
					searchItem.setVisible(false);
					// ****************************************************************************
					/*
					 * fragment = ClinicListFragment.create(this); FRAGMENT_TAG
					 * = "CLINIC_LIST";
					 * fragmentManager.beginTransaction().replace
					 * (R.id.content_frame, fragment,FRAGMENT_TAG).commit();
					 */
				} else if (tag.equals("CLINIC_LIST")) {
					getActionBar().setTitle("둘러보기");
					searchItem.setVisible(true);

				} else if (tag.equals(ItDocConstants.TAG_FRAGMENT_CLINIC_LIST)) {
					// fragment =
					// getSupportFragmentManager().findFragmentByTag("CLINIC_LIST");
					// fragment.getView().setVisibility(View.VISIBLE);
					searchItem.setVisible(true);
				} else if (tag.equals(ItDocConstants.TAG_FRAGMENT_SUPPORT)) {
					// fragment =
					// getSupportFragmentManager().findFragmentByTag("CLINIC_LIST");
					// fragment.getView().setVisibility(View.VISIBLE);
					// *****************************actionbar title setting
					// ***********************
					getActionBar().setTitle("추천 한의원");
					searchItem.setVisible(true);
					// ****************************************************************************
				} else if (tag.equals("HANBANG_INFO")) {
					// fragment =
					// getSupportFragmentManager().findFragmentByTag("CLINIC_LIST");
					// fragment.getView().setVisibility(View.VISIBLE);
					// *****************************actionbar title setting
					// ***********************
					getActionBar().setTitle("한의콕s 한방정보");
					searchItem.setVisible(true);
					// ****************************************************************************
				} else if (tag.equals("MYKOK_LIST")) {
					// fragment =
					// getSupportFragmentManager().findFragmentByTag("CLINIC_LIST");
					// fragment.getView().setVisibility(View.VISIBLE);
					// *****************************actionbar title setting
					// ***********************
					getActionBar().setTitle("나의 콕!리스트");
					searchItem.setVisible(true);
					// ****************************************************************************
				} else if (tag.equals("FIRST_CLINIC_LIST")) {
					getActionBar().setTitle("둘러보기");
					searchItem.setVisible(true);
					fragment = ClinicListFragment.create(this);
					FRAGMENT_TAG = "FIRST_CLINIC_LIST";
					fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, FRAGMENT_TAG).disallowAddToBackStack().commit();
					
				} else if (tag.equals("SEARCH_RESULT")) {
					getActionBar().setTitle("검색결과");
					searchItem.setVisible(false);
				}
			}

			super.onBackPressed();
		}

	}

	private boolean isPressed;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			if (msg.what == 0) {
				isPressed = false;
			} 
		}
	};

	/*
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { super.onActivityResult(requestCode, resultCode
	 * ,data); switch(resultCode){ case Activity.RESULT_OK:
	 * //*****************************actionbar title setting
	 * ***********************
	 * getActionBar().setTitle(R.string.title_activity_main_drawer);
	 * //**********
	 * ****************************************************************** Bundle
	 * bundle = data.getExtras(); boolean isLogin =
	 * bundle.getBoolean("isLogin"); if(isLogin) setLayoutVisible(true, false);
	 * else setLayoutVisible(false, true);
	 * 
	 * break; } }
	 */
	private boolean isLastFragment = true;

	@Override
	public void onBackStackChanged() {
		isLastFragment = false;
		int fragmentCount = fragmentManager.getBackStackEntryCount();

		Log.d("koo", "fragment backstack count:" + fragmentCount);
		if (fragmentCount == 0) {
			isLastFragment = true;
			// Toast.makeText(this, "last fragment", Toast.LENGTH_LONG).show();
			Fragment listFragment = fragmentManager.findFragmentByTag("CLINIC_LIST");
			if (listFragment == null) {

			} else {
				listFragment.getView().setVisibility(View.VISIBLE);
			}
		} else {
			Fragment listFragment = fragmentManager.findFragmentByTag("CLINIC_LIST");

			if (listFragment == null) {

			} else {
				listFragment.getView().setVisibility(View.VISIBLE);
			}

		}

		List<Fragment> fragmentList = fragmentManager.getFragments();
		for (int i = 0; i < fragmentList.size(); i++) {
			Fragment f = fragmentList.get(i);
			if (f != null) {
				if (f == null) {
					Log.d("koo", "fragment null");
				} else {
					Log.d("koo", "fragment tag:" + f.getTag());
				}

			}

		}

	}

}
