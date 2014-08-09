package com.kmbridge.itdoc.hardcoding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.KmClinicDetailActivity;
import com.kmbridge.itdoc.activity.KmClinicMoreDoctorActivity;

public class ClinicListFragment extends Fragment {

	private Context context;
	private ViewPager mViewPager1;
	private ViewPager mViewPager2;
	private ViewPager mViewPager3;
	private ViewPager mViewPager4;
	
	private PagerAdapter mPagerAdapterDiet;
	private PagerAdapter mPagerAdapterGyujung;
	private PagerAdapter mPagerAdapterTheme1;
	private PagerAdapter mPagerAdapterTheme2;
	
	public static ClinicListFragment create(Context context) {
		ClinicListFragment clinicListFragment = new ClinicListFragment();
		clinicListFragment.setContext(context);
		return clinicListFragment;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.hard_fragment_clinic_list, container, false);
		mViewPager1 = (ViewPager) rootView.findViewById(R.id.hard_list_clinic_pager1);
		
		mViewPager2 = (ViewPager) rootView.findViewById(R.id.hard_list_clinic_pager2);
		mViewPager3 = (ViewPager) rootView.findViewById(R.id.hard_list_clinic_pager3);
		mViewPager4 = (ViewPager) rootView.findViewById(R.id.hard_list_clinic_pager4);
		
		mPagerAdapterDiet = new PagerAdapterDiet(getFragmentManager());
		mViewPager1.setAdapter(mPagerAdapterDiet);
		
		mPagerAdapterGyujung = new PagerAdapterGyujung(getFragmentManager());
		mViewPager2.setAdapter(mPagerAdapterGyujung);
		
		mPagerAdapterTheme1 = new PagerAdapterTheme1(getFragmentManager());
		mViewPager3.setAdapter(mPagerAdapterTheme1);
		
		mPagerAdapterTheme2 = new PagerAdapterTheme2(getFragmentManager());
		mViewPager4.setAdapter(mPagerAdapterTheme2);
		
		return rootView;
	}

	private class PagerAdapterDiet extends FragmentStatePagerAdapter {

		public PagerAdapterDiet(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
                        // 해당하는 page의 Fragment를 생성합니다.
			Log.d("koo", "PagerAdapter getItem 1");		
			return PageFragment.create(position);
		}

		@Override
		public int getCount() {
			return 3;  // 총 5개의 page를 보여줍니다.
		}

	}
	
	
	private class PagerAdapterGyujung extends FragmentStatePagerAdapter {

		public PagerAdapterGyujung(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
                        // 해당하는 page의 Fragment를 생성합니다.
			Log.d("koo", "PagerAdapter getItem 1");		
			return BodyPageFragment.create(position);
		}

		@Override
		public int getCount() {
			return 2;  // 총 5개의 page를 보여줍니다.
		}

	}
	
	
	// 테마1
	private class PagerAdapterTheme1 extends FragmentStatePagerAdapter {

		public PagerAdapterTheme1(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
                        // 해당하는 page의 Fragment를 생성합니다.
			Log.d("koo", "PagerAdapter getItem 1");		
			return Theme1PageFragment.create(position);
		}

		@Override
		public int getCount() {
			return 2;  // 총 5개의 page를 보여줍니다.
		}

	}
	
	private class PagerAdapterTheme2 extends FragmentStatePagerAdapter {

		public PagerAdapterTheme2(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
                        // 해당하는 page의 Fragment를 생성합니다.
			Log.d("koo", "PagerAdapter getItem 1");		
			return Theme2PageFragment.create(position);
		}

		@Override
		public int getCount() {
			return 2;  // 총 5개의 page를 보여줍니다.
		}

	}
	

}
