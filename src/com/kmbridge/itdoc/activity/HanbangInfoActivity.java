package com.kmbridge.itdoc.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.fragment.HanBangInnerFragment;

public class HanbangInfoActivity extends FragmentActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hanbang_info);
		
		TextView text0 = (TextView) findViewById(R.id.textview_hanbang_info_0);
		TextView text1 = (TextView) findViewById(R.id.textview_hanbang_info_1);
		TextView text2 = (TextView) findViewById(R.id.textview_hanbang_info_2);
		TextView text3 = (TextView) findViewById(R.id.textview_hanbang_info_3);
		text0.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/nanum_bold.ttf"));
		text1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/nanum_bold.ttf"));
		text2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/nanum_bold.ttf"));
		text3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/nanum_bold.ttf"));
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		/*Bundle bundle = getIntent().getExtras();
		String position = bundle.getString("position");
		if(position.equals("0")){
			Log.d("koo", "test up");
		}else if(position.equals("1")){
			Log.d("koo", "test down");
		}
		*/
		Fragment fregment = new HanBangInnerFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.content_frame, fregment);
		transaction.commit();	
		
		
		//fragmentManager.beginTransaction().add(R.id.content_frame, fragment,FRAGMENT_TAG).addToBackStack(null).commit();
		//fragment = fragmentManager.findFragmentByTag("CLINIC_LIST");
		//fragment.getView().setVisibility(View.GONE);
		//searchItem.setVisible(false); 
		
		//afterFragmentCreate(position);	
	}

	
	
	
}
