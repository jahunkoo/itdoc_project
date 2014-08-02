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

public class DrawerTitleAdapter extends ArrayAdapter<Title> implements OnClickListener{

	private View view;
	private List<Title> titleList;
	private LayoutInflater inflator;
	private int layoutResId; 
	private LinearLayout leftDrawerBottomLayout;
	private String userEmail;	//없으면 null로 명시함
	private boolean isLogin;
	
	public DrawerTitleAdapter(Context context,LinearLayout leftDrawerBottomLayout ,int resource,List<Title> titleList) {
		super(context, resource, titleList);
		this.titleList =titleList;  
		this.layoutResId = resource;
		inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.leftDrawerBottomLayout = leftDrawerBottomLayout;
		
		//이메일이 담겨있는지 확인한다.
		SharedPreferenceUtil sharedUtil = new SharedPreferenceUtil();
		try {
			userEmail = sharedUtil.getData(context, ItDocConstants.SHARED_KEY_EMAIL);
		} catch (RecordNotFoundException e) {
			userEmail = null;
			e.printStackTrace();
		}finally{
			if(userEmail == null){
				isLogin = false;
				LinearLayout linearLayout = (LinearLayout) inflator.inflate(R.layout.main_drawer_item_bottom_before_login , null);
				leftDrawerBottomLayout.addView(linearLayout);
			}else{
				isLogin = true;
				LinearLayout linearLayout = (LinearLayout) inflator.inflate(R.layout.main_drawer_item_bottom_after_login , null);
				leftDrawerBottomLayout.addView(linearLayout);
			}
		}
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


	@Override
	public void onClick(View v) {
		if(isLogin){
			
		}else {
			
		}
		
	}

	
}