/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kmbridge.itdoc.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.fragment.JoinFragment;
import com.kmbridge.itdoc.fragment.LoginFragment;
import com.kmbridge.itdoc.fragment.ScreenSlidePageFragment;
import com.kmbridge.itdoc.util.ItDocConstants;


/**
 * Demonstrates a "screen-slide" animation using a {@link ViewPager}. Because {@link ViewPager}
 * automatically plays such an animation when calling {@link ViewPager#setCurrentItem(int)}, there
 * isn't any animation-specific code in this sample.
 *
 * <p>This sample shows a "next" button that advances the user to the next step in a wizard,
 * animating the current screen out (to the left) and the next screen in (from the right). The
 * reverse animation is played when the user presses the "previous" button.</p>
 *
 * @see ScreenSlidePageFragment
 */
public class ScreenSlideActivity extends FragmentActivity implements ScreenSlidePageFragment.CustomOnClickListener{
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        
        ActionBar actionbar = this.getActionBar();
    	actionbar.hide();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
        
        Log.d("koo", "onCreate");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        //item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                
            	/*
            	 * 액션바에 뒤로가기 버튼 눌렀을때 이전 액티비티로 넘어가는 부분
            	 */
            	//NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment} objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        

        public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private String TAG_FRAGMENT;
    
    // ScreenSlidePageFragment.CustomOnClickListener의 구현
	@Override
	public void onClicked(int id) {

		
		 switch(id){
	        case R.id.button_fragment_screen_slide_register:
	        	joinLayoutElement();
	    		break;
	        case R.id.button_fragment_screen_slide_login:
	        	loginLayoutElement();
	            break;
	        }
	    }

	private void joinLayoutElement() {
		Log.d("debug","check2");
		Fragment joinFragment = new JoinFragment();
		TAG_FRAGMENT = ItDocConstants.TAG_FRAGMENT_JOIN;
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.linearLayout_screen_slide_for_join, joinFragment,TAG_FRAGMENT).addToBackStack(null).commit();
		findViewById(R.id.linearLayout_pager).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_screen_slide_for_join).setVisibility(View.VISIBLE);
		findViewById(R.id.linearLayout_screen_slide_for_login).setVisibility(View.GONE);
	}
	
	private void loginLayoutElement() {
		Fragment loginFragment = new LoginFragment();
		TAG_FRAGMENT = ItDocConstants.TAG_FRAGMENT_JOIN;
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.linearLayout_screen_slide_for_login, loginFragment,TAG_FRAGMENT).addToBackStack(null).commit();
		findViewById(R.id.linearLayout_pager).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_screen_slide_for_join).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_screen_slide_for_login).setVisibility(View.VISIBLE);
		
	}
	
	@Override
	public void onBackPressed() {
		ActionBar actionbar = this.getActionBar();
    	actionbar.hide();
		
		findViewById(R.id.linearLayout_pager).setVisibility(View.VISIBLE);
		findViewById(R.id.linearLayout_screen_slide_for_join).setVisibility(View.GONE);
		findViewById(R.id.linearLayout_screen_slide_for_login).setVisibility(View.GONE);
		
		//밑에 코드에 의해서 실제 백버튼 기능이 수행됨.
		super.onBackPressed();
	}
}
