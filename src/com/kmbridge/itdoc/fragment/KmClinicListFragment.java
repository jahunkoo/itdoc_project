package com.kmbridge.itdoc.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.ClinicListAdapter;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class KmClinicListFragment extends Fragment implements OnItemClickListener, OnClickListener {

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

		email = "chicken@gmail.com";

		clinicListAdapter = new ClinicListAdapter(context, email);

		// mListView.addFooterView(mInflater.inflate(R.layout.footer, null));

		mListView = (ListView) rootView.findViewById(R.id.listview_clinic_list);
		mListView.setAdapter(clinicListAdapter);
		mListView.setOnItemClickListener(this);
		return rootView;

	}

	public static KmClinicListFragment create(Context context) {
		KmClinicListFragment kmClinicListFragment = new KmClinicListFragment();

		kmClinicListFragment.setContext(context);

		return kmClinicListFragment;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.d("kim", "0");
		int type = clinicListAdapter.getItem(position).getType();
		int Id = clinicListAdapter.getItem(position).getId();
		String email = clinicListAdapter.email;

		Log.d("kim", "1");
		if (type == 0) {
			Log.d("kim", "2");
			ConnectionBridge conn = new ConnectionBridge();
			ArrayList<String> result = conn.insertKmClinicFollow("insertKmClinicFollow", context, email, Id);

			if (result.get(0) == "success") {
				Log.d("kim", "3");
				ImageView img = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_follow_img);
				img.setImageResource(R.drawable.follow);
			}

		} else {

		}

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

}
