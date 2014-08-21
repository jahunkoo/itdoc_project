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
		ImageView recommendImgGood;
		ImageView recommendImgSoso;
		ImageView recommendImgBad;
		TextView clinicName;
		TextView date;
		TextView recommendText;
		TextView reviewDetail;
		TextView keyword;

		if (view == null) {
			view = inflator.inflate(R.layout.review_list_item_1, parent, false);
			view.setTag((Integer) position);
		}

		face = (ImageView) view.findViewById(R.id.imageView1);
		recommendImgGood = (ImageView) view.findViewById(R.id.ImageView02);
		recommendImgSoso = (ImageView) view.findViewById(R.id.ImageView01);
		recommendImgBad = (ImageView) view.findViewById(R.id.imageView2);

		clinicName = (TextView) view.findViewById(R.id.textView1);
		date = (TextView) view.findViewById(R.id.textView3);
		recommendText = (TextView) view.findViewById(R.id.textView5);
		reviewDetail = (TextView) view.findViewById(R.id.textView6);
		keyword = (TextView) view.findViewById(R.id.textView4);

		ReviewView reviewView = reviewViewList.get(position);

		clinicName.setText(reviewView.getKmClinicName());
		reviewDetail.setText(reviewView.getComment());

		// 아직 리뷰 키워드가 없음
		// keyword.setText(reviewView.getReviewKeywordList().get(0).getKeyword());

		recommendImgGood.setImageResource(R.drawable.emoticon_good_grey);
		recommendImgSoso.setImageResource(R.drawable.emoticon_soso_grey);
		recommendImgBad.setImageResource(R.drawable.emoticon_bad_grey);

		String pictureName;

		if (reviewView.getKmClinicId() <= 10) {
			pictureName = "doctor" + toString().valueOf(reviewView.getKmClinicId());
		} else {
			pictureName = "doctor" + toString().valueOf(reviewView.getKmClinicId() - 10);
		}

		int pictureId = context.getResources().getIdentifier(pictureName, "drawable", context.getPackageName());

		face.setImageResource(pictureId);

		switch (reviewView.getFavoriteType()) {
		case 1:
			recommendImgGood.setImageResource(R.drawable.emoticon_good_red);
//			recommendText.setText("추천");
			break;
		case 2:
			recommendImgSoso.setImageResource(R.drawable.emoticon_soso_red);
//			recommendText.setText("괜찮다");
			break;
		case 3:
			recommendImgBad.setImageResource(R.drawable.emoticon_bad_red);
//			recommendText.setText("비추천");
			break;
		default:
			recommendImgSoso.setImageResource(R.drawable.emoticon_soso_red);
			break;
		}

		return view;
	}

}
