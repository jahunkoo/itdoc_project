package com.kmbridge.itdoc.adapter;

import java.util.ArrayList;
import java.util.List;

import lazyList.ImageLoader;
import android.R.string;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.util.BasicConstants;

public class ClinicListAdapter extends BaseAdapter {

	public List<ClinicListItem> clinicListItemList = new ArrayList<ClinicListItem>();
	private LayoutInflater inflator;
	public ImageLoader imageLoader;
	public ArrayAdapter<ClinicListItem> adapter;

	public ClinicListAdapter(Context context) {

		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(context);

		ArrayList<KmClinicView> kmClinicViewList = new ArrayList<KmClinicView>();
		ConnectionBridge connectionBridge = new ConnectionBridge();
		kmClinicViewList = connectionBridge.getKmClinicViewList("getAllKmClinic", context);

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

			clinicListItemList.add(new ClinicListItem(id, picturepath, name, regionName, keyword, followNum, likeNum));

		}

		adapter = new ArrayAdapter<ClinicListItem>(context, R.id.listview_clinic_list, clinicListItemList);

		Log.d("kim", "ClinicListAdapter(54) list size is " + kmClinicViewList.size());

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return clinicListItemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return clinicListItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		ImageView img;
		TextView name;
		TextView regionName;
		TextView likeNum;
		TextView followNum;
		TextView keyword;

		if (view == null) {
			view = inflator.inflate(R.layout.clinic_list_item, parent,false);
		}

		
		img = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		name = (TextView) view.findViewById(R.id.textview_clinic_list_item_name);
		regionName = (TextView) view.findViewById(R.id.textview_clinic_list_item_region);
		likeNum = (TextView) view.findViewById(R.id.textview_clinic_list_item_likenum);
		followNum = (TextView) view.findViewById(R.id.textview_clinic_list_item_follower);
		keyword = (TextView) view.findViewById(R.id.textview_clinic_list_item_keyword);
		
		ClinicListItem clinicListItem = (ClinicListItem) getItem(position);

		String url = "http://yss159.cafe24.com:8080/ItDocImgServer/getPicture?picturePath=" + clinicListItem.picturepath +"&objectType=2";
		
		try {
			imageLoader.DisplayImage(url, img);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		name.setText(clinicListItem.name);
		regionName.setText(clinicListItem.regionName);
		keyword.setText(clinicListItem.keyword);
		
		likeNum.setText(toString().valueOf(clinicListItem.likeNum));
		followNum.setText(toString().valueOf(clinicListItem.followNum));

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
		
		public ClinicListItem(int id, String picturepath, String name, String regionName, String keyword, int followNum, int likeNum) {
			super();
			this.id = id;
			this.picturepath = picturepath;
			this.name = name;
			this.regionName = regionName;
			this.keyword = keyword;
			this.followNum = followNum;
			this.likeNum = likeNum;
		}

		

	}

}
