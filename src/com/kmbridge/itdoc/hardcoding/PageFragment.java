package com.kmbridge.itdoc.hardcoding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.KmClinicDetailActivity;

public class PageFragment extends Fragment implements OnClickListener{

	private int mPageNumber;
	
	ImageView kmClinicDetail;

	public static PageFragment create(int pageNumber) {
		PageFragment fragment = new PageFragment();
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
		case 0: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item1, container, false);break;
 		case 1: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item2, container, false);break;
		case 2: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item3, container, false);break;
		}

		kmClinicDetail = (ImageView) rootView.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		kmClinicDetail.setOnClickListener(this);
		
		
		//((TextView) rootView.findViewById(R.id.number)).setText(mPageNumber + "");
		return rootView;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.imageview_clinic_list_item_clinicimage:
				if(mPageNumber==0)	callActivity(2);
				else if(mPageNumber==1) callActivity(11);
				else if(mPageNumber==2) callActivity(7);
				break;
		}
	}	
	
	public void callActivity(int clinicNumber)
	{
		Intent intent = new Intent(getActivity(), KmClinicDetailActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("clinicNumber", clinicNumber);
		Log.d("kim3","flagment : "+clinicNumber);
		startActivity(intent);
	}
}
