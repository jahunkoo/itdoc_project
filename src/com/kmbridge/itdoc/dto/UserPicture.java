package com.kmbridge.itdoc.dto;

public class UserPicture {
	private int id;
	private String userEmail;
	private String picturePath;
	
	public UserPicture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPicture(int id, String userEmail, String picturePath) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.picturePath = picturePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	@Override
	public String toString() {
		return "UserPicture [id=" + id + ", userEmail=" + userEmail
				+ ", picturePath=" + picturePath + "]";
	}

	
	
}
