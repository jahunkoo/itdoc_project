package com.kmbridge.itdoc.activity;

import java.util.Properties;

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
	private KmClinicDetailView mapInfo = new KmClinicDetailView();
	private String mapPointString;
	private Properties prop;
	private String methodUrl = "getDetailKmClinic";
	
	GoogleMap HaniMap;
	LatLng loc = new LatLng(37.49927, 127.03748); 
	CameraPosition cp = new CameraPosition.Builder().target((loc)).zoom(16).build();
	MarkerOptions marker = new MarkerOptions().position(loc); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		/*mapPointString = bridge.getKmClinicDetailViewList(methodUrl, this,  );
		
		HaniMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		HaniMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp)); 
		
		MarkerOptions hani_1 = new MarkerOptions();
		hani_1.position(new LatLng(37.49927, 127.03748)); 
		hani_1.title("윤성수 한의원"); 
		HaniMap.addMarker(hani_1).showInfoWindow();*/
		
	}
}
