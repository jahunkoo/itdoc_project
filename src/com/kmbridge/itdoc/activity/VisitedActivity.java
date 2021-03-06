package com.kmbridge.itdoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dialog.VisitedDialog;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.hardcoding.HardImageSelectHelperActivity;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class VisitedActivity extends HardImageSelectHelperActivity implements
		OnClickListener {
	// MainDialog mMainDialog;
	private VisitedDialog visitedDlg;
	private View.OnClickListener mClickListener;
	Button btnFaceNice;
	Button btnFaceNotbad;
	Button btnFaceBad;
	Button btnCamera;
	Button btnComplate;
	ImageView imgDisplayImage;
	TextView txtFaceSelectResult;

	EditText myEditText;
	ScrollView myScrollView;

	public TextView txtVisitedChoice;
	// TextView txtKeywordDisplay;
	public int clinicNumber;

	ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visited);

		// getActionBar().setDisplayHomeAsUpEnabled(true);

		// 인텐트로 넘겨준 값을 받아온다.
		Intent intent = getIntent();
		clinicNumber = intent.getExtras().getInt("clinicNumber");

		// json파서 로드
		LoadData load = new LoadData(this);
		// 한의원 객체를 가져옴
		KmClinicDetailView KmClinicview = load
				.getKmClinicDetailView(clinicNumber);
		// 병원 이름 지정
		getActionBar().setTitle(KmClinicview.getName());

		setLayout();
		setListener();

		// ActionBarActivity actionBarActivity = new ActionBarActivity();
		// actionBarActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getSelectedImageFile(); // extract selected & saved image file.
		/*
		 * ScrollView scroll = (ScrollView)findViewById(R.id.visited_scroll);
		 * scroll.post(new Runnable() {
		 * 
		 * @Override public void run() { ScrollView scroll =
		 * (ScrollView)findViewById(R.id.visited_scroll);
		 * scroll.fullScroll(ScrollView.FOCUS_UP);
		 * 
		 * } });
		 */

	}

	/*
	 * public static class MainDialog extends DialogFragment {
	 * 
	 * @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
	 * AlertDialog.Builder mBuilder = new AlertDialog.Builder( getActivity());
	 * LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();
	 * mBuilder.setView(mLayoutInflater.inflate(R.layout.dialog_visited, null));
	 * return mBuilder.create(); }
	 * 
	 * @Override public void onStop() { super.onStop(); }
	 * 
	 * }
	 * 
	 * public void ONCLICK_DIALOG(View v) { switch (v.getId()) { case
	 * R.id.dialogi_item0: keywordData+="한방 다이어트";
	 * txtKeywordDisplay.setText(keywordData); break; }
	 * 
	 * }
	 */

	private void setLayout() {
		btnFaceNice = (Button) findViewById(R.id.btn_face_nice);
		btnFaceNotbad = (Button) findViewById(R.id.btn_face_notbad);
		btnFaceBad = (Button) findViewById(R.id.btn_face_bad);
		txtFaceSelectResult = (TextView) findViewById(R.id.txt_face_select_result);
		btnCamera = (Button) findViewById(R.id.btn_camara);
		txtVisitedChoice = (TextView) findViewById(R.id.kmclinic_visited_choice);
		btnComplate = (Button) findViewById(R.id.btn_complate);
		imgDisplayImage = (ImageView) findViewById(R.id.imageview_visited);
		myEditText = (EditText) findViewById(R.id.edit_text_comment);
		myScrollView = (ScrollView) findViewById(R.id.myScrollView);

	}

	private void setListener() {
		btnFaceNice.setOnClickListener(this);
		btnFaceNotbad.setOnClickListener(this);
		btnFaceBad.setOnClickListener(this);
		btnCamera.setOnClickListener(this);
		txtVisitedChoice.setOnClickListener(this);
		btnComplate.setOnClickListener(this);
		myScrollView.setOnClickListener(this);
		myEditText.setOnClickListener(this);

	}

	private void callImageActivity() {
		super.callActivity = this;
		setImageSizeBoundary(400);
		setCropOption(1, 1);
		startSelectImage();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_face_nice:
			btnFaceNice.setBackgroundResource(R.drawable.img_good_on);
			btnFaceNotbad.setBackgroundResource(R.drawable.img_notbad_off);
			btnFaceBad.setBackgroundResource(R.drawable.img_bad_off);
			txtFaceSelectResult.setText("추천");
			break;
		case R.id.btn_face_notbad:
			btnFaceNice.setBackgroundResource(R.drawable.img_good_off);
			btnFaceNotbad.setBackgroundResource(R.drawable.img_notbad_on);
			btnFaceBad.setBackgroundResource(R.drawable.img_bad_off);
			txtFaceSelectResult.setText("괜찮다");
			break;
		case R.id.btn_face_bad:
			btnFaceNice.setBackgroundResource(R.drawable.img_good_off);
			btnFaceNotbad.setBackgroundResource(R.drawable.img_notbad_off);
			btnFaceBad.setBackgroundResource(R.drawable.img_bad_on);
			txtFaceSelectResult.setText("비추천");
			break;
		case R.id.btn_camara:
			callImageActivity();
			imgDisplayImage.setVisibility(View.VISIBLE);
			break;
		case R.id.kmclinic_visited_choice:
			visitedDlg = new VisitedDialog(this, mClickListener);
			visitedDlg.show();
			// dialogactivity.showDialog();
			// mMainDialog = new MainDialog();
			// mMainDialog.show(getFragmentManager(), "");
		case R.id.edit_text_comment:
			// myScrollView.scrollTo(0,600);
			scrollToEnd();
			Log.d("kim5", "scroll");
			break;
		case R.id.btn_complate:
			/*
			 * Intent intent = new Intent(this, KmClinicDetailActivity.class);
			 * intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			 * intent.putExtra("clinicNumber", clinicNumber);
			 * startActivity(intent);
			 */
			finish();
		}
	}

	/*public void scrollToEnd() {
		myScrollView.post(new Runnable() {
			@Override
			public void run() {
				myScrollView.fullScroll(View.FOCUS_DOWN);
			}

		});
	}*/

	public void scrollToEnd()
	{
		myScrollView.fullScroll(View.FOCUS_DOWN);
	}
}
