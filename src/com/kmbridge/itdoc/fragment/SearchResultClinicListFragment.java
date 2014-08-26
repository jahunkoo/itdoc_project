package com.kmbridge.itdoc.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ClinicListAdapter;
import com.kmbridge.itdoc.adapter.HardSearchResultAdapter;
import com.kmbridge.itdoc.adapter.KooJahunSearchResultAdapter;
import com.kmbridge.itdoc.adapter.SearchResultAdapter;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.util.RecycleUtil;

public class SearchResultClinicListFragment extends Fragment {

	private Context context;
	private LayoutInflater mInflater;
	ListView mListView;
	ClinicListAdapter clinicListAdapter;
	private ArrayList<KmClinicView> kmClinicViewList;

	private boolean calledType = false;
	
	public void setContext(Context context) {
		this.context = context;
	}

	public void setClinicList(ArrayList<KmClinicView> kmClinicViewList) {
		this.kmClinicViewList = kmClinicViewList;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		String email = "test@gmail.com";
		/*
		 * try { email = new SharedPreferenceUtil().getData(context,
		 * ItDocConstants.SHARED_KEY_EMAIL);
		 * 
		 * } catch (RecordNotFoundException e) { email = null;
		 * e.printStackTrace(); }
		 */
		View rootView = inflater.inflate(R.layout.fragment_clinic_list, container, false);
		HardSearchResultAdapter searchResultAdapter = new HardSearchResultAdapter(email, context, kmClinicViewList);
		//SearchResultAdapter searchResultAdapter = new SearchResultAdapter(email, context, kmClinicViewList);
		//KooJahunSearchResultAdapter searchResultAdapter = new KooJahunSearchResultAdapter(email, context, kmClinicViewList);
		mListView = (ListView) rootView.findViewById(R.id.listview_clinic_list);
		mListView.setAdapter(searchResultAdapter);

		return rootView;
	}
	
	@Override
	public void onDestroy() {
		RecycleUtil.recursiveRecycle(mListView);
		Log.d("kim","removeAllViews");
		
		if(calledType) {
			getActivity().getActionBar().setTitle("둘러보기");
		}
		
		super.onDestroy();
	}

	public static SearchResultClinicListFragment create(Context context, ArrayList<KmClinicView> kmClinicViewList) {
		SearchResultClinicListFragment searchResultClinicListFragment = new SearchResultClinicListFragment();
		searchResultClinicListFragment.setContext(context);
		searchResultClinicListFragment.setClinicList(kmClinicViewList);
		return searchResultClinicListFragment;
	}

	/**
	 * 
	 * @param context
	 * @param kmClinicViewList
	 * @param calledType true면 둘러보기에서 온것, false면 검색화면에서 온것
	 * @return
	 */
	public static SearchResultClinicListFragment create(Context context, ArrayList<KmClinicView> kmClinicViewList,boolean calledType) {
		SearchResultClinicListFragment searchResultClinicListFragment = new SearchResultClinicListFragment();
		searchResultClinicListFragment.setContext(context);
		searchResultClinicListFragment.setClinicList(kmClinicViewList);
		searchResultClinicListFragment.setCalledType(calledType);
		return searchResultClinicListFragment;
	}
	
	public void setCalledType(boolean calledType) {
		this.calledType = calledType;
	}
	
}

