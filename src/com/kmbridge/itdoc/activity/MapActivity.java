package com.kmbridge.itdoc.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicDetailView;

public class MapActivity extends FragmentActivity {
	
	private ConnectionBridge bridge = new ConnectionBridge();
	private String methodUrl = "getDetailKmClinic";
	private String clinicName; //한의원 이름 저장 변수
	private String mapLocation; //맵 포인트 저장 변수
	private String latitude; //위도
	private String longitude; //경도
	
	GoogleMap HaniMap;
	/*LatLng loc = new LatLng(37.49927, 127.03748); 
	CameraPosition cp = new CameraPosition.Builder().target((loc)).zoom(16).build();*/
	//MarkerOptions marker = new MarkerOptions().position(loc); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		LatLng loc = new LatLng(37.49927, 127.03748);
		CameraPosition cp = new CameraPosition.Builder().target((loc)).zoom(16)
				.build();
		// MarkerOptions marker = new MarkerOptions().position(loc);

		HaniMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		HaniMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

		MarkerOptions hani_1 = new MarkerOptions();
		// hani_1.position(new LatLng(Double.parseDouble(latitude),
		// Double.parseDouble(longitude)));
		hani_1.position(new LatLng(37.49927, 127.03748));
		hani_1.title("OO한의원");
		HaniMap.addMarker(hani_1).showInfoWindow();
		
		//서버 연결시 맵 처리 부분
		/*int clinic_location=1;
		ArrayList<KmClinicDetailView> kmClinicDetailView = bridge.getKmClinicDetailViewList(methodUrl, this, clinic_location);
		mapLocation = kmClinicDetailView.get(0).getMapPoint();
		clinicName = kmClinicDetailView.get(0).getName();
		
		//Log.d("mapPoint",mapLocation);
		
		latitude = mapLocation.split(",")[0];
		longitude = mapLocation.split(",")[1];
		//Log.d("mapPoint",latitude);
		//Log.d("mapPoint",longitude);
		
		LatLng loc = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)); 
		CameraPosition cp = new CameraPosition.Builder().target((loc)).zoom(16).build();
		//MarkerOptions marker = new MarkerOptions().position(loc);
		
		HaniMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		HaniMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp)); 
		
		
		MarkerOptions hani_1 = new MarkerOptions();
		hani_1.position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))); 
		hani_1.title(clinicName); 
		HaniMap.addMarker(hani_1).showInfoWindow();*/
		
	}
}
