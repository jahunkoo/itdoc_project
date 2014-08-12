package com.kmbridge.itdoc.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.hardcoding.LoadData;

public class RandomPath extends Activity {
	public static int randomPathClinic01[][] = new int[30][10];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// json파서 로드
		LoadData load = new LoadData(this);

		// 한의원 객체를 가져옴
		//KmClinicDetailView KmClinicview = load.getKmClinicDetailView(clinicNumber);
		List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
		//simpleList = KmClinicview.getUserSimpleInfoList();
		simpleList = load.getAllUserSimpleInfo();
		
		simpleList.get(0).getPicturePath();
		
		/*for(int j=0;j<5;j++)
		{
			String pictureName = simpleList.get(j).getPicturePath();
			String []arr = pictureName.split(".png");
			String resName = arr[0];
			//Log.d("kim4","Path : "+resName);
			int pictureId = getResources().getIdentifier(resName, "drawable", this.getPackageName());
			//Log.d("kim4","Resource :"+pictureId);
			likeUser[j].setImageResource(pictureId);
		}*/
	}

}
