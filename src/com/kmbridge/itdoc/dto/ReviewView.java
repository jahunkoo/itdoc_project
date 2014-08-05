package com.kmbridge.itdoc.dto;

import java.sql.Date;
import java.util.List;

/**
 * 리뷰페이지에 필요한 정보를 담고 있는 클래스 
 * @author Administrator
 *
 */
public class ReviewView {
	
	private int 	reviewId;
	private String 	userEmail;			//리뷰 남기는 사용자의 이메일
	private String	userName;			//사용자의 이름
	private int 	favoriteType;		// 추천,괜찮아요,비추천
	private String 	reviewTime;			//리뷰남긴 시점의 시각
	private String 	comment;			//실제 리뷰 글
	private String	reviewPicturePath;	//사진
	
	private int 	kmClinicId;			//리뷰 당하는 한의원코드
	private String 	kmClinicName;
	private String 	kmClinicPicturePath;
	private int 	kmClinicBigRegionCode;
	private String 	kmClinicBigRegionName;
	private int 	kmClinicMiddleRegionCode;
	private String 	kmClinicMiddleRegionName;
	private String 	kmClinicRemainRegion;
	private List<ReviewKeyword> reviewKeywordList;	//리뷰 키워드(review_keyword 테이블에 삽입됨) 
													//서버로 보낼때는  키워드 사이에 ,찍어서 String으로 보내자   
	//private List<UserSimpleInfo> userSimpleInfoArray;	//추천한 사람들의 간단한 정보들 (이메일, 사진, 이름)
	
	public ReviewView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReviewView(int reviewId, String userEmail, String userName,
			int favoriteType, String reviewTime, String comment,
			String reviewPicturePath, int kmClinicId, String kmClinicName,
			String kmClinicPicturePath, int kmClinicBigRegionCode,
			String kmClinicBigRegionName, int kmClinicMiddleRegionCode,
			String kmClinicMiddleRegionName, String kmClinicRemainRegion,
			List<ReviewKeyword> reviewKeywordList) {
		super();
		this.reviewId = reviewId;
		this.userEmail = userEmail;
		this.userName = userName;
		this.favoriteType = favoriteType;
		this.reviewTime = reviewTime;
		this.comment = comment;
		this.reviewPicturePath = reviewPicturePath;
		this.kmClinicId = kmClinicId;
		this.kmClinicName = kmClinicName;
		this.kmClinicPicturePath = kmClinicPicturePath;
		this.kmClinicBigRegionCode = kmClinicBigRegionCode;
		this.kmClinicBigRegionName = kmClinicBigRegionName;
		this.kmClinicMiddleRegionCode = kmClinicMiddleRegionCode;
		this.kmClinicMiddleRegionName = kmClinicMiddleRegionName;
		this.kmClinicRemainRegion = kmClinicRemainRegion;
		this.reviewKeywordList = reviewKeywordList;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getReviewPicturePath() {
		return reviewPicturePath;
	}
	public void setReviewPicturePath(String reviewPicturePath) {
		this.reviewPicturePath = reviewPicturePath;
	}
	public int getKmClinicId() {
		return kmClinicId;
	}
	public void setKmClinicId(int kmClinicId) {
		this.kmClinicId = kmClinicId;
	}
	public String getKmClinicName() {
		return kmClinicName;
	}
	public void setKmClinicName(String kmClinicName) {
		this.kmClinicName = kmClinicName;
	}
	public String getKmClinicPicturePath() {
		return kmClinicPicturePath;
	}
	public void setKmClinicPicturePath(String kmClinicPicturePath) {
		this.kmClinicPicturePath = kmClinicPicturePath;
	}
	public int getKmClinicBigRegionCode() {
		return kmClinicBigRegionCode;
	}
	public void setKmClinicBigRegionCode(int kmClinicBigRegionCode) {
		this.kmClinicBigRegionCode = kmClinicBigRegionCode;
	}
	public String getKmClinicBigRegionName() {
		return kmClinicBigRegionName;
	}
	public void setKmClinicBigRegionName(String kmClinicBigRegionName) {
		this.kmClinicBigRegionName = kmClinicBigRegionName;
	}
	public int getKmClinicMiddleRegionCode() {
		return kmClinicMiddleRegionCode;
	}
	public void setKmClinicMiddleRegionCode(int kmClinicMiddleRegionCode) {
		this.kmClinicMiddleRegionCode = kmClinicMiddleRegionCode;
	}
	public String getKmClinicMiddleRegionName() {
		return kmClinicMiddleRegionName;
	}
	public void setKmClinicMiddleRegionName(String kmClinicMiddleRegionName) {
		this.kmClinicMiddleRegionName = kmClinicMiddleRegionName;
	}
	public String getKmClinicRemainRegion() {
		return kmClinicRemainRegion;
	}
	public void setKmClinicRemainRegion(String kmClinicRemainRegion) {
		this.kmClinicRemainRegion = kmClinicRemainRegion;
	}
	public List<ReviewKeyword> getReviewKeywordList() {
		return reviewKeywordList;
	}
	public void setReviewKeywordList(List<ReviewKeyword> reviewKeywordList) {
		this.reviewKeywordList = reviewKeywordList;
	}

	@Override
	public String toString() {
		return "ReviewView [reviewId=" + reviewId + ", userEmail=" + userEmail
				+ ", userName=" + userName + ", favoriteType=" + favoriteType
				+ ", reviewTime=" + reviewTime + ", comment=" + comment
				+ ", reviewPicturePath=" + reviewPicturePath + ", kmClinicId="
				+ kmClinicId + ", kmClinicName=" + kmClinicName
				+ ", kmClinicPicturePath=" + kmClinicPicturePath
				+ ", kmClinicBigRegionCode=" + kmClinicBigRegionCode
				+ ", kmClinicBigRegionName=" + kmClinicBigRegionName
				+ ", kmClinicMiddleRegionCode=" + kmClinicMiddleRegionCode
				+ ", kmClinicMiddleRegionName=" + kmClinicMiddleRegionName
				+ ", kmClinicRemainRegion=" + kmClinicRemainRegion
				+ ", reviewKeywordList=" + reviewKeywordList + "]";
	}
	
	
}
