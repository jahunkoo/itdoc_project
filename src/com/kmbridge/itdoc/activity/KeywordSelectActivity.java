package com.kmbridge.itdoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.kmbridge.itdoc.R;

public class KeywordSelectActivity extends Activity implements OnClickListener {

	ImageButton searchItem[] = new ImageButton[14];
	boolean checkItem[] = new boolean[14];
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keyword_select);

		getActionBar().setTitle("관심 분야 설정");

		setLayout();
		setListener();
		
		for(int i=0;i<14;i++)
		{
			checkItem[i]= false;
		}

	}

	private void setLayout() {
		searchItem[0] = (ImageButton) findViewById(R.id.search_item1);
		searchItem[1] = (ImageButton) findViewById(R.id.search_item2);
		searchItem[2] = (ImageButton) findViewById(R.id.search_item3);
		searchItem[3] = (ImageButton) findViewById(R.id.search_item4);
		searchItem[4] = (ImageButton) findViewById(R.id.search_item5);
		searchItem[5] = (ImageButton) findViewById(R.id.search_item6);
		searchItem[6] = (ImageButton) findViewById(R.id.search_item7);
		searchItem[7] = (ImageButton) findViewById(R.id.search_item8);
		searchItem[8] = (ImageButton) findViewById(R.id.search_item9);
		searchItem[9] = (ImageButton) findViewById(R.id.search_item10);
		searchItem[10] = (ImageButton) findViewById(R.id.search_item11);
		searchItem[11] = (ImageButton) findViewById(R.id.search_item12);
		searchItem[12] = (ImageButton) findViewById(R.id.search_item13);
		searchItem[13] = (ImageButton) findViewById(R.id.search_item14);

	}

	private void setListener() {
		for (int i = 0; i < 14; i++) {
			searchItem[i].setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search_item1:
			if (checkItem[0] == false) {
				searchItem[0].setBackgroundResource(R.drawable.skin_on);
				checkItem[0] = true;
			} else {
				searchItem[0].setBackgroundResource(R.drawable.skin_off);
				checkItem[0] = false;
			}
			break;
		case R.id.search_item2:
			if (checkItem[1] == false) {
				searchItem[1].setBackgroundResource(R.drawable.nose_on);
				checkItem[1] = true;
			} else {
				searchItem[1].setBackgroundResource(R.drawable.nose_off);
				checkItem[1] = false;
			}
			break;
		case R.id.search_item3:
			if (checkItem[2] == false) {
				searchItem[2].setBackgroundResource(R.drawable.diet_on);
				checkItem[2] = true;
			} else {
				searchItem[2].setBackgroundResource(R.drawable.diet_off);
				checkItem[2] = false;
			}
			break;
		case R.id.search_item4:
			if (checkItem[3] == false) {
				searchItem[3].setBackgroundResource(R.drawable.disk_on);
				checkItem[3] = true;
			} else {
				searchItem[3].setBackgroundResource(R.drawable.disk_off);
				checkItem[3] = false;
			}
			break;
		case R.id.search_item5:
			if (checkItem[4] == false) {
				searchItem[4].setBackgroundResource(R.drawable.beauty_on);
				checkItem[4] = true;
			} else {
				searchItem[4].setBackgroundResource(R.drawable.beauty_off);
				checkItem[4] = false;
			}
			break;
		case R.id.search_item6:
			if (checkItem[5] == false) {
				searchItem[5].setBackgroundResource(R.drawable.child_on);
				checkItem[5] = true;
			} else {
				searchItem[5].setBackgroundResource(R.drawable.child_off);
				checkItem[5] = false;
			}
			break;
		case R.id.search_item7:
			if (checkItem[6] == false) {
				searchItem[6].setBackgroundResource(R.drawable.oriental_medicine_on);
				checkItem[6] = true;
			} else {
				searchItem[6].setBackgroundResource(R.drawable.oriental_medicine_off);
				checkItem[6] = false;
			}
			break;
		case R.id.search_item8:
			if (checkItem[7] == false) {
				searchItem[7].setBackgroundResource(R.drawable.stomach_on);
				checkItem[7] = true;
			} else {
				searchItem[7].setBackgroundResource(R.drawable.stomach_off);
				checkItem[7] = false;
			}
			break;
		case R.id.search_item9:
			if (checkItem[8] == false) {
				searchItem[8].setBackgroundResource(R.drawable.mother_on);
				checkItem[8] = true;
			} else {
				searchItem[8].setBackgroundResource(R.drawable.mother_off);
				checkItem[8] = false;
			}
			break;
		case R.id.search_item10:
			if (checkItem[9] == false) {
				searchItem[9].setBackgroundResource(R.drawable.man_on);
				checkItem[9] = true;
			} else {
				searchItem[9].setBackgroundResource(R.drawable.man_off);
				checkItem[9] = false;
			}
			break;
		case R.id.search_item11:
			if (checkItem[10] == false) {
				searchItem[10].setBackgroundResource(R.drawable.needle_on);
				checkItem[10] = true;
			} else {
				searchItem[10].setBackgroundResource(R.drawable.needle_off);
				checkItem[10] = false;
			}
			break;
		case R.id.search_item12:
			if (checkItem[11] == false) {
				searchItem[11].setBackgroundResource(R.drawable.oldman_on);
				checkItem[11] = true;
			} else {
				searchItem[11].setBackgroundResource(R.drawable.oldman_off);
				checkItem[11] = false;
			}
			break;
		case R.id.search_item13:
			if (checkItem[12] == false) {
				searchItem[12].setBackgroundResource(R.drawable.stomach_on);
				checkItem[12] = true;
			} else {
				searchItem[12].setBackgroundResource(R.drawable.stomach_off);
				checkItem[12] = false;
			}
			break;
		case R.id.search_item14:
			if (checkItem[13] == false) {
				searchItem[13].setBackgroundResource(R.drawable.bald_on);
				checkItem[13] = true;
			} else {
				searchItem[13].setBackgroundResource(R.drawable.bald_off);
				checkItem[13] = false;
			}
			break;

		}
	}
	
	public void keywordToggle()
	{
		
	}

}
