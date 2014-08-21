package com.kmbridge.itdoc.fragment;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.HanbangInfoActivity;
import com.kmbridge.itdoc.activity.SupporterActivity;
import com.kmbridge.itdoc.hardcoding.PageFragment;
import com.kmbridge.itdoc.image.ImageManager;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HanbangInfoFragment extends Fragment implements OnClickListener{
	
	private Context context;
	private String[] titleArr;
	private int[] imgSrcArr;
	
	private String introduce1;
	
	private void setContext(Context context){
		this.context = context;
	}
	
	public static HanbangInfoFragment create(Context context) {
		HanbangInfoFragment suppotersFragment = new HanbangInfoFragment();
		suppotersFragment.setContext(context);
		return suppotersFragment;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		titleArr = getResources().getStringArray(R.array.hanbang_info_main_introduce_array);
		introduce1 = getResources().getString(R.string.info_fragment_name);
		imgSrcArr = new int[titleArr.length];
		imgSrcArr[0] = R.drawable.theme3;
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.hard_fragment_supporters_kookoo, container, false);
		LinearLayout containLayout = (LinearLayout) rootView.findViewById(R.id.linearlayout_fragment_supporters_main);
		
		for(int i=0;i<titleArr.length;i++){
			RelativeLayout elementLayout = (RelativeLayout) inflater.inflate(R.layout.layout_item_hanbang_info_main, container, false);
			
			ImageView clinicImgView = (ImageView) elementLayout.findViewById(R.id.imageview_hard_hanbang_info_up);
			Bitmap bitmap = ImageManager.decodeSampledBitmapFromResource(getResources(), imgSrcArr[i], ImageManager.screenWidth);
			
			int bitmapHeight = bitmap.getHeight();
			Log.d("koo", "bitmapHeight :"+bitmapHeight);
			if(bitmapHeight>560){
				int remainHeight = bitmapHeight - 560;
				bitmap = Bitmap.createBitmap(bitmap, 0, remainHeight/2, bitmap.getWidth(), 560, null, false);
			}else if(bitmapHeight<560){
				  int bitmapWidth = bitmap.getWidth();
			      bitmapHeight = bitmap.getHeight();
			      float yScale = 560 / bitmapHeight;
			      Matrix matrix = new Matrix();
			      matrix.postScale(1, yScale);
			      Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
			      bitmap = scaledBitmap ;
			      Log.d("koo", "bitmapHeight in <560:"+bitmapHeight);
			}
			
			
			clinicImgView.setImageBitmap(bitmap);
			
			elementLayout.setTag(""+i);
			elementLayout.setOnClickListener(this);
			
			containLayout.addView(elementLayout);
		}
		
		//upView = (ImageView) rootView.findViewById(R.id.imageview_hard_hanbang_info_up);
		//upView.setOnClickListener(this);
		//downView = (ImageView) rootView.findViewById(R.id.imageview_hard_support_down);
		//downView.setOnClickListener(this); 
		Button button = (Button) rootView.findViewById(R.id.button_fragment_supporters_main);
		button.setTag("button");
		button.setOnClickListener(this);
		button.setText("정보 검색");
		return rootView;
	}
	

	
	
	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		Intent intent = new Intent(getActivity(), HanbangInfoActivity.class);
		if(tag.equals("0")){
			Log.d("koo", "clicked view tag:"+tag);
			intent.putExtra("position", tag);
			getActivity().startActivity(intent);
		}else if(tag.equals("1")){
			Log.d("koo", "clicked view tag:"+tag);
			intent.putExtra("position", tag);
			getActivity().startActivity(intent);
		}else if(tag.equals("button")){
			Log.d("koo", "clicked view tag:"+tag);
		}
		
	}
	
	
	
}
