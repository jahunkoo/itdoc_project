package com.kmbridge.itdoc.adapter;

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
import com.kmbridge.itdoc.image.ImageManager;
import com.kmbridge.itdoc.util.RecycleUtil;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class HardSearchResultAdapter extends ArrayAdapter implements OnClickListener{

	// 추천한 이웃들 저장할 이미지뷰 배열
	ImageView likeUser[] = new ImageView[4];

	private LayoutInflater inflator;
	public ImageLoader imageLoader;
	public String email;
	public Context context;
	public ArrayList<KmClinicView> kmClinicViewList;
	private List<String[]> profilePicturePathList = new ArrayList<String[]>();
	
	public HardSearchResultAdapter(String email, Context context, ArrayList<KmClinicView> kmClinicViewList) {
		super(context, R.layout.hard_search_clinic_list_item, kmClinicViewList);
		this.email = email;
		this.context = context;
		this.kmClinicViewList= kmClinicViewList; 
		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(context);
		email = "test@gmail.com";
		
		LoadData load = new LoadData(context);
		
		for (int i = 0; i < kmClinicViewList.size(); i++) {
			KmClinicView kmClinicView = kmClinicViewList.get(i);
			
			KmClinicDetailView KmClinicview = load.getKmClinicDetailView(kmClinicView.getId());
			kmClinicView.setPicturePath(KmClinicview.getPicturePath());
			
			ArrayList<UserSimpleInfo>  userList = load.getRandomUserSimpleInfoList(kmClinicView.getId());
			String[] userArr = new String[userList.size()];
			for(int j=0;j<userArr.length;j++){
				userArr[j] = userList.get(j).getPicturePath();
			}
			profilePicturePathList.add(userArr);
		}
		/*
		for (int i = 0; i < kmClinicViewList.size(); i++) {

			KmClinicView kmClinicView = kmClinicViewList.get(i);
			
			//KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicId);
		Log.d("koo", "picture name0:"+picturePath);
		// 병원사진 뿌리기
		//String ClinicPictureName = KmClinicview.getPicturePath();
		 * 
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
				Thread.currentThread().sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clinicListItemList.add(new ClinicListItem(id, picturepath, name, regionName, keyword, followNum, likeNum, type));
		}
		Log.d("koo", "is successful?");
		*/
	}
	
	private ImageView imgView;
	private TextView nameTextView;
	private TextView regionNameTextView;
	private TextView likeNumTextView;
	private TextView followNumTextView;
	private ImageView followImgView;
	
	private int clinicId;
	private int type;
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		
		// TextView keyword;
		View view = convertView;
		if (view == null) {
			view = inflator.inflate(R.layout.hard_search_clinic_list_item, parent, false);
			view.setTag((Integer) position);
			// 병원이미지
			imgView = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_clinicimage);
			// 병원 이름
			nameTextView = (TextView) view.findViewById(R.id.textview_clinic_list_item_name);
			// 병원 주소
			regionNameTextView = (TextView) view.findViewById(R.id.textview_clinic_list_item_region);
			// 추천 수
			likeNumTextView = (TextView) view.findViewById(R.id.textview_clinic_list_item_likenum);
			// 팔로워 수
			followNumTextView = (TextView) view.findViewById(R.id.textview_clinic_list_item_follower);
			// keyword = (TextView)
			// view.findViewById(R.id.textview_clinic_list_item_keyword);
			// 하트이미지
			followImgView = (ImageView) view.findViewById(R.id.imageview_clinic_list_item_follow_img);
			
			// 추천한 이웃들 사진 지정
			likeUser[0] = (ImageView) view.findViewById(R.id.imageView1);
			likeUser[1] = (ImageView) view.findViewById(R.id.imageView2);
			likeUser[2] = (ImageView) view.findViewById(R.id.ImageView01);
			likeUser[3] = (ImageView) view.findViewById(R.id.ImageView02);
		}
		
		
		//데이터 초기화
		KmClinicView kmClinicView = kmClinicViewList.get(position);
		String[] pictureNameArr = profilePicturePathList.get(position);
		String local 		= kmClinicView.getBigRegionName() + " " + kmClinicView.getMiddleRegionName();
		clinicId			= kmClinicView.getId();
		String picturePath 	= kmClinicView.getPicturePath();
		String name 		= kmClinicView.getName();
		String regionName 	= local;
		int followNum 		= kmClinicView.getFollowNum();
		int likeNum 		= kmClinicView.getUserSimpleInfoList().size();
		String keyword 		= kmClinicView.getKeywordList().get(0);
		type 				= kmClinicView.getType();

		// 추천한 이웃들 사진 지정
		//likeUser[0] = (ImageView) view.findViewById(R.id.imageView1);
		//likeUser[1] = (ImageView) view.findViewById(R.id.imageView2);
		//likeUser[2] = (ImageView) view.findViewById(R.id.ImageView01);
		//likeUser[3] = (ImageView) view.findViewById(R.id.ImageView02);
		// likeUser[4] = (ImageView) view.findViewById(R.id.ImageView03);
		

		// json파서 로드
		//LoadData load = new LoadData(context);
		// 한의원 객체를 가져옴
		//KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicId);
		Log.d("koo", "picture name0:"+picturePath);
		// 병원사진 뿌리기
		//String ClinicPictureName = KmClinicview.getPicturePath();
		String[] ClinicArr = picturePath.split(".png");
		Log.d("koo", "picture name1:"+ClinicArr[0]);
		String resClinName = ClinicArr[0];
		int clinicPictureId = context.getResources().getIdentifier(resClinName, "drawable", context.getPackageName());
		//Bitmap bitmap = ImageManager.decodeSampledBitmapFromResource(context.getResources() , clinicPictureId, ImageManager.screenWidth);
		//imgView.setImageBitmap(bitmap);
		imgView.setImageResource(clinicPictureId);
		// 비트맵 이미지를 비트맵의 크기를 가지고 설정하는 코드
		// 실제로 이걸로 코드를 짜 보면 이미지의 크기가 제 멋대로 뜬다.
		
		// 추천한 사람 뿌리기
		//List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		
		//simpleList = load.getRandomUserSimpleInfoList(clinicId);
		/*for (int i = 0; i < pictureNameArr.length-1; i++) {
			
			String pictureName = pictureNameArr[i];
			String[] arr = pictureName.split(".png");
			String resName = arr[0];
			// Log.d("kim4","Path : "+resName);
			int pictureId = context.getResources().getIdentifier(resName, "drawable", this.context.getPackageName());
			//Bitmap bm = ImageManager.decodeSampledBitmapFromResource(context.getResources() , pictureId, ImageManager.screenWidth);
			// Log.d("kim4","Resource :"+pictureId);
			likeUser[i].setImageResource(pictureId);
			//likeUser[i].setImageBitmap(bm);
		}
*/
		nameTextView.setText(name);
		regionNameTextView.setText(regionName);
		// keyword.setText(clinicListItem.keyword);

		likeNumTextView.setText(toString().valueOf(likeNum));
		followNumTextView.setText(toString().valueOf(followNum));

		if (type == 1) {
			followImgView.setImageResource(R.drawable.follow);
		} else {
			followImgView.setImageResource(R.drawable.not_follow);
		}
		
		if(SharedPreferenceUtil.isExist(context, "follow")==true && (clinicId==2 || clinicId==3 || clinicId==4 || clinicId==11))
		{
			followImgView.setImageResource(R.drawable.follow);
		}

		Log.d("kim3","Id : "+clinicId);
		
		followImgView.setTag("followImg");
		imgView.setTag("clinicImg");
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
						type = 1;
						img.setImageResource(R.drawable.follow);

					} else {
						// ArrayList<String> result =
						// conn.deleteKmClinicFollow("deleteKmClinicFollow",
						// context, email, clinicId);
						type = 0;
						img.setImageResource(R.drawable.not_follow);

					}
					break;

				case R.id.imageview_clinic_list_item_clinicimage:

					Intent intent = new Intent(context, KmClinicDetailActivity.class);

					intent.putExtra("clinicNumber", clinicId);

					Log.d("kim", "clinicId is " + clinicId);

					context.startActivity(intent);
					break;
				}
			}
		};

		followImgView.setOnClickListener(onClickListener);
		imgView.setOnClickListener(onClickListener);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
