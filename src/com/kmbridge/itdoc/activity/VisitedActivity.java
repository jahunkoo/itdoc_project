package com.kmbridge.itdoc.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.dialog.VisitedDialogActivity;

public class VisitedActivity extends ImageSelectHelperActivity implements OnClickListener {
	//MainDialog mMainDialog;
	private VisitedDialogActivity visitedDlg;
	private View.OnClickListener mClickListener;
	Button btnFaceNice;
	Button btnFaceNotbad;
	Button btnFaceBad;
	Button btnCamera;
	TextView txtFaceSelectResult;
	public TextView txtVisitedChoice;
	//TextView txtKeywordDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visited);

		setLayout();
		setListener();
		
		getSelectedImageFile(); // extract selected & saved image file.
		
	}
	

/*	public static class MainDialog extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder mBuilder = new AlertDialog.Builder(
					getActivity());
			LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();
			mBuilder.setView(mLayoutInflater.inflate(R.layout.dialog_visited, null));
			return mBuilder.create();
		}

		@Override
		public void onStop() {
			super.onStop();
		}

	}
	
	public void ONCLICK_DIALOG(View v) {
		switch (v.getId()) {
		case R.id.dialogi_item0:
			keywordData+="한방 다이어트";
			txtKeywordDisplay.setText(keywordData);
			break;
		}

	}*/


	private void setLayout() {
		btnFaceNice = (Button) findViewById(R.id.btn_face_nice);
		btnFaceNotbad = (Button) findViewById(R.id.btn_face_notbad);
		btnFaceBad = (Button) findViewById(R.id.btn_face_bad);
		txtFaceSelectResult = (TextView) findViewById(R.id.txt_face_select_result);
		btnCamera = (Button) findViewById(R.id.btn_camara);
		txtVisitedChoice = (TextView) findViewById(R.id.kmclinic_visited_choice);
	}

	private void setListener() {
		btnFaceNice.setOnClickListener(this);
		btnFaceNotbad.setOnClickListener(this);
		btnFaceBad.setOnClickListener(this);
		btnCamera.setOnClickListener(this);
		txtVisitedChoice.setOnClickListener(this);
	}

	private void callImageActivity(){
		super.callActivity = this;
		setImageSizeBoundary(400);
		setCropOption(1, 1);
		startSelectImage();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_face_nice:
			btnFaceNice.setBackgroundResource(R.drawable.face_nice_on);
			btnFaceNotbad.setBackgroundResource(R.drawable.face_notbad_off);
			btnFaceBad.setBackgroundResource(R.drawable.face_bad_off);
			txtFaceSelectResult.setText("추천");
			break;
		case R.id.btn_face_notbad:
			btnFaceNice.setBackgroundResource(R.drawable.face_nice_off);
			btnFaceNotbad.setBackgroundResource(R.drawable.face_notbad_on);
			btnFaceBad.setBackgroundResource(R.drawable.face_bad_off);
			txtFaceSelectResult.setText("괜찮다");
			break;
		case R.id.btn_face_bad:
			btnFaceNice.setBackgroundResource(R.drawable.face_nice_off);
			btnFaceNotbad.setBackgroundResource(R.drawable.face_notbad_off);
			btnFaceBad.setBackgroundResource(R.drawable.face_bad_on);
			txtFaceSelectResult.setText("비추천");
			break;
		case R.id.btn_camara:
			callImageActivity();
			break;
		case R.id.kmclinic_visited_choice:
			visitedDlg = new VisitedDialogActivity(this, mClickListener);
			visitedDlg.show();
			//dialogactivity.showDialog();
			//mMainDialog = new MainDialog();
			//mMainDialog.show(getFragmentManager(), "");
		}
	}

}
