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
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class SearchResultAdapter extends BaseAdapter {

	//추천한 이웃들 저장할 이미지뷰 배열
	ImageView likeUser[] = new ImageView[4];
		
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

		//병원이미지
		img = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		
		//병원 이름
		name = (TextView) view.findViewById(R.id.textview_clinic_list_item_name);
		
		//병원 주소
		regionName = (TextView) view.findViewById(R.id.textview_clinic_list_item_region);
		
		//추천 수
		likeNum = (TextView) view.findViewById(R.id.textview_clinic_list_item_likenum);
		
		//팔로워 수
		followNum = (TextView) view.findViewById(R.id.textview_clinic_list_item_follower);
		//keyword = (TextView) view.findViewById(R.id.textview_clinic_list_item_keyword);
		
		//하트이미지 
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
		
		//추천한 이웃들 사진 지정
		likeUser[0] = (ImageView) view.findViewById(R.id.imageView1);
		likeUser[1] = (ImageView) view.findViewById(R.id.imageView2);
		likeUser[2] = (ImageView) view.findViewById(R.id.ImageView01);
		likeUser[3] = (ImageView) view.findViewById(R.id.ImageView02);
		//likeUser[4] = (ImageView) view.findViewById(R.id.ImageView03);	
		clinicId = clinicListItem.id;
		final int type = clinicListItem.type;
		
		//json파서 로드
		LoadData load = new LoadData(context);
		//한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicId);
		
		//병원사진 뿌리기
		String ClinicPictureName = KmClinicview.getPicturePath();
		String []ClinicArr = ClinicPictureName.split(".png");
		String resClinName = ClinicArr[0];
		Log.d("kim5",resClinName);
		int clinicPictureId = context.getResources().getIdentifier(resClinName, "drawable", context.getPackageName());
		img.setImageResource(clinicPictureId);
		
		//추천한 사람 뿌리기
		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		simpleList = load.getRandomUserSimpleInfoList(clinicId);
		for(int j=0;j<simpleList.size()-1;j++)
		{
			String pictureName = simpleList.get(j).getPicturePath();
			String []arr = pictureName.split(".png");
			String resName = arr[0];
			//Log.d("kim4","Path : "+resName);
			int pictureId = context.getResources().getIdentifier(resName, "drawable", this.context.getPackageName());
			//Log.d("kim4","Resource :"+pictureId);
			likeUser[j].setImageResource(pictureId);
		}
		
		name.setText(clinicListItem.name);
		regionName.setText(clinicListItem.regionName);
		//keyword.setText(clinicListItem.keyword);

		likeNum.setText(toString().valueOf(clinicListItem.likeNum));
		followNum.setText(toString().valueOf(clinicListItem.followNum));

		

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
						//ArrayList<String> result = conn.insertKmClinicFollow("insertKmClinicFollow", context, email, clinicId);
						clinicListItem.type = 1;
						img.setImageResource(R.drawable.follow);

					} else {
						//ArrayList<String> result = conn.deleteKmClinicFollow("deleteKmClinicFollow", context, email, clinicId);
						clinicListItem.type = 0;
						img.setImageResource(R.drawable.not_follow);

					}
					break;

				case R.id.imageview_clinic_list_item_clinicimage:

					Intent intent = new Intent(context, KmClinicDetailActivity.class);

					intent.putExtra("clinicNumber", clinicId);
					Log.d("kim2",""+clinicId);
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
