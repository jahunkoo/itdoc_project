package com.kmbridge.itdoc.fragment;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.SupporterActivity;
import com.kmbridge.itdoc.image.ImageManager;

public class SuppotersFragment extends Fragment implements OnClickListener{
	
	private Context context;
	private String[] introduceArr;
	private int[] imgSrcArr;
	private void setContext(Context context){
		this.context = context;
	}
	
	public static SuppotersFragment create(Context context) {
		SuppotersFragment suppotersFragment = new SuppotersFragment();
		suppotersFragment.setContext(context);
		return suppotersFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		introduceArr = getResources().getStringArray(R.array.choose_clinic_main_introduce_array);
		imgSrcArr = new int[introduceArr.length];
		imgSrcArr[0] = 	R.drawable.test_test;
		imgSrcArr[1] =  R.drawable.test_test_test;
		
		super.onCreate(savedInstanceState);
	}

	private ImageView upView;
	private ImageView downView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.hard_fragment_supporters_kookoo, container, false);
		 
		LinearLayout containLayout = (LinearLayout) rootView.findViewById(R.id.linearlayout_fragment_supporters_main);		
		
		for(int i=0;i<introduceArr.length;i++){
			RelativeLayout elementLayout = (RelativeLayout) inflater.inflate(R.layout.layout_choice_clinic_item, container, false);
			
			ImageView clinicImgView = (ImageView) elementLayout.findViewById(R.id.imageview_choice_clinic_item);
			ImageView favoriteImgView =  (ImageView) elementLayout.findViewById(R.id.imageview_choice_clinic_item_follow_img);
			TextView introduceTextView = (TextView) elementLayout.findViewById(R.id.textview_choice_clinic_item_introduce);
			
			Bitmap bitmap = ImageManager.decodeSampledBitmapFromResource(getResources(), imgSrcArr[i], ImageManager.screenWidth);
			
			int bitmapHeight = bitmap.getHeight();
			if(bitmapHeight>560){
				int remainHeight = bitmapHeight - 560;
				bitmap = Bitmap.createBitmap(bitmap, 0, remainHeight/2, bitmap.getWidth(), 560, null, false);
			}
		
			clinicImgView.setImageBitmap(bitmap);
			//clinicImgView.setMaxHeight(560);
			//clinicImgView.setLayoutParams(new LayoutParams(elementLayout.getWidth(), 560));
			introduceTextView.setText(introduceArr[i]);
			elementLayout.setTag(""+i);
			elementLayout.setOnClickListener(this);
			
			containLayout.addView(elementLayout);
		}
		
		Button button = (Button) rootView.findViewById(R.id.button_fragment_supporters_main);
		button.setTag("button");
		button.setOnClickListener(this);
		return rootView;
	}
	
	
	@Override
	public void onClick(View v) {
		
		String tag = (String) v.getTag();
		Intent intent = new Intent(getActivity(), SupporterActivity.class);
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
