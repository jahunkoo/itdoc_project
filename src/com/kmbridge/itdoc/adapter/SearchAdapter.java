package com.kmbridge.itdoc.adapter;

import com.kmbridge.itdoc.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter{

	Context context;
	String[] recentSearch = null;
	private LayoutInflater inflator;
	
	
	public SearchAdapter(Context context,String[] recentSearch) {
		super();
		
		this.context = context;
		this.recentSearch = recentSearch;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return recentSearch.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return recentSearch[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;

		TextView recent;
		
		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if (view == null) {
			view = inflator.inflate(R.layout.search_list_item, parent,false);
			view.setTag((Integer)position);
		}

		recent = (TextView) view.findViewById(R.id.textview_search_list_item);
		
		String recentKeyword = recentSearch[recentSearch.length - position - 1];
		
		recent.setText(recentKeyword);
		
		return view;
	}

}
