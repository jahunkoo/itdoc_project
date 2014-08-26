package com.kmbridge.itdoc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.kmbridge.itdoc.R;

public class DoctorRecommendDialog extends Dialog implements android.view.View.OnClickListener{

	TextView complete;
	
	
	public DoctorRecommendDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_doctor_recommend);
		
		
		
		setElements();
		
	}
	
	private void setElements() {
		
		complete = (TextView) findViewById(R.id.textView1);
		
		complete.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.textView1:
			dismiss();
			break;

		default:
			break;
		}
		
		
	}
	

}
