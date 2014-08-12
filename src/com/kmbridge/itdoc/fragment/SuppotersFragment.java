package com.kmbridge.itdoc.fragment;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.SupporterActivity;
import com.kmbridge.itdoc.hardcoding.PageFragment;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageView;

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
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setAlpha(10);
		upView.setBackgroundColor(paint.getColor());
		upView.setOnClickListener(this);
		downView = (ImageView) rootView.findViewById(R.id.imageview_hard_support_down);
		downView.setOnClickListener(this); 
		 
		 
		 
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
			Intent intent = new Intent(getActivity(), SupporterActivity.class);
			intent.putExtra("position", "down");
			getActivity().startActivity(intent);
		}

	}
	
	
	
}