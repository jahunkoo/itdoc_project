package com.kmbridge.itdoc.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ClinicListAdapter;

public class KmClinicListFragment extends Fragment{

	private Context context;
	private LayoutInflater mInflater;
	ListView mListView;
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Log.d("kim","KmClinicListFragment(17) Run very well");
		
		View rootView = inflater.inflate(R.layout.fragment_clinic_list, container, false);
		
		ClinicListAdapter clinicListAdapter = new ClinicListAdapter(context);
		
		// mListView.addFooterView(mInflater.inflate(R.layout.footer, null));
		
		mListView = (ListView) rootView.findViewById(R.id.listview_clinic_list);
		mListView.setAdapter(clinicListAdapter);

		return rootView;
		
	}

	public static KmClinicListFragment create(Context context) {
		KmClinicListFragment kmClinicListFragment = new KmClinicListFragment();
		
		kmClinicListFragment.setContext(context);
		
		return kmClinicListFragment;
	}
	
	
}
