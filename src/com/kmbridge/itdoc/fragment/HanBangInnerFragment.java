package com.kmbridge.itdoc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RelativeLayout;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.KmClinicDetailActivity;

public class HanBangInnerFragment extends Fragment implements OnClickListener{

	RelativeLayout relative_hanbang_info;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.hanbang_info_item, container, false);

		relative_hanbang_info = (RelativeLayout) v.findViewById(R.id.relative_layout_hanbang_info);
		relative_hanbang_info.setOnClickListener(this);
		return v;
	}

	

	@Override
	public void onClick(View v) {

		switch (v.getId())
		{
		case R.id.relative_layout_hanbang_info:
			Intent intent = new Intent(getActivity(), KmClinicDetailActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("clinicNumber", 30);
			startActivity(intent);
			break;
		}

	}

}
