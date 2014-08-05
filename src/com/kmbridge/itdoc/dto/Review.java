package com.kmbridge.itdoc.dto;

import java.util.List;

public class Review {
	private int id;
	private int favoriteType;		//0:default  1:추천  2:괜찮다  3.비추천
	private String reviewTime;		//리뷰남긴 시점의 시각
	private String comment;			//리뷰 글
	private String picturePath;		//사진
	private String userEmail;		//리뷰 남기는 사용자의 이메일
	private int kmClinicId;			//리뷰 당하는 한의원코드
	private List<ReviewKeyword> reviewKeywordList;	//리뷰 키워드(review_keyword 테이블에 삽입됨) 
													//서버로 보낼때는  키워드 사이에 ,찍어서 String으로 보내자   
	
	public Review() {
		super();
	}

	public Review(int id, int favoriteType, String reviewTime, String comment,
			String picturePath, String userEmail, int kmClinicId,
			List<ReviewKeyword> reviewKeywordList) {
		super();
		this.id = id;
		this.favoriteType = favoriteType;
		this.reviewTime = reviewTime;
		this.comment = comment;
		this.picturePath = picturePath;
		this.userEmail = userEmail;
		this.kmClinicId = kmClinicId;
		this.reviewKeywordList = reviewKeywordList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(int favoriteType) {
		this.favoriteType = favoriteType;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
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

	public List<ReviewKeyword> getReviewKeywordList() {
		return reviewKeywordList;
	}

	public void setReviewKeywordList(List<ReviewKeyword> reviewKeywordList) {
		this.reviewKeywordList = reviewKeywordList;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", favoriteType=" + favoriteType
				+ ", reviewTime=" + reviewTime + ", comment=" + comment
				+ ", picturePath=" + picturePath + ", userEmail=" + userEmail
				+ ", kmClinicId=" + kmClinicId + ", reviewKeywordList="
				+ reviewKeywordList + "]";
	}



}
