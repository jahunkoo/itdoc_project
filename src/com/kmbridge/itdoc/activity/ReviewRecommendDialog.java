package com.kmbridge.itdoc.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.thread.ReviewDialogHandler;

public class ReviewRecommendDialog extends Dialog implements android.view.View.OnClickListener {

	private static  SeeAllReviewListActivity SeeAllReviewListActivity = null;

	private Context context;

	ImageView good;
	ImageView soso;
	ImageView bad;
	TextView complete;
	ReviewDialogHandler mHandler;
	LinearLayout lyt_good;
	LinearLayout lyt_soso;
	LinearLayout lyt_bad;

	TextView txt1;
	TextView txt2;
	TextView txt3;
	TextView txt4;
	TextView txt5;
	TextView txt6;

	boolean bGood = true;
	boolean bSoso = true;
	boolean bBad = true;

	/**
	 * 
	 * 리뷰 보기 설정을 눌렀을 때 어떤 리뷰를 볼 지 세팅 한다.
	 * @param bGood 추천 한의원 보기에 대한 boolean값 세팅
	 * @param bSoso 괜찮다 한의원 보기에 대한 boolean값 세팅
	 * @param bBad 비추천 한의원 보기에 대한 boolean값 세팅
	 */
	public ReviewRecommendDialog(Context context, boolean bGood, boolean bSoso, boolean bBad) {
		super(context);

		this.context = context;
		this.bGood = bGood;
		this.bSoso = bSoso;
		this.bBad = bBad;

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(true);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_review_recommend);

		setElements();

		
		mHandler = new ReviewDialogHandler(context);

	}

	private void setElements() {

		good = (ImageView) findViewById(R.id.imageView_review_recommend_good);
		soso = (ImageView) findViewById(R.id.imageView_review_recommend_soso);
		bad = (ImageView) findViewById(R.id.imageView_review_recommend_bad);
		complete = (TextView) findViewById(R.id.textView_review_recommend_complete);
		lyt_good = (LinearLayout) findViewById(R.id.linearlayout_review_recommend_good);
		lyt_soso = (LinearLayout) findViewById(R.id.linearlayout_review_recommend_soso);
		lyt_bad = (LinearLayout) findViewById(R.id.linearlayout_review_recommend_bad);

		txt1 = (TextView) findViewById(R.id.textView1);
		txt2 = (TextView) findViewById(R.id.textView4);
		txt3 = (TextView) findViewById(R.id.textView2);
		txt4 = (TextView) findViewById(R.id.textView5);
		txt5 = (TextView) findViewById(R.id.textView3);
		txt6 = (TextView) findViewById(R.id.textView6);

		good.setOnClickListener(this);
		soso.setOnClickListener(this);
		bad.setOnClickListener(this);

		lyt_good.setOnClickListener(this);
		lyt_soso.setOnClickListener(this);
		lyt_bad.setOnClickListener(this);

		complete.setOnClickListener(this);
		
		// 최초 다이얼로그 띄울 시 색을 boolean에 맞게 세팅
		if (bGood) {
			goodRed();
		} else {
			goodGrey();
		}
		if (bSoso) {
			sosoRed();
		} else {
			sosoGrey();
		}
		if (bBad) {
			badRed();
		} else {
			badGray();
		}
		
		SeeAllReviewListActivity = (SeeAllReviewListActivity) context;
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.linearlayout_review_recommend_good:
		case R.id.imageView_review_recommend_good:

			if (bGood) {
				goodGrey();
				bGood = false;
			} else {
				goodRed();
				bGood = true;
			}

			mHandler.sendEmptyMessage(mHandler.MSG_LIKE);
			break;

		case R.id.imageView_review_recommend_soso:
		case R.id.linearlayout_review_recommend_soso:

			if (bSoso) {
				sosoGrey();
				bSoso = false;
			} else {
				sosoRed();
				bSoso = true;
			}
			mHandler.sendEmptyMessage(mHandler.MSG_SOSO);
			break;

		case R.id.imageView_review_recommend_bad:
		case R.id.linearlayout_review_recommend_bad:

			if (bBad) {
				badGray();
				bBad = false;
			} else {
				badRed();
				bBad = true;
			}
			mHandler.sendEmptyMessage(mHandler.MSG_BAD);
			break;
		case R.id.textView_review_recommend_complete:
			mHandler.sendEmptyMessage(mHandler.MSG_COMPLETE);
			SeeAllReviewListActivity.setbGood(bGood);
			SeeAllReviewListActivity.setbSoso(bSoso);
			SeeAllReviewListActivity.setbBad(bBad);
			SeeAllReviewListActivity.adapterChange();
			dismiss();
			break;

		default:
			break;
		}
	}

	private void goodRed() {
		good.setImageResource(R.drawable.emoticon_good_red);
		txt1.setTextColor(Color.parseColor("#FF0000"));
		txt2.setTextColor(Color.parseColor("#FF0000"));
	}

	private void goodGrey() {
		good.setImageResource(R.drawable.emoticon_good_grey);
		txt1.setTextColor(Color.parseColor("#DCDCDC"));
		txt2.setTextColor(Color.parseColor("#DCDCDC"));
	}

	private void sosoRed() {
		soso.setImageResource(R.drawable.emoticon_soso_red);
		txt3.setTextColor(Color.parseColor("#FF0000"));
		txt4.setTextColor(Color.parseColor("#FF0000"));
	}

	private void sosoGrey() {
		soso.setImageResource(R.drawable.emoticon_soso_grey);
		txt3.setTextColor(Color.parseColor("#DCDCDC"));
		txt4.setTextColor(Color.parseColor("#DCDCDC"));
	}

	private void badRed() {
		bad.setImageResource(R.drawable.emoticon_bad_red);
		txt5.setTextColor(Color.parseColor("#FF0000"));
		txt6.setTextColor(Color.parseColor("#FF0000"));
	}

	private void badGray() {
		bad.setImageResource(R.drawable.emoticon_bad_grey);
		txt5.setTextColor(Color.parseColor("#DCDCDC"));
		txt6.setTextColor(Color.parseColor("#DCDCDC"));
	}
	
}
