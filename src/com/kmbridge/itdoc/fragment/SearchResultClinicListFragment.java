package com.kmbridge.itdoc.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ClinicListAdapter;
import com.kmbridge.itdoc.adapter.SearchResultAdapter;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class SearchResultClinicListFragment extends Fragment {

	private Context context;
	private LayoutInflater mInflater;
	ListView mListView;
	ClinicListAdapter clinicListAdapter;
	private ArrayList<KmClinicView> kmClinicViewList;

	public void setContext(Context context) {
		this.context = context;
	}
	public void setClinicList(ArrayList<KmClinicView> kmClinicViewList) {
		this.kmClinicViewList = kmClinicViewList;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		String email;

		try {
			email = new SharedPreferenceUtil().getData(context, ItDocConstants.SHARED_KEY_EMAIL);
		} catch (RecordNotFoundException e) {
			email = null;
			e.printStackTrace();
		}

		View rootView = inflater.inflate(R.layout.fragment_clinic_list, container, false);

		SearchResultAdapter searchResultAdapter = new SearchResultAdapter(email, context, kmClinicViewList);
		
		mListView = (ListView) rootView.findViewById(R.id.listview_clinic_list);
		mListView.setAdapter(searchResultAdapter);

		return rootView;

	}

	public static SearchResultClinicListFragment create(Context context, ArrayList<KmClinicView> kmClinicViewList) {
		SearchResultClinicListFragment searchResultClinicListFragment = new SearchResultClinicListFragment();

		searchResultClinicListFragment.setContext(context);
		searchResultClinicListFragment.setClinicList(kmClinicViewList);
		
		return searchResultClinicListFragment;
	}


}
