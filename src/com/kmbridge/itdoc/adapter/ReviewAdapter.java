package com.kmbridge.itdoc.adapter;

import java.util.List;

import lazyList.ImageLoader;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dto.ReviewKeyword;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.util.ItDocConstants;

public class ReviewAdapter extends ArrayAdapter<ReviewView> {

	private List<ReviewView> reviewViewList;
	private FragmentActivity activity;
	private LayoutInflater inflator;
	public ImageLoader imageLoader;
	
	public ReviewAdapter(Context context, int textViewResourceId, List<ReviewView> reviewViewList) {
		super(context, textViewResourceId, reviewViewList);
		
		this.reviewViewList = reviewViewList;
		this.activity = (FragmentActivity) context;
		imageLoader=new ImageLoader(activity.getApplicationContext());
		inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null) view = inflator.inflate(R.layout.layout_review, null);
		
		Log.d("koo", "getView position:"+position);
		ReviewView reviewView = reviewViewList.get(position);
		
		ImageView kmClinicImgView = (ImageView) view.findViewById(R.id.imageview_layout_review_profile);
		
		TextView kmCLinicNameTextView = (TextView) view.findViewById(R.id.textview_layout_review_kmclinic_name);
		TextView kmClinicLocationTextView = (TextView) view.findViewById(R.id.textview_layout_review_kmclinic_location);
		TextView kmClinicDateTextView = (TextView) view.findViewById(R.id.textview_layout_review_date);
		TextView keywordsTextView = (TextView) view.findViewById(R.id.textview_layout_review_keywords);
		ImageView emotionImgView = (ImageView) view.findViewById(R.id.imageview_layout_review_emotion);
		TextView commentTextView = (TextView) view.findViewById(R.id.textview_layout_review_comment);
		
		
		kmCLinicNameTextView.setText( reviewView.getKmClinicName() );
		String fullLocation = reviewView.getKmClinicBigRegionName() +" "+ reviewView.getKmClinicMiddleRegionName() +" "+reviewView.getKmClinicRemainRegion(); 
		kmClinicLocationTextView.setText( fullLocation );
		kmClinicDateTextView.setText( reviewView.getReviewTime() );
		
		List<ReviewKeyword> reviewKeywordList = reviewView.getReviewKeywordList();
		StringBuffer keywordBuffer = new StringBuffer();
		for(int i=0;i<reviewKeywordList.size();i++){
			ReviewKeyword reviewKeyword = reviewKeywordList.get(i);
			if(i == reviewKeywordList.size()-1){
				keywordBuffer.append(reviewKeyword.getKeyword());
			}
			keywordBuffer.append(reviewKeyword.getKeyword()).append(",");
		}
		keywordsTextView.setText(keywordBuffer.toString());
		
		int favoriteType = reviewView.getFavoriteType();
		switch(favoriteType){
		case ItDocConstants.FAVORITE_GOOD:
			emotionImgView.setImageResource(R.drawable.emoticon_good_red);break;
		case ItDocConstants.FAVORITE_SOSO:
			emotionImgView.setImageResource(R.drawable.emoticon_soso_red);break;
		case ItDocConstants.FAVORITE_BAD:
			emotionImgView.setImageResource(R.drawable.emoticon_bad_red);break;
		}
		
		commentTextView.setText(reviewView.getComment());
		
		return view;
	}

	
	
	
	
}
