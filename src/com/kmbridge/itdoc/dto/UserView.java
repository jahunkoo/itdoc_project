package com.kmbridge.itdoc.dto;

import java.util.List;

public class UserView {

	private String email;					//이메일
	private String name;					//이름
	private String cellPhone;				//전화번호 	ex)010-6449-0040
	private int birthday;					//생년월일		ex)19880822
	private int gender;						//성별  		ex)0:기본  1:남자  2:여자
	private String registerDate;			//등록시간 	ex)yyyy-mm-dd hh:mm:ss
	private String school;					//학력
	private String job;						//직업
	private String introduce;				//자기소개
	private int gradeCode;					//회원등급
	private int bigRegionCode;				//전국 구분 	ex)1:서울
	private String bigRegionName;
	private int middleRegionCode;			//군,구 구분 	ex)23:강남구
	private String middleRegionName;		
	private String remainRegion;			//나머지지역
	private String picturePath;				//사진이름
	private int certificationCode;			//인증코드  0:기본 
	private int followNum;
	private int followingNum;
	private boolean isFollow = false;		//
	private List<ReviewView> reviewViewList;
	private List<UserSimpleFollow> followList;
	private List<UserSimpleFollow> followingList;
	public UserView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public UserView(String email, String name, String cellPhone, int birthday,
			int gender, String registerDate, String school, String job,
			String introduce, int gradeCode, int bigRegionCode,
			String bigRegionName, int middleRegionCode,
			String middleRegionName, String remainRegion, String picturePath,
			int certificationCode, int followNum, int followingNum,
			boolean isFollow, List<ReviewView> reviewViewList,
			List<UserSimpleFollow> followList,
			List<UserSimpleFollow> followingList) {
		super();
		this.email = email;
		this.name = name;
		this.cellPhone = cellPhone;
		this.birthday = birthday;
		this.gender = gender;
		this.registerDate = registerDate;
		this.school = school;
		this.job = job;
		this.introduce = introduce;
		this.gradeCode = gradeCode;
		this.bigRegionCode = bigRegionCode;
		this.bigRegionName = bigRegionName;
		this.middleRegionCode = middleRegionCode;
		this.middleRegionName = middleRegionName;
		this.remainRegion = remainRegion;
		this.picturePath = picturePath;
		this.certificationCode = certificationCode;
		this.followNum = followNum;
		this.followingNum = followingNum;
		this.isFollow = isFollow;
		this.reviewViewList = reviewViewList;
		this.followList = followList;
		this.followingList = followingList;
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
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public int getBirthday() {
		return birthday;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(int gradeCode) {
		this.gradeCode = gradeCode;
	}
	public int getBigRegionCode() {
		return bigRegionCode;
	}
	public void setBigRegionCode(int bigRegionCode) {
		this.bigRegionCode = bigRegionCode;
	}
	public String getBigRegionName() {
		return bigRegionName;
	}
	public void setBigRegionName(String bigRegionName) {
		this.bigRegionName = bigRegionName;
	}
	public int getMiddleRegionCode() {
		return middleRegionCode;
	}
	public void setMiddleRegionCode(int middleRegionCode) {
		this.middleRegionCode = middleRegionCode;
	}
	public String getMiddleRegionName() {
		return middleRegionName;
	}
	public void setMiddleRegionName(String middleRegionName) {
		this.middleRegionName = middleRegionName;
	}
	public String getRemainRegion() {
		return remainRegion;
	}
	public void setRemainRegion(String remainRegion) {
		this.remainRegion = remainRegion;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public int getCertificationCode() {
		return certificationCode;
	}
	public void setCertificationCode(int certificationCode) {
		this.certificationCode = certificationCode;
	}
	public int getFollowNum() {
		return followNum;
	}
	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}
	public int getFollowingNum() {
		return followingNum;
	}
	public void setFollowingNum(int followingNum) {
		this.followingNum = followingNum;
	}
	public boolean isFollow() {
		return isFollow;
	}
	public void setFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}
	public List<ReviewView> getReviewViewList() {
		return reviewViewList;
	}
	public void setReviewViewList(List<ReviewView> reviewViewList) {
		this.reviewViewList = reviewViewList;
	}
	
	public List<UserSimpleFollow> getFollowList() {
		return followList;
	}
	public void setFollowList(List<UserSimpleFollow> followList) {
		this.followList = followList;
	}
	
	public List<UserSimpleFollow> getFollowingList() {
		return followingList;
	}

	public void setFollowingList(List<UserSimpleFollow> followingList) {
		this.followingList = followingList;
	}

	@Override
	public String toString() {
		return "UserView [email=" + email + ", name=" + name + ", cellPhone="
				+ cellPhone + ", birthday=" + birthday + ", gender=" + gender
				+ ", registerDate=" + registerDate + ", school=" + school
				+ ", job=" + job + ", introduce=" + introduce + ", gradeCode="
				+ gradeCode + ", bigRegionCode=" + bigRegionCode
				+ ", bigRegionName=" + bigRegionName + ", middleRegionCode="
				+ middleRegionCode + ", middleRegionName=" + middleRegionName
				+ ", remainRegion=" + remainRegion + ", picturePath="
				+ picturePath + ", certificationCode=" + certificationCode
				+ ", followNum=" + followNum + ", followingNum=" + followingNum
				+ ", isFollow=" + isFollow + ", reviewViewList="
				+ reviewViewList + ", followList=" + followList
				+ ", followingList=" + followingList + "]";
	}

	
}
