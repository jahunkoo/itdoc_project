package com.kmbridge.itdoc.hardcoding;

import com.kmbridge.itdoc.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Theme2PageFragment extends Fragment {

	private int mPageNumber;

	public static Theme2PageFragment create(int pageNumber) {
		Theme2PageFragment fragment = new Theme2PageFragment();
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
		case 0: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item4_1, container, false);break;
		case 1: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item4_2, container, false);break;
		}
		
		
		//((TextView) rootView.findViewById(R.id.number)).setText(mPageNumber + "");
		return rootView;
	}
	
}
