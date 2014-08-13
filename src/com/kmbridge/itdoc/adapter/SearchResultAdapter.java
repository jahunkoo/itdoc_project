package com.kmbridge.itdoc.adapter;

import java.util.ArrayList;
import java.util.List;

import lazyList.ImageLoader;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.KmClinicDetailActivity;
import com.kmbridge.itdoc.adapter.ClinicListAdapter.ClinicListItem;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicView;

public class SearchResultAdapter extends BaseAdapter {

	private LayoutInflater inflator;
	public ImageLoader imageLoader;
	public ArrayAdapter<ClinicListItem> adapter;
	public String email;
	public Context context;

	public List<ClinicListItem> clinicListItemList = new ArrayList<ClinicListItem>();

	public SearchResultAdapter(String email, Context context, ArrayList<KmClinicView> kmClinicViewList) {
		super();
		this.email = email;
		this.context = context;

		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(context);


		email = "test@gmail.com";

		for (int i = 0; i < kmClinicViewList.size(); i++) {

			KmClinicView kmClinicView = kmClinicViewList.get(i);

			String local = kmClinicView.getBigRegionName() + " " + kmClinicView.getMiddleRegionName();

			int id = kmClinicView.getId();
			String picturepath = kmClinicView.getPicturePath();
			String name = kmClinicView.getName();
			String regionName = local;
			int followNum = kmClinicView.getFollowNum();
			int likeNum = kmClinicView.getUserSimpleInfoList().size();
			String keyword = kmClinicView.getKeywordList().get(0);
			int type = kmClinicView.getType();

			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clinicListItemList.add(new ClinicListItem(id, picturepath, name, regionName, keyword, followNum, likeNum, type));

		}

		adapter = new ArrayAdapter<ClinicListItem>(context, R.id.listview_clinic_list, clinicListItemList);

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return clinicListItemList.size();
	}

	@Override
	public ClinicListItem getItem(int position) {
		// TODO Auto-generated method stub
		return clinicListItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		ImageView img = null;
		ImageView followImg;
		TextView name;
		TextView regionName;
		TextView likeNum;
		TextView followNum;
		//TextView keyword;
		final int clinicId;

		if (view == null) {
			view = inflator.inflate(R.layout.hard_search_clinic_list_item, parent, false);
			view.setTag((Integer) position);
		}

		img = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		name = (TextView) view.findViewById(R.id.textview_clinic_list_item_name);
		regionName = (TextView) view.findViewById(R.id.textview_clinic_list_item_region);
		likeNum = (TextView) view.findViewById(R.id.textview_clinic_list_item_likenum);
		followNum = (TextView) view.findViewById(R.id.textview_clinic_list_item_follower);
		//keyword = (TextView) view.findViewById(R.id.textview_clinic_list_item_keyword);
		followImg = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_follow_img);

		final ClinicListItem clinicListItem = (ClinicListItem) getItem(position);
/*
		String url = "http://yss159.cafe24.com:8080/ItDocImgServer/getPicture?picturePath=" + clinicListItem.picturepath + "&objectType=2";
		try {
			imageLoader.DisplayImage(url, img);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		name.setText(clinicListItem.name);
		regionName.setText(clinicListItem.regionName);
		//keyword.setText(clinicListItem.keyword);

		likeNum.setText(toString().valueOf(clinicListItem.likeNum));
		followNum.setText(toString().valueOf(clinicListItem.followNum));

		clinicId = clinicListItem.id;
		final int type = clinicListItem.type;

		if (clinicListItem.type == 1) {
			followImg.setImageResource(R.drawable.follow);
		} else {
			followImg.setImageResource(R.drawable.not_follow);
		}

		followImg.setTag("followImg");
		img.setTag("clinicImg");
		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {

				ImageView img = (ImageView) v.findViewWithTag("followImg");
				ImageView clinicImg = (ImageView) v.findViewWithTag("clinicImg");
				ConnectionBridge conn = new ConnectionBridge();

				switch (v.getId()) {

				case R.id.imageview_clinic_list_item_follow_img:

					if (type == 0) {
						ArrayList<String> result = conn.insertKmClinicFollow("insertKmClinicFollow", context, email, clinicId);
						clinicListItem.type = 1;
						img.setImageResource(R.drawable.follow);

					} else {
						ArrayList<String> result = conn.deleteKmClinicFollow("deleteKmClinicFollow", context, email, clinicId);
						clinicListItem.type = 0;
						img.setImageResource(R.drawable.not_follow);

					}
					break;

				case R.id.imageview_clinic_list_item_clinicimage:

					Intent intent = new Intent(context, KmClinicDetailActivity.class);

					intent.putExtra("clinicNumber", clinicId);
					
					Log.d("kim","clinicId is " + clinicId);
					
					context.startActivity(intent);
					break;
				}
			}
		};

		followImg.setOnClickListener(onClickListener);
		img.setOnClickListener(onClickListener);
		return view;
	}

	public class ClinicListItem {
		int id;
		String picturepath;
		String name;
		String regionName;
		String keyword;
		int followNum;
		int likeNum;
		int type;

		public ClinicListItem(int id, String picturepath, String name, String regionName, String keyword, int followNum, int likeNum, int type) {
			super();
			this.id = id;
			this.picturepath = picturepath;
			this.name = name;
			this.regionName = regionName;
			this.keyword = keyword;
			this.followNum = followNum;
			this.likeNum = likeNum;
			this.type = type;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

	}
}
