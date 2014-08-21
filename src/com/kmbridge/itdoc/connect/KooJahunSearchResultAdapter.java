package com.kmbridge.itdoc.connect;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import lazyList.ImageLoader;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import com.kmbridge.itdoc.image.ImageManager;
import com.kmbridge.itdoc.util.RecycleUtil;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class KooJahunSearchResultAdapter extends BaseAdapter {

	// 추천한 이웃들 저장할 이미지뷰 배열
	ImageView likeUser[] = new ImageView[4];

	private LayoutInflater inflator;
	//public ImageLoader imageLoader;
	public String email;
	public Context context;

	private List<WeakReference<View>> mRecycleList = new ArrayList<WeakReference<View>>();
	public List<ClinicListItem> clinicListItemList = new ArrayList<ClinicListItem>();

	public void recycle() {
		RecycleUtil.recursiveRecycle(mRecycleList);
	}

	public void recycleHalf() {
		int halfSize = mRecycleList.size() / 2;
		List<WeakReference<View>> recycleHalfList = mRecycleList.subList(0, halfSize);
		RecycleUtil.recursiveRecycle(recycleHalfList);
		for (int i = 0; i < halfSize; i++)
			mRecycleList.remove(0);
	}

	public KooJahunSearchResultAdapter(String email, Context context, ArrayList<KmClinicView> kmClinicViewList) {
		super();
		this.email = email;
		this.context = context;

		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imageLoader = new ImageLoader(context);

		email = "test@gmail.com";
		LoadData load = new LoadData(context);
		pictureIdArr = new int[kmClinicViewList.size()];
		
		for (int i = 0; i < kmClinicViewList.size(); i++) {
			Log.d("koo", "jahunkoo"+i);
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

			clinicListItemList.add(new ClinicListItem(id, picturepath, name, regionName, keyword, followNum, likeNum, type));
			
			// 한의원 객체를 가져옴
			KmClinicDetailView KmClinicview = load.getKmClinicDetailView(id);
			String ClinicPictureName = KmClinicview.getPicturePath();
			String[] ClinicArr = ClinicPictureName.split(".png");
			String resClinName = ClinicArr[0];
			
			int clinicPictureId = context.getResources().getIdentifier(resClinName, "drawable", context.getPackageName());
			pictureIdArr[i] = clinicPictureId; 
			
			//사용자 프로필 이미지
			
			List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
			simpleList = load.getRandomUserSimpleInfoList(id);
			int[] picArr = new int[simpleList.size() - 1];
			for (int j = 0; j < simpleList.size() - 1; j++) {
				String pictureName = simpleList.get(j).getPicturePath();
				String[] arr = pictureName.split(".png");
				String resName = arr[0];
				// Log.d("kim4","Path : "+resName);
				int pictureId = context.getResources().getIdentifier(resName, "drawable", this.context.getPackageName());
				picArr[j] = pictureId; 
			}
			profilePictureList.add(picArr);
		}

	}

	private int[] pictureIdArr; 
	private List<int[]> profilePictureList = new ArrayList<int[]>();
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

		ImageView imgView = null;
		ImageView followImgView;
		TextView nameView;
		TextView regionNameView;
		TextView likeNumView;
		TextView followNumView;
		
		
		if (view == null) {
			view = inflator.inflate(R.layout.hard_search_clinic_list_item, parent, false);
			Log.d("koo", "sdasdasdasasd");
			view.setTag((Integer) position);
			
			// 추천한 이웃들 사진 지정
			likeUser[0] = (ImageView) view.findViewById(R.id.imageView1);
			likeUser[1] = (ImageView) view.findViewById(R.id.imageView2);
			likeUser[2] = (ImageView) view.findViewById(R.id.ImageView01);
			likeUser[3] = (ImageView) view.findViewById(R.id.ImageView02);
		}

		
		// TextView keyword;
		final int clinicId;

		// 병원이미지
		imgView = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_clinicimage);
		// 병원 이름
		nameView = (TextView) view.findViewById(R.id.textview_clinic_list_item_name);
		// 병원 주소
		regionNameView = (TextView) view.findViewById(R.id.textview_clinic_list_item_region);
		// 추천 수
		likeNumView = (TextView) view.findViewById(R.id.textview_clinic_list_item_likenum);
		// 팔로워 수
		followNumView = (TextView) view.findViewById(R.id.textview_clinic_list_item_follower);
		// keyword = (TextView)
		// view.findViewById(R.id.textview_clinic_list_item_keyword);
		// 하트이미지
		followImgView = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_follow_img);
		
		final ClinicListItem clinicListItem = (ClinicListItem) getItem(position);
		
		clinicId = clinicListItem.id;
		final int type = clinicListItem.type;

		//imgView.setImageResource(pictureIdArr[position]);
		
		//Bitmap bitmap = ImageManager.decodeSampledBitmapFromResource(context.getResources() , clinicPictureId, ImageManager.screenWidth);
		//Bitmap bitmap = ImageManager.decodeSampledBitmapFromResource(context.getResources() , pictureIdArr[position], ImageManager.screenWidth);
		//imgView.setImageBitmap(bitmap);

		int[] pictureIdArr = profilePictureList.get(position);
		/*
		for (int j = 0; j < pictureIdArr.length - 1; j++) {
			likeUser[j].setImageResource(pictureIdArr[j]);
		}
*/
		
		nameView.setText(clinicListItem.name);
		regionNameView.setText(clinicListItem.regionName);
		// keyword.setText(clinicListItem.keyword);

		likeNumView.setText(toString().valueOf(clinicListItem.likeNum));
		followNumView.setText(toString().valueOf(clinicListItem.followNum));
/*
		if (clinicListItem.type == 1) {
			followImgView.setImageResource(R.drawable.follow);
		} else {
			followImgView.setImageResource(R.drawable.not_follow);
		}
		*/
		/*
		if(SharedPreferenceUtil.isExist(context, "follow")==true && (clinicId==2 || clinicId==3 || clinicId==4 || clinicId==11))
		{
			followImgView.setImageResource(R.drawable.follow);
		}
*/
		Log.d("kim3","Id : "+clinicId);
		
		followImgView.setTag("followImg");
		imgView.setTag("clinicImg");
		/*
		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {

				ImageView img = (ImageView) v.findViewWithTag("followImg");
				ImageView clinicImg = (ImageView) v.findViewWithTag("clinicImg");
				ConnectionBridge conn = new ConnectionBridge();

				switch (v.getId()) {

				case R.id.imageview_clinic_list_item_follow_img:

					if (type == 0) {
						// ArrayList<String> result =
						// conn.insertKmClinicFollow("insertKmClinicFollow",
						// context, email, clinicId);
						clinicListItem.type = 1;
						img.setImageResource(R.drawable.follow);

					} else {
						// ArrayList<String> result =
						// conn.deleteKmClinicFollow("deleteKmClinicFollow",
						// context, email, clinicId);
						clinicListItem.type = 0;
						img.setImageResource(R.drawable.not_follow);

					}
					break;

				case R.id.imageview_clinic_list_item_clinicimage:

					Intent intent = new Intent(context, KmClinicDetailActivity.class);

					intent.putExtra("clinicNumber", clinicId);

					Log.d("kim", "clinicId is " + clinicId);

					recycle();

					context.startActivity(intent);
					break;
				}
			}
		};
*/
		//followImgView.setOnClickListener(onClickListener);
		//imgView.setOnClickListener(onClickListener);
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
