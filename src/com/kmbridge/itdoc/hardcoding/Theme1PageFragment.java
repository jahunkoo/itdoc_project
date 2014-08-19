package com.kmbridge.itdoc.hardcoding;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.KmClinicDetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Theme1PageFragment extends Fragment implements OnClickListener{

	private int mPageNumber;
	ImageView kmClinicTheme;
	
	public static Theme1PageFragment create(int pageNumber) {
		Theme1PageFragment fragment = new Theme1PageFragment();
		Bundle args = new Bundle();
		args.putInt("page", pageNumber);
		fragment.setArguments(args);
		Log.d("koo", "PageFragment create 1");	
		return fragment;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt("page");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView  = null;
		
		switch(mPageNumber){
		case 0: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item3_1, container, false);break;
		case 1: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item3_2, container, false);break;
		}
		
		kmClinicTheme = (ImageView) rootView.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		kmClinicTheme.setOnClickListener(this);
		
		//((TextView) rootView.findViewById(R.id.number)).setText(mPageNumber + "");
		return rootView;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.imageview_clinic_list_item_clinicimage:
				if(mPageNumber==0)	callActivity("피부");
				else if(mPageNumber==1) callActivity("아이");
				break;
		}
	}	
	
	public void callActivity(String keyword) {
		//Log.d("kim5", "flagment : " + clinicNumber);
		Intent intent = new Intent(getActivity(), HardSearchFragment.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("keyword", keyword);
		//Log.d("kim5", "flagment : " + clinicNumber);
		startActivity(intent);
	}
	
}
