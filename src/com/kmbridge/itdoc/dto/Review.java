package com.kmbridge.itdoc.dto;

public class Review {
	private int id;
	private int userWishType;	//0:default  1:가고 싶어요  2:가봤어요
	private int favoriteType;	//0:default  1:추천  2:괜찮다  3.비추천
	private String choiceTime;
	private String comment;
	private String picturePath;
	private String userEmail;
	private int kmClinicId;
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int id, int userWishType, int favoriteType,
			String choiceTime, String comment, String picturePath,
			String userEmail, int kmClinicId) {
		super();
		this.id = id;
		this.userWishType = userWishType;
		this.favoriteType = favoriteType;
		this.choiceTime = choiceTime;
		this.comment = comment;
		this.picturePath = picturePath;
		this.userEmail = userEmail;
		this.kmClinicId = kmClinicId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserWishType() {
		return userWishType;
	}
	public void setUserWishType(int userWishType) {
		this.userWishType = userWishType;
	}
	public int getFavoriteType() {
		return favoriteType;
	}
	public void setFavoriteType(int favoriteType) {
		this.favoriteType = favoriteType;
	}
	public String getChoiceTime() {
		return choiceTime;
	}
	public void setChoiceTime(String choiceTime) {
		this.choiceTime = choiceTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
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
	@Override
	public String toString() {
		return "Review [id=" + id + ", userWishType=" + userWishType
				+ ", favoriteType=" + favoriteType + ", choiceTime="
				+ choiceTime + ", comment=" + comment + ", picturePath="
				+ picturePath + ", userEmail=" + userEmail + ", kmClinicId="
				+ kmClinicId + "]";
	}

	
	
}
