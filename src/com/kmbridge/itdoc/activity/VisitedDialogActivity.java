package com.kmbridge.itdoc.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.kmbridge.itdoc.R;

public class VisitedDialogActivity extends Dialog implements OnClickListener {

	private Context mContext = null;
	private View.OnClickListener mClickListener;
	private TextView txtkeywordDisplay;
	private TextView txtkeywordGuide;
	private TextView item0;
	private TextView item1;
	private TextView item2;
	private TextView item3;
	private TextView item4;
	
	private String keywordResult="";

	public VisitedDialogActivity(Context context,
			View.OnClickListener clicklistener) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setCanceledOnTouchOutside(false);// 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
		this.setCancelable(true); // 백키로 다이알로그 닫기

		mContext = context;
		mClickListener = clicklistener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visited_dialog);
		
		setLayout();
		setListener();
	}

	private void setLayout() {
		item0 = (TextView) findViewById(R.id.dialogi_item0);
		item1 = (TextView) findViewById(R.id.dialogi_item1);
		item2 = (TextView) findViewById(R.id.dialogi_item2);
		item3 = (TextView) findViewById(R.id.dialogi_item3);
		item4 = (TextView) findViewById(R.id.dialogi_item4);
		txtkeywordDisplay = (TextView) findViewById(R.id.txt_keyword_display);
		txtkeywordGuide = (TextView) findViewById(R.id.txt_keyword_guide);
		
	}

	private void setListener() {
		item0.setOnClickListener(this);
		item1.setOnClickListener(this);
		item2.setOnClickListener(this);
		item3.setOnClickListener(this);
		item4.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialogi_item0:
			txtkeywordGuide.setText("또 다른 키워드를 선택하세요.");
			keywordResult += "한방 다이어트 ";
			txtkeywordDisplay.setText(keywordResult);
			break;
		case R.id.dialogi_item1:
			txtkeywordGuide.setText("또 다른 키워드를 선택하세요.");
			keywordResult += "불임 ";
			txtkeywordDisplay.setText(keywordResult);
			break;
		case R.id.dialogi_item2:
			txtkeywordGuide.setText("또 다른 키워드를 선택하세요.");
			keywordResult += "변비 ";
			txtkeywordDisplay.setText(keywordResult);
			break;
		case R.id.dialogi_item3:
			txtkeywordGuide.setText("또 다른 키워드를 선택하세요.");
			keywordResult += "피부 ";
			txtkeywordDisplay.setText(keywordResult);
			break;
		case R.id.dialogi_item4:
			txtkeywordGuide.setText("또 다른 키워드를 선택하세요.");
			keywordResult += "탈모 ";
			txtkeywordDisplay.setText(keywordResult);
			break;
		}
	}
	
	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
