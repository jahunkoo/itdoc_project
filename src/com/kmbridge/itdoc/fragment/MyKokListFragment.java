package com.kmbridge.itdoc.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.hardcoding.LoadData;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class MyKokListFragment extends Fragment {

	private Context context;

	LoadData load;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_my_kok_list, container, false);
		
		SharedPreferenceUtil.setData(context, "follow", "check");
		
		load = new LoadData(context);
		
		ArrayList<KmClinicView> item1 = load.searchClinicListByKeyword("피부");
		serachResult(item1);
		
		return rootView;
		
	}
	
	public void serachResult(ArrayList<KmClinicView> kmClinicViewList)
	{
		Fragment fragment = SearchResultClinicListFragment.create(context, kmClinicViewList);
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.beginTransaction().add(R.id.content_frame, fragment,ItDocConstants.TAG_FRAGMENT_CLINIC_LIST).addToBackStack(null).commit();
	}
	
	public static MyKokListFragment create(Context context) {
		MyKokListFragment myKokFragment = new MyKokListFragment();
		
		myKokFragment.setContext(context);

		return myKokFragment;
	}

	private void setContext(Context context) {
		this.context = context;
	}
	

}
