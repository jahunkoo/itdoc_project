package com.kmbridge.itdoc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ClinicListAdapter;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class KmClinicListFragment extends Fragment {

	private Context context;
	private LayoutInflater mInflater;
	ListView mListView;
	ClinicListAdapter clinicListAdapter;

	public void setContext(Context context) {
		this.context = context;
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

		clinicListAdapter = new ClinicListAdapter(context, email);

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
