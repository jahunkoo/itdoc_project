package com.kmbridge.itdoc.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.ItemTitle;
import com.kmbridge.itdoc.dto.SectionTitle;
import com.kmbridge.itdoc.dto.Title;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class DrawerTitleAdapter extends ArrayAdapter<Title> {

	private View view;
	private List<Title> titleList;
	private LayoutInflater inflator;
	private int layoutResId; 
	
	public DrawerTitleAdapter(Context context,int resource,List<Title> titleList) {
		super(context, resource, titleList);
		this.titleList =titleList;  
		this.layoutResId = resource;
		inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		view = convertView;
		if(view == null) view = inflator.inflate(layoutResId, null);
			
		//******************************** drawer 하단 레이아웃에 새로운 레이아웃 추가 ***********

		Title title = titleList.get(position);
		if(title!=null){
			if(title.isSection()){
				SectionTitle sectionTitle = (SectionTitle) title;
				TextView textView = (TextView) view.findViewById(R.id.textview_main_drawer_item);
				textView.setText(sectionTitle.getSectionTitle());
				textView.setClickable(false);
				
			}else {
				ItemTitle itemTitle = (ItemTitle) title;
				TextView textView = (TextView) view.findViewById(R.id.textview_main_drawer_item);
				textView.setText(itemTitle.getItemTitle());
				textView.setTextSize(13f);
			}
		}
		
		return view;
	}

	
}
