package com.kmbridge.itdoc.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class ReviewAdapter extends BaseAdapter {

	private LayoutInflater inflator;
	Context context;
	LoadData loadData;
	ArrayList<ReviewView> loadReviewViewList;
	ArrayList<ReviewView> reviewViewList;
	boolean bGood;
	boolean bSoso;
	boolean bBad;

	public ReviewAdapter(Context context, boolean bGood, boolean bSoso, boolean bBad) {
		super();
		this.context = context;
		this.bGood = bGood;
		this.bSoso = bSoso;
		this.bBad = bBad;

		inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		loadData = new LoadData(context);
		loadReviewViewList = (ArrayList<ReviewView>) loadData.getAllReviewView();

		reviewViewList = new ArrayList<ReviewView>();
		
		for (int i = 0; i < loadReviewViewList.size(); i++) {

			switch (loadReviewViewList.get(i).getFavoriteType()) {

			case 1:
				if (bGood)
					reviewViewList.add(loadReviewViewList.get(i));
				break;
			case 2:
				if (bSoso)
					reviewViewList.add(loadReviewViewList.get(i));
				break;
			case 3:
				if (bBad)
					reviewViewList.add(loadReviewViewList.get(i));
				break;
			}

		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return reviewViewList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return reviewViewList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		ImageView face;
		ImageView recommendImg;
		TextView clinicName;
		TextView location;
		TextView date;
		TextView recommendText;
		TextView reviewDetail;

		if (view == null) {
			view = inflator.inflate(R.layout.review_list_item_1, parent, false);
			view.setTag((Integer) position);
		}

		face = (ImageView) view.findViewById(R.id.imageView1);
		recommendImg = (ImageView) view.findViewById(R.id.imageView2);
		clinicName = (TextView) view.findViewById(R.id.textView1);
		location = (TextView) view.findViewById(R.id.textView2);
		date = (TextView) view.findViewById(R.id.textView3);
		recommendText = (TextView) view.findViewById(R.id.textView5);
		reviewDetail = (TextView) view.findViewById(R.id.textView6);

		ReviewView reviewView = reviewViewList.get(position);

		clinicName.setText(reviewView.getKmClinicName());
		reviewDetail.setText(reviewView.getComment());
		location.setText(reviewView.getKmClinicBigRegionName() + " " + reviewView.getKmClinicMiddleRegionName());

		Log.d("kim", "ReviewAdapter favorate type is " + reviewView.getFavoriteType());

		return view;
	}

}
