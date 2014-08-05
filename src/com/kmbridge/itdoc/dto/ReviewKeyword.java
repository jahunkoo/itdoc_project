package com.kmbridge.itdoc.dto;

public class ReviewKeyword {
	private int id;
	private int reviewId;
	private String keyword;
	private int objectType;
	public ReviewKeyword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewKeyword(int id, int reviewId, String keyword, int objectType) {
		super();
		this.id = id;
		this.reviewId = reviewId;
		this.keyword = keyword;
		this.objectType = objectType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getObjectType() {
		return objectType;
	}
	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}
	@Override
	public String toString() {
		return "ReviewKeyword [id=" + id + ", reviewId=" + reviewId
				+ ", keyword=" + keyword + ", objectType=" + objectType + "]";
	}
	
	
}
