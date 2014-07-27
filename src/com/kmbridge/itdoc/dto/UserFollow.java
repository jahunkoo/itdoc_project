package com.kmbridge.itdoc.dto;

public class UserFollow {
	private String userEmail;
	private String followEmail;
	private int type;
	public UserFollow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserFollow(String userEmail, String followEmail, int type) {
		super();
		this.userEmail = userEmail;
		this.followEmail = followEmail;
		this.type = type;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFollowEmail() {
		return followEmail;
	}
	public void setFollowEmail(String followEmail) {
		this.followEmail = followEmail;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "UserFollow [userEmail=" + userEmail + ", followEmail="
				+ followEmail + ", type=" + type + "]";
	}
	
	
}
