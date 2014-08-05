package com.kmbridge.itdoc.activity;

import java.util.ArrayList;
import java.util.List;

import lazyList.ImageLoader;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.DrawerTitleAdapter;
import com.kmbridge.itdoc.dto.ItemTitle;
import com.kmbridge.itdoc.dto.SectionTitle;
import com.kmbridge.itdoc.dto.Title;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.fragment.KmClinicListFragment;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class MainDrawerActivity extends FragmentActivity implements OnClickListener{

	public ImageLoader imageLoader;
	
	protected DrawerLayout mDrawerLayout;
	protected RelativeLayout mDrawerRelativeLayout;
	protected LinearLayout leftDrawerBottomLayout;
	protected ListView mDrawerList;
	protected ActionBarDrawerToggle mDrawerToggle;

	//protected String[] mDrawerMenuTitles;
	protected CharSequence mDrawerTitle;
	protected CharSequence mTitle;

	private List<Title> mDrawerMenuTitleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_drawer);
		
		imageLoader = new ImageLoader(this);

		mTitle = mDrawerTitle = getTitle();
		// ********** stringarray 받기 시작 
		//mDrawerMenuTitles = getResources().getStringArray(R.array.drawer_menu_title_array);
		setDrawerTitleList();
		// ********** end

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerRelativeLayout = (RelativeLayout) findViewById(R.id.relativelayout_left_drawer);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		leftDrawerBottomLayout = (LinearLayout) mDrawerRelativeLayout.findViewById(R.id.linearlayout_left_drawer_bottom);
		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// Set the adapter for the list view
		//mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.main_drawer_list_item, mDrawerMenuTitles));
		mDrawerList.setAdapter(new DrawerTitleAdapter(this,R.layout.main_drawer_list_item, mDrawerMenuTitleList));
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
				// getActionBar().setTitle(mDrawerTitle);
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			selectItem(POSITION_KMCLINIC_LIST_FRAGMENT);
		}

	}
	
	private String userEmail;	//없으면 null로 명시함
	private boolean isLogin;
	private LinearLayout leftBottomLayout;

	public void setDrawerLeft() {
		
		
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				try {
					userEmail = SharedPreferenceUtil.getData(this, ItDocConstants.SHARED_KEY_EMAIL);
				} catch (RecordNotFoundException e) {
					userEmail = null;
					e.printStackTrace();
				}finally{
					if(userEmail == null){
						Log.d("koo", "userEmail is null");
						isLogin = false;
						beforeLoginLayout = (LinearLayout) inflator.inflate(R.layout.main_drawer_item_bottom_before_login , null);
						leftBottomLayout = beforeLoginLayout;
						Button button = (Button) leftBottomLayout .findViewById(R.id.button_left_drawer_bottom_login_or_register);
						button.setOnClickListener(this);
						leftDrawerBottomLayout.addView(leftBottomLayout);
					}else{
						isLogin = true;
						afterLoginLayout = (LinearLayout) inflator.inflate(R.layout.main_drawer_item_bottom_after_login , null);
						leftBottomLayout= afterLoginLayout;
						
						//환경설정 이미지 클릭시 컨피그(환경설정)액티비티로 이동하는 버튼
						ImageButton imgBtn = (ImageButton) leftBottomLayout.findViewById(R.id.imagebutton_left_drawer_bottom_setting);
						imgBtn.setOnClickListener(this);
						
						TextView nameTextView = (TextView) leftBottomLayout.findViewById(R.id.textview_left_drawer_bottom_name);
						nameTextView.setOnClickListener(this);
						String profileName = null;
						try {
							profileName = SharedPreferenceUtil.getData(this, ItDocConstants.SHARED_KEY_NAME);
						} catch (RecordNotFoundException e) {
							e.printStackTrace();
						}
						
						nameTextView.setText(profileName);
						
						ImageView img = null;//사용자 사진 저장 객체
						img = (ImageView) leftBottomLayout.findViewById (R.id.imageview_left_drawer_bottom_profile);
						
						//로그인시 사용자 사진 달기
						//String url = "http://yss159.cafe24.com:8080/ItDocImgServer/getPicture?picturePath=" + clinicListItem.picturepath +"&objectType=1";
						
						String picturePath = null;
						try {
							picturePath = SharedPreferenceUtil.getData(this, ItDocConstants.SHARED_KEY_PICTURE_URL);
							Log.d("kim3","Main Drawer :"+picturePath);
						} catch (RecordNotFoundException e1) {
							e1.printStackTrace();
						}
						
						String url  = "http://yss159.cafe24.com:8080/ItDocImgServer/getPicture?picturePath=" + picturePath + "&objectType=1";
						
						Log.d("kim3","url :"+url);
						
					
						try {
							imageLoader.DisplayImage(url, img);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//사용자 이미지 클릭시 프로필픽쳐 액티비티로 이동하는 버튼
						ImageButton imgProfileBtn = (ImageButton) leftBottomLayout.findViewById(R.id.imageview_left_drawer_bottom_profile);
						imgProfileBtn.setOnClickListener(this);
						leftDrawerBottomLayout.addView(leftBottomLayout);
					}
					leftBottomLayout.setOnClickListener(this);
				}
	}
	private LinearLayout afterLoginLayout;
	private LinearLayout beforeLoginLayout;
	public void setLayoutVisible(boolean isShowAfterLayout ,boolean isShowBeforeLayout){
		if(isShowAfterLayout)	afterLoginLayout.setVisibility(View.VISIBLE);
		else 					afterLoginLayout.setVisibility(View.GONE);
		
		if(isShowBeforeLayout)	beforeLoginLayout.setVisibility(View.VISIBLE);
		else					beforeLoginLayout.setVisibility(View.GONE);
	}
	
	private void setDrawerTitleList(){
		String[] mDrawerSectionTitles = getResources().getStringArray(
				R.array.drawer_menu_title_section_array);
		String[] mDrawerSearchItemTitles = getResources().getStringArray(
				R.array.drawer_menu_title_item_array_search);
		String[] mDrawerPlusItemTitles = getResources().getStringArray(
				R.array.drawer_menu_title_item_array_plus);
		
		List<Title> titleList = new ArrayList<Title>();
		for (int i = 0; i < mDrawerSectionTitles.length; i++) {
			SectionTitle sectionTitle = new SectionTitle();
			sectionTitle.setSectionTitle(mDrawerSectionTitles[i]);
			titleList.add(sectionTitle);

			
			if (i == 0) {
				for (int j = 0; j < mDrawerSearchItemTitles.length; j++) {
					ItemTitle itemTitle = new ItemTitle();
					itemTitle.setItemTitle(mDrawerSearchItemTitles[j]);
					titleList.add(itemTitle);
				}
			}else if (i == 1) {
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
	// *********************************************************************************************************
	// onCreateOptionsMenu는 말그대로 actionBar에서 옵션메뉴를 추가할때 MenuInflater를 이용해서 생성하는
	// 부분
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_basic_drawer_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	// This is called right before the menu is shown, every time it is shown.
	// menu: The options menu as last shown or first initialized by
	// onCreateOptionsMenu().
	// onCreateOptionsMenu가 수행되고 바로 실행되는 메서드라 생각하자
	// 이 메서드는 drawer화면이 전환될 때마다 수행됨 -> 그 이유가 토글에서 invalidateOptionsMenu()를 실행했기
	// 때문임.
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		//boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList); // 안에 들어있는
																		// listview가
																		// 보여지는지로
																		// 판단하네
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerRelativeLayout);
		menu.findItem(R.id.action_search).setVisible(!drawerOpen); // drawer가
																	// 닫혀있으면
																	// 안보이지
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
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private final int POSITION_KMCLINIC_LIST_FRAGMENT = 2;

	private void selectItem(int position) {

		Fragment fragment;
		FragmentManager fragmentManager = getSupportFragmentManager();
		Bundle args = new Bundle();

		switch (position) {
		case POSITION_KMCLINIC_LIST_FRAGMENT:
			createKmClinicListFragment(fragmentManager, position);
			break;

		/*default:

			// update the main content by replacing fragments
			fragment = new PlanetFragment();

			args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
			fragment.setArguments(args);

			fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			setTitle(mDrawerMenuTitles[position]);

			mDrawerLayout.closeDrawer(mDrawerList);
			*/
		}
	}

	// *********************************************end***************************************************
	private void createKmClinicListFragment(FragmentManager fragmentManager,
			int position) {
		Fragment fragment = KmClinicListFragment.create(this);
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		mDrawerList.setItemChecked(position, true);
		ItemTitle title = (ItemTitle) mDrawerMenuTitleList.get(position); 
		setTitle(title.getItemTitle());
		//mDrawerLayout.closeDrawer(mDrawerList);
		mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId())
		{
			case R.id.button_left_drawer_bottom_login_or_register:
				Intent intent_register = new Intent(this,UserManagerActivity.class);
				//intent_register.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent_register);
				break;
				
			case R.id.imageview_left_drawer_bottom_profile:
				Intent intent_profile_picture = new Intent(this,ProfilePictureActivity.class);
				//intent_setting.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				//startActivityForResult(intent_setting,0);
				startActivity(intent_profile_picture);
				break;
				
			case R.id.textview_left_drawer_bottom_name:
				Intent intentUserProfile = new Intent(this,UserProfileActivity.class);
				intentUserProfile.putExtra(ItDocConstants.EMAIL, ItDocConstants.SHARED_KEY_EMAIL);
				startActivity(intentUserProfile);
				break;
				
			case R.id.imagebutton_left_drawer_bottom_setting:
				Log.d("kim","config_click!");
				Intent intent_setting = new Intent(this,ConfigActivity.class);
				//intent_setting.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				//startActivityForResult(intent_setting,0);
				startActivity(intent_setting);
				break;
				
		}
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode ,data);
		switch(resultCode){
		case Activity.RESULT_OK: 
			//*****************************actionbar title setting ***********************
			getActionBar().setTitle(R.string.title_activity_main_drawer);
			//****************************************************************************
			Bundle bundle = data.getExtras();
			boolean isLogin = bundle.getBoolean("isLogin");
			if(isLogin)	setLayoutVisible(true, false);
			else		setLayoutVisible(false, true);
			
			break;
		}
	}
	

}
