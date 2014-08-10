package com.kmbridge.itdoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kmbridge.itdoc.hardcoding.LoadData;

public class FollowerAdapter extends BaseAdapter{


	private LayoutInflater inflator;
	Context context;
	LoadData loadData;
	
	
	
	
	public FollowerAdapter(Context context) {
		super();
		this.context = context;
		
		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadData = new LoadData(context);
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
