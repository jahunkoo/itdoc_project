package com.kmbridge.itdoc.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RelativeLayout;

import com.kmbridge.itdoc.R;

public class KmClinicListFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Log.d("kim","KmClinicListFragment(17) Run very well");
		
		View rootView = inflater.inflate(R.layout.fragment_clinic_list, container, false);
		
		return rootView;
		
	}

}
