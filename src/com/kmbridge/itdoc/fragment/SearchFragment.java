package com.kmbridge.itdoc.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.SearchAdapter;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.hardcoding.LoadData;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class SearchFragment extends Fragment implements OnClickListener, OnItemClickListener {

	ArrayList<String> allKeywordsList = new ArrayList<String>();
	private Context context;

	AutoCompleteTextView search;
	Button searchButton;

	ListView recentListView;

	String[] keywords1 = null;
	SearchAdapter searchAdapter;

	SharedPreferenceUtil share = new SharedPreferenceUtil();

	public static final String RECENT_KEYWORD = "recentKeyword";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_search, container, false);
		
		share.setData(context, RECENT_KEYWORD, "한의콕" + ",");
		
		ConnectionBridge keywordConnection = new ConnectionBridge();

		search = (AutoCompleteTextView) rootView.findViewById(R.id.autocomplete_textview_fragment_search);
		searchButton = (Button) rootView.findViewById(R.id.button_fragment_search);
		recentListView = (ListView) rootView.findViewById(R.id.listview_fragment_search_recent_search);

		searchButton.setOnClickListener(this);

		allKeywordsList = keywordConnection.getAllKeywords("getAllKeywords", context);
		ArrayAdapter<String> keywordsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, allKeywordsList);
		search.setAdapter(keywordsAdapter);

		try {
			keywords1 = share.getData(context, RECENT_KEYWORD).split(",");
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		searchAdapter = new SearchAdapter(context, keywords1);
		recentListView.setAdapter(searchAdapter);
		recentListView.setOnItemClickListener(this);

		return rootView;

	}

	public static SearchFragment create(Context context) {
		SearchFragment searchFragment = new SearchFragment();

		searchFragment.setContext(context);

		return searchFragment;
	}

	private void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {

		String keywords;

		switch (v.getId()) {
		case R.id.button_fragment_search:
			String text = search.getText().toString();
			
			LoadData load = new LoadData(getActivity());
			ConnectionBridge conn = new ConnectionBridge();
			ArrayList<KmClinicView> kmClinicViewList = conn.getAllKmClinicListKeyword("getKmClinicListByKeyword", context, text);
			kmClinicViewList.toString();

			if (share.isExist(context, RECENT_KEYWORD)) {
				try {
					keywords = share.getData(context, RECENT_KEYWORD);

					keywords = keywords + text + ",";

					share.setData(context, RECENT_KEYWORD, keywords);

				} catch (RecordNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				share.setData(context, RECENT_KEYWORD, text + ",");
			}

			try {
				keywords1 = share.getData(context, RECENT_KEYWORD).split(",");
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
			
			Fragment fragment = SearchResultClinicListFragment.create(context, kmClinicViewList);
			FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

			// 검색 버튼을 누르면 바로 추가가 되도록 하는 코드 이지만
			// 나중에는 어차피 검색 버튼을 누르면 화면이 바로 넘어가서 검색 결과를 보여 줄 예정이므로, 상관 없음.
			// searchAdapter = new SearchAdapter(context, keywords1);
			// recentListView.setAdapter(searchAdapter);

			break;

		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		Log.d("kim", "itemClick item is " + searchAdapter.getItem(position));

		search.setText((CharSequence) searchAdapter.getItem(keywords1.length - position - 1));

	}

}
