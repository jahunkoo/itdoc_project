package com.kmbridge.itdoc.dto;

public class UserSimpleFollow {

	private String email;
	private String name;
	private String picturePath;
	private int followNum;
	public UserSimpleFollow(String email, String name, String picturePath,
			int followNum) {
		super();
		this.email = email;
		this.name = name;
		this.picturePath = picturePath;
		this.followNum = followNum;
	}
	private int type;
	public UserSimpleFollow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public int getFollowNum() {
		return followNum;
	}
	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "UserSimpleFollow [email=" + email + ", name=" + name
				+ ", picturePath=" + picturePath + ", followNum=" + followNum
				+ ", type=" + type + "]";
	}
	
	
}
