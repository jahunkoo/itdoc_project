package com.kmbridge.itdoc.fragment;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.SupporterActivity;
import com.kmbridge.itdoc.hardcoding.PageFragment;
import com.kmbridge.itdoc.image.ImageManager;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
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
import android.widget.ImageView;
import android.widget.Toast;

public class SuppotersFragment extends Fragment implements OnClickListener{
	
	private Context context;
	private void setContext(Context context){
		this.context = context;
	}
	
	public static SuppotersFragment create(Context context) {
		SuppotersFragment suppotersFragment = new SuppotersFragment();
		suppotersFragment.setContext(context);
		return suppotersFragment;
	}

	private ImageView upView;
	private ImageView downView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.hard_fragment_supporters_kookoo, container, false);
		upView = (ImageView) rootView.findViewById(R.id.imageview_hard_support_up);
		upView.setOnClickListener(this);
		downView = (ImageView) rootView.findViewById(R.id.imageview_hard_support_down);
		downView.setOnClickListener(this); 
		 
		//ImageView imgView1 = (ImageView) findViewById(R.id.imageview_hard_support_up);
		
		//Bitmap supportUpBitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.supporters_main_up);
		Bitmap supportUpBitmap =ImageManager.decodeSampledBitmapFromResource(getActivity().getResources(), R.drawable.supporters_main_up, ImageManager.screenWidth);
		upView.setImageBitmap(supportUpBitmap);
		Log.d("koo", "support up img density:"+supportUpBitmap.getDensity());
		Log.d("koo", "support up img width:"+supportUpBitmap.getWidth());
		Log.d("koo", "support up img height:"+supportUpBitmap.getHeight());
		
		supportUpBitmap =ImageManager.decodeSampledBitmapFromResource(getActivity().getResources(), R.drawable.supporters_main_down, ImageManager.screenWidth);
		downView.setImageBitmap(supportUpBitmap);
		
		return rootView;
	}
	


	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == upView.getId()){
			Intent intent = new Intent(getActivity(), SupporterActivity.class);
			intent.putExtra("position", "up");
			getActivity().startActivity(intent);
		}else if(id == downView.getId()){
			Toast.makeText(getActivity(),"준비중입니다 :)", Toast.LENGTH_SHORT).show();
			
			//Intent intent = new Intent(getActivity(), SupporterActivity.class);
			//intent.putExtra("position", "down");
			//getActivity().startActivity(intent);
		}

	}
	
	
	
}
