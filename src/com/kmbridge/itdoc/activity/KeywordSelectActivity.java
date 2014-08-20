package com.kmbridge.itdoc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class KeywordSelectActivity extends Activity implements OnClickListener {

	ImageButton searchItem[] = new ImageButton[14];
	boolean checkItem[] = new boolean[14];

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keyword_select);

		getActionBar().setTitle("관심 분야 설정");

		setLayout();
		setCheck();
		setListener();

		for (int i = 0; i < 14; i++) {
			checkItem[i] = false;
		}

	}

	private void setCheck() {
		if (SharedPreferenceUtil.isExist(this, "item1") == true) {
			searchItem[0].setBackgroundResource(R.drawable.skin_on);
		} if (SharedPreferenceUtil.isExist(this, "item1") == false) {
			searchItem[0].setBackgroundResource(R.drawable.skin_off);
		} if (SharedPreferenceUtil.isExist(this, "item2") == true) {
			searchItem[1].setBackgroundResource(R.drawable.nose_on);
		} if (SharedPreferenceUtil.isExist(this, "item2") == false) {
			searchItem[1].setBackgroundResource(R.drawable.nose_off);
		} if (SharedPreferenceUtil.isExist(this, "item3") == true) {
			searchItem[2].setBackgroundResource(R.drawable.diet_on);
		} if (SharedPreferenceUtil.isExist(this, "item3") == false) {
			searchItem[2].setBackgroundResource(R.drawable.diet_off);
		} if (SharedPreferenceUtil.isExist(this, "item4") == true) {
			searchItem[3].setBackgroundResource(R.drawable.disk_on);
		} if (SharedPreferenceUtil.isExist(this, "item4") == false) {
			searchItem[3].setBackgroundResource(R.drawable.disk_off);
		} if (SharedPreferenceUtil.isExist(this, "item5") == true) {
			searchItem[4].setBackgroundResource(R.drawable.beauty_on);
		} if (SharedPreferenceUtil.isExist(this, "item5") == false) {
			searchItem[4].setBackgroundResource(R.drawable.beauty_off);
		} if (SharedPreferenceUtil.isExist(this, "item6") == true) {
			searchItem[5].setBackgroundResource(R.drawable.child_on);
		} if (SharedPreferenceUtil.isExist(this, "item6") == false) {
			searchItem[5].setBackgroundResource(R.drawable.child_off);
		} if (SharedPreferenceUtil.isExist(this, "item7") == true) {
			searchItem[6].setBackgroundResource(R.drawable.oriental_medicine_on);
		} if (SharedPreferenceUtil.isExist(this, "item7") == false) {
			searchItem[6].setBackgroundResource(R.drawable.oriental_medicine_off);
		} if (SharedPreferenceUtil.isExist(this, "item8") == true) {
			searchItem[7].setBackgroundResource(R.drawable.stomach_on);
		} if (SharedPreferenceUtil.isExist(this, "item8") == false) {
			searchItem[7].setBackgroundResource(R.drawable.stomach_off);
		} if (SharedPreferenceUtil.isExist(this, "item9") == true) {
			searchItem[8].setBackgroundResource(R.drawable.mother_on);
		} if (SharedPreferenceUtil.isExist(this, "item9") == false) {
			searchItem[8].setBackgroundResource(R.drawable.mother_off);
		} if (SharedPreferenceUtil.isExist(this, "item10") == true) {
			searchItem[9].setBackgroundResource(R.drawable.man_on);
		} if (SharedPreferenceUtil.isExist(this, "item10") == false) {
			searchItem[9].setBackgroundResource(R.drawable.man_off);
		} if (SharedPreferenceUtil.isExist(this, "item11") == true) {
			searchItem[10].setBackgroundResource(R.drawable.needle_on);
		} if (SharedPreferenceUtil.isExist(this, "item11") == false) {
			searchItem[10].setBackgroundResource(R.drawable.needle_off);
		} if (SharedPreferenceUtil.isExist(this, "item12") == true) {
			searchItem[11].setBackgroundResource(R.drawable.oldman_on);
		} if (SharedPreferenceUtil.isExist(this, "item12") == false) {
			searchItem[11].setBackgroundResource(R.drawable.oldman_off);
		} if (SharedPreferenceUtil.isExist(this, "item13") == true) {
			searchItem[12].setBackgroundResource(R.drawable.stomach_on);
		} if (SharedPreferenceUtil.isExist(this, "item13") == false) {
			searchItem[12].setBackgroundResource(R.drawable.stomach_off);
		} if (SharedPreferenceUtil.isExist(this, "item14") == true) {
			searchItem[13].setBackgroundResource(R.drawable.bald_on);
		} if (SharedPreferenceUtil.isExist(this, "item14") == false) {
			searchItem[13].setBackgroundResource(R.drawable.bald_off);
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
				SharedPreferenceUtil.setData(this, "item1", "check");
			} else {
				searchItem[0].setBackgroundResource(R.drawable.skin_off);
				checkItem[0] = false;
				SharedPreferenceUtil.setData(this, "item1", null);
			}
			break;
		case R.id.search_item2:
			if (checkItem[1] == false) {
				searchItem[1].setBackgroundResource(R.drawable.nose_on);
				checkItem[1] = true;
				SharedPreferenceUtil.setData(this, "item2", "check");
			} else {
				searchItem[1].setBackgroundResource(R.drawable.nose_off);
				checkItem[1] = false;
				SharedPreferenceUtil.setData(this, "item2", null);
			}
			break;
		case R.id.search_item3:
			if (checkItem[2] == false) {
				searchItem[2].setBackgroundResource(R.drawable.diet_on);
				checkItem[2] = true;
				SharedPreferenceUtil.setData(this, "item3", "check");
			} else {
				searchItem[2].setBackgroundResource(R.drawable.diet_off);
				checkItem[2] = false;
				SharedPreferenceUtil.setData(this, "item3", null);
			}
			break;
		case R.id.search_item4:
			if (checkItem[3] == false) {
				searchItem[3].setBackgroundResource(R.drawable.disk_on);
				checkItem[3] = true;
				SharedPreferenceUtil.setData(this, "item4", "check");
			} else {
				searchItem[3].setBackgroundResource(R.drawable.disk_off);
				checkItem[3] = false;
				SharedPreferenceUtil.setData(this, "item4", null);
			}
			break;
		case R.id.search_item5:
			if (checkItem[4] == false) {
				searchItem[4].setBackgroundResource(R.drawable.beauty_on);
				checkItem[4] = true;
				SharedPreferenceUtil.setData(this, "item5", "check");
			} else {
				searchItem[4].setBackgroundResource(R.drawable.beauty_off);
				checkItem[4] = false;
				SharedPreferenceUtil.setData(this, "item5", null);
			}
			break;
		case R.id.search_item6:
			if (checkItem[5] == false) {
				searchItem[5].setBackgroundResource(R.drawable.child_on);
				checkItem[5] = true;
				SharedPreferenceUtil.setData(this, "item6", "check");
			} else {
				searchItem[5].setBackgroundResource(R.drawable.child_off);
				checkItem[5] = false;
				SharedPreferenceUtil.setData(this, "item6", null);
			}
			break;
		case R.id.search_item7:
			if (checkItem[6] == false) {
				searchItem[6]
						.setBackgroundResource(R.drawable.oriental_medicine_on);
				checkItem[6] = true;
				SharedPreferenceUtil.setData(this, "item7", "check");
			} else {
				searchItem[6]
						.setBackgroundResource(R.drawable.oriental_medicine_off);
				checkItem[6] = false;
				SharedPreferenceUtil.setData(this, "item7", null);
			}
			break;
		case R.id.search_item8:
			if (checkItem[7] == false) {
				searchItem[7].setBackgroundResource(R.drawable.stomach_on);
				checkItem[7] = true;
				SharedPreferenceUtil.setData(this, "item8", "check");
			} else {
				searchItem[7].setBackgroundResource(R.drawable.stomach_off);
				checkItem[7] = false;
				SharedPreferenceUtil.setData(this, "item8", null);
			}
			break;
		case R.id.search_item9:
			if (checkItem[8] == false) {
				searchItem[8].setBackgroundResource(R.drawable.mother_on);
				checkItem[8] = true;
				SharedPreferenceUtil.setData(this, "item9", "check");
			} else {
				searchItem[8].setBackgroundResource(R.drawable.mother_off);
				checkItem[8] = false;
				SharedPreferenceUtil.setData(this, "item9", null);
			}
			break;
		case R.id.search_item10:
			if (checkItem[9] == false) {
				searchItem[9].setBackgroundResource(R.drawable.man_on);
				checkItem[9] = true;
				SharedPreferenceUtil.setData(this, "item10", "check");
			} else {
				searchItem[9].setBackgroundResource(R.drawable.man_off);
				checkItem[9] = false;
				SharedPreferenceUtil.setData(this, "item10", null);
			}
			break;
		case R.id.search_item11:
			if (checkItem[10] == false) {
				searchItem[10].setBackgroundResource(R.drawable.needle_on);
				checkItem[10] = true;
				SharedPreferenceUtil.setData(this, "item11", "check");
			} else {
				searchItem[10].setBackgroundResource(R.drawable.needle_off);
				checkItem[10] = false;
				SharedPreferenceUtil.setData(this, "item11", null);
			}
			break;
		case R.id.search_item12:
			if (checkItem[11] == false) {
				searchItem[11].setBackgroundResource(R.drawable.oldman_on);
				checkItem[11] = true;
				SharedPreferenceUtil.setData(this, "item12", "check");
			} else {
				searchItem[11].setBackgroundResource(R.drawable.oldman_off);
				checkItem[11] = false;
				SharedPreferenceUtil.setData(this, "item12", null);
			}
			break;
		case R.id.search_item13:
			if (checkItem[12] == false) {
				searchItem[12].setBackgroundResource(R.drawable.stomach_on);
				checkItem[12] = true;
				SharedPreferenceUtil.setData(this, "item13", "check");
			} else {
				searchItem[12].setBackgroundResource(R.drawable.stomach_off);
				checkItem[12] = false;
				SharedPreferenceUtil.setData(this, "item13", null);
			}
			break;
		case R.id.search_item14:
			if (checkItem[13] == false) {
				searchItem[13].setBackgroundResource(R.drawable.bald_on);
				checkItem[13] = true;
				SharedPreferenceUtil.setData(this, "item14", "check");
			} else {
				searchItem[13].setBackgroundResource(R.drawable.bald_off);
				checkItem[13] = false;
				SharedPreferenceUtil.setData(this, "item14", null);
			}
			break;

		}
	}

	public void keywordToggle() {

	}

}
