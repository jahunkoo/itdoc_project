package com.kmbridge.itdoc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kmbridge.itdoc.R;

public class VisitedDialog extends Dialog implements OnClickListener {
	
	

	private Context mContext = null;
	private View.OnClickListener mClickListener;
	private TextView txtkeywordDisplay;
	private TextView txtkeywordGuide;
	private TextView item0;
	private TextView item1;
	private TextView item2;
	private TextView item3;
	private TextView item4;
	
	boolean check_item0 = true;
	boolean check_item1 = true;
	boolean check_item2 = true;
	boolean check_item3 = true;
	boolean check_item4 = true;
	
	private Button btnDigComplete;
	
	String strColor = "#DBC000";
	
	public String keywordResult="";

	public VisitedDialog(Context context,View.OnClickListener clicklistener) {
		super(context);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//this.setCanceledOnTouchOutside(false);// 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
		//this.setCancelable(true); // 백키로 다이알로그 닫기

		mContext = context;
		mClickListener = clicklistener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visited_dialog);
		setTitle("	어떤 치료를 받았나요?");
		setLayout();
		setListener();
	}

	private void setLayout() {
		item0 = (TextView) findViewById(R.id.dialog_item0);
		item1 = (TextView) findViewById(R.id.dialog_item1);
		item2 = (TextView) findViewById(R.id.dialog_item2);
		item3 = (TextView) findViewById(R.id.dialog_item3);
		item4 = (TextView) findViewById(R.id.dialog_item4);
		txtkeywordDisplay = (TextView) findViewById(R.id.txt_keyword_display);
		txtkeywordGuide = (TextView) findViewById(R.id.txt_keyword_guide);
		btnDigComplete = (Button) findViewById(R.id.btn_dialog_complete);
		
	}

	private void setListener() {
		item0.setOnClickListener(this);
		item1.setOnClickListener(this);
		item2.setOnClickListener(this);
		item3.setOnClickListener(this);
		item4.setOnClickListener(this);
		btnDigComplete.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_item0:
			if(check_item0==true)
			{
				setDlgLayout("한방 다이어트",item0);
				check_item0=false;
			}
			else Toast.makeText(mContext, "이미 등록된 키워드 입니다.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.dialog_item1:
			if(check_item1==true)
			{
				setDlgLayout("불임",item1);
				check_item1=false;
			}
			else Toast.makeText(mContext, "이미 등록된 키워드 입니다.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.dialog_item2:
			if(check_item2==true)
			{
				setDlgLayout("변비",item2);
				check_item2=false;
			}
			else Toast.makeText(mContext, "이미 등록된 키워드 입니다.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.dialog_item3:
			if(check_item3==true)
			{
				setDlgLayout("피부",item3);
				check_item3=false;
			}
			else Toast.makeText(mContext, "이미 등록된 키워드 입니다.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.dialog_item4:
			if(check_item4==true)
			{
				setDlgLayout("탈모",item4);
				check_item4=false;
			}
			else Toast.makeText(mContext, "이미 등록된 키워드 입니다.", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.btn_dialog_complete:
			//SharedPreferenceUtil.setData(mContext, "keyword_selected", keywordResult);
			//VisitedActivity visitedActivity = new VisitedActivity();
			//visitedActivity.txtVisitedChoice.setText(keywordResult);
			dismiss();
			break;
			
		}
	}
	
	public void setDlgLayout(String keywordResult, TextView item)
	{
		txtkeywordGuide.setText("또 다른 키워드를 선택하세요.");
		this.keywordResult += keywordResult+" ";
		txtkeywordDisplay.setText(this.keywordResult);
		item.setTextColor(Color.parseColor(strColor));
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
