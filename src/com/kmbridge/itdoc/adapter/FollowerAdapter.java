package com.kmbridge.itdoc.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.UserProfileActivity;
import com.kmbridge.itdoc.dto.UserSimpleFollow;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.hardcoding.LoadData;
import com.kmbridge.itdoc.util.ItDocConstants;

public class FollowerAdapter extends BaseAdapter{


	
	private LayoutInflater inflator;
	Context context;
	LoadData loadData;
	String userEmail;
	UserView userView;
	List<UserSimpleFollow> followList;
	
	/**
	 * 
	 * @param context
	 * @param userEmail 사용자의 이메일을 받아서 누구의 프로필인지 확인한다.
	 * @param type 0이면 팔로잉, 1이면 팔로워를 호출한다.
	 */
	public FollowerAdapter(Context context, String userEmail, int type) {
		super();
		this.context = context;
		this.userEmail = userEmail;
		
		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		loadData = new LoadData(context);
		
		userView = loadData.getUserView(userEmail);
		
		if(type == 0) {
			followList = userView.getFollowingList();
		} else if(type == 1) {
			followList = userView.getFollowList();
		} else {
			// 나중에 exception 처리 해준다.
		}
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return followList.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return followList.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		ImageView face;
		ImageView followButton;
		TextView name;
		TextView followNum;
		
		if (view == null) {
			view = inflator.inflate(R.layout.user_follower_item, parent, false);
			view.setTag((Integer) position);
		}

		face = (ImageView) view.findViewById(R.id.imageView1);
		name = (TextView) view.findViewById(R.id.textView1);
		followNum = (TextView) view.findViewById(R.id.textView3);
		followButton = (ImageView) view.findViewById(R.id.imageView2);
		
		name.setText(followList.get(position).getName());
		followNum.setText(toString().valueOf(followList.get(position).getFollowNum()));
		
		
		// 나중에 여기에 이미지 꺼내와서 붙이는 함수 써준다
		face.setImageResource(R.drawable.face6);
		
		
		followButton.setTag("followButton");
		
		OnClickListener onClickListener = new OnClickListener() {
			
			ImageView img;
			
			@Override
			public void onClick(View v) {

				switch(v.getId()) {
				
				case R.id.imageView1 :
				
					Intent intent = new Intent(context,UserProfileActivity.class);
					intent.putExtra(ItDocConstants.EMAIL, followList.get(position).getEmail());
					
					Log.d("kim","FollowerAdapter(112) getEmail is " + followList.get(position).getEmail());
					
					context.startActivity(intent);
				break;
				
				case R.id.imageView2 :
				
					img = (ImageView) v.findViewWithTag("followButton");
					
					img.setImageResource(R.drawable.btn_call_off);
					
				break;
				
				}
				
			}
		};
		followButton.setOnClickListener(onClickListener);
		face.setOnClickListener(onClickListener);
		
		
		
		return view;
	}
	
}
