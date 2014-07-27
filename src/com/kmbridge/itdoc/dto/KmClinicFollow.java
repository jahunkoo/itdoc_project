package com.kmbridge.itdoc.dto;

public class KmClinicFollow {
	private String userEmail;
	private int kmClinicId;
	private int type;
	public KmClinicFollow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KmClinicFollow(String userEmail, int kmClinicId, int type) {
		super();
		this.userEmail = userEmail;
		this.kmClinicId = kmClinicId;
		this.type = type;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getKmClinicId() {
		return kmClinicId;
	}
	public void setKmClinicId(int kmClinicId) {
		this.kmClinicId = kmClinicId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
