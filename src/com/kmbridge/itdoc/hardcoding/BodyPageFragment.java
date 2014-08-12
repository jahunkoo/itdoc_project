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

public class BodyPageFragment extends Fragment implements OnClickListener{

	private int mPageNumber;
	ImageView kmClinicDetailRecomand;
	
	public static BodyPageFragment create(int pageNumber) {
		BodyPageFragment fragment = new BodyPageFragment();
		Bundle args = new Bundle();
		args.putInt("page", pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt("page");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = null;

		switch (mPageNumber) {
		case 0:	rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item2_1, container, false);break;
		case 1:	rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item2_2, container, false); break;
		}
		
		kmClinicDetailRecomand = (ImageView) rootView.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		kmClinicDetailRecomand.setOnClickListener(this);
		// ((TextView) rootView.findViewById(R.id.number)).setText(mPageNumber +
		// "");
		return rootView;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.imageview_clinic_list_item_clinicimage:
				if(mPageNumber==0)	callActivity(30);
				else if(mPageNumber==1) callActivity(14);
				break;
		}
	}	

	
	public void callActivity(int clinicNumber) {
		Log.d("kim5", "flagment : " + clinicNumber);
		Intent intent = new Intent(getActivity(), KmClinicDetailActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("clinicNumber", clinicNumber);
		Log.d("kim5", "flagment : " + clinicNumber);
		startActivity(intent);
	}

}
