package com.kmbridge.itdoc.hardcoding;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.fragment.SearchResultClinicListFragment;
import com.kmbridge.itdoc.util.ItDocConstants;

public class Theme1PageFragment extends Fragment implements OnClickListener{

	Context context;
	
	private int mPageNumber;
	ImageView kmClinicTheme;
	FragmentManager fragmentManager;
	
	public static Theme1PageFragment create(Context context, int pageNumber) {
		Theme1PageFragment fragment = new Theme1PageFragment();
		Bundle args = new Bundle();
		fragment.setContext(context);
		args.putInt("page", pageNumber);
		fragment.setArguments(args);
		Log.d("koo", "PageFragment create 1");	
		return fragment;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt("page");
		
		fragmentManager = getActivity().getSupportFragmentManager();
		
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
		LoadData loadData = new LoadData(context);
		
		ArrayList<KmClinicView> kmClinicViewList = loadData.searchClinicListByKeyword(keyword);
		
		Fragment fragment = SearchResultClinicListFragment.create(context, kmClinicViewList,true);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.content_frame, fragment,ItDocConstants.TAG_FRAGMENT_CLINIC_LIST).addToBackStack(null).commit();
		getActivity().getActionBar().setTitle(keyword + " 검색 결과");
		
		// InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
	}
	

	private void setContext (Context context) {
		this.context = context;
	}
	
	
}
