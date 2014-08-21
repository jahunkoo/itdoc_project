package com.kmbridge.itdoc.hardcoding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.HanbangInfoActivity;
import com.kmbridge.itdoc.activity.MainDrawerActivity;
import com.kmbridge.itdoc.image.ImageManager;

public class Theme2PageFragment extends Fragment implements OnClickListener{

	private int mPageNumber;
	ImageView kmClinicTheme2;
	
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
		case 0: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item4_1, container, false); break;
				//Bitmap bitmap = ImageManager.decodeSampledBitmapFromResource(getResources(), R.drawable.hanbang_info_up_baby, ImageManager.screenWidth);
				//imgView.setImageBitmap(bitmap);
		case 1: rootView = (ViewGroup) inflater.inflate(R.layout.clinic_list_item4_2, container, false);break;
		}
		
		kmClinicTheme2 = (ImageView) rootView.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		kmClinicTheme2.setOnClickListener(this);
		
		//((TextView) rootView.findViewById(R.id.number)).setText(mPageNumber + "");
		return rootView;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.imageview_clinic_list_item_clinicimage:
				if(mPageNumber==0)
				{
					Log.d("kim5","testKim");
					Intent intent = new Intent(getActivity(),HanbangInfoActivity.class);
					startActivity(intent);
				}
				break;
		}
	}	
	
}
