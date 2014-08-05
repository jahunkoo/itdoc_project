package com.kmbridge.itdoc.dto;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kmbridge.itdoc.exception.UserException;

public class User {
	
	private final int DEFAULT_NUM = 0;
	private final int MIN_PASSWORD_NUM = 6;
	private final int BIRTH_YEAR_MIN = 1900;
	private int currentYear;
	private final String EMAIL_PATTERN =
	            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String INPUT_EMPTY = "정보를 입력하세요";
	public static final String NOT_EMAIL_TYPE = "이메일 형식이 아닙니다.";
	public static final String PASSWORD_LENGTH_NEED_UP_TO_SIX = "비밀번호는 6자리 이상이어야 합니다.";
	public static final String NOT_CORRECT_BIRTH_YEAR = "올바른 연도를 입력하세요";
	
	private String email;
	private String password;
	private String name;
	private String cellPhone;
	private int birthday;
	private int gender 	= DEFAULT_NUM;
	private String registerDate;
	private String school;
	private String job;
	private String introduce;
	private int gradeCode;
	private int bigRegionCode;
	private int middleRegionCode;
	private String remainRegion;
	private int flag	= DEFAULT_NUM;
	private int certificationCode;
	
	//생성자 이전에 호출되는 부분
	{
		Calendar cal = Calendar.getInstance();
		currentYear = Calendar.getInstance().get ( cal.YEAR );
	}
	
	
	public User() {
		super();
	}

	public User(int currentYear, String email, String password, String name,
			String cellPhone, int birthday, int gender, String registerDate,
			String school, String job, String introduce, int gradeCode,
			int bigRegionCode, int middleRegionCode, String remainRegion,
			int flag, int certificationCode) {
		super();
		this.currentYear = currentYear;
		this.email = email;
		this.password = password;
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
		this.middleRegionCode = middleRegionCode;
		this.remainRegion = remainRegion;
		this.flag = flag;
		this.certificationCode = certificationCode;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UserException {
		if(email == null || email.isEmpty()){
			throw new UserException(INPUT_EMPTY);
		}else if(!isEmailAddress(email)){
			throw new UserException(NOT_EMAIL_TYPE);
		}
		
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws UserException {
		if(password == null || password.isEmpty()){
			throw new UserException(INPUT_EMPTY);
		}else if(password.length()<MIN_PASSWORD_NUM){
			throw new UserException(PASSWORD_LENGTH_NEED_UP_TO_SIX);
		}
		this.password = password;
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

	public int getMiddleRegionCode() {
		return middleRegionCode;
	}

	public void setMiddleRegionCode(int middleRegionCode) {
		this.middleRegionCode = middleRegionCode;
	}

	public String getRemainRegion() {
		return remainRegion;
	}

	public void setRemainRegion(String remainRegion) {
		this.remainRegion = remainRegion;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getCertificationCode() {
		return certificationCode;
	}

	public void setCertificationCode(int certificationCode) {
		this.certificationCode = certificationCode;
	}

	public boolean isEmailAddress(String email) {
	        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
	        Matcher emailMatcher = emailPattern.matcher(email);

	        return emailMatcher.matches();
	    }

	@Override
	public String toString() {
		return "User [DEFAULT_NUM=" + DEFAULT_NUM + ", MIN_PASSWORD_NUM="
				+ MIN_PASSWORD_NUM + ", BIRTH_YEAR_MIN=" + BIRTH_YEAR_MIN
				+ ", currentYear=" + currentYear + ", EMAIL_PATTERN="
				+ EMAIL_PATTERN + ", email=" + email + ", password=" + password
				+ ", name=" + name + ", cellPhone=" + cellPhone + ", birthday="
				+ birthday + ", gender=" + gender + ", registerDate="
				+ registerDate + ", school=" + school + ", job=" + job
				+ ", introduce=" + introduce + ", gradeCode=" + gradeCode
				+ ", bigRegionCode=" + bigRegionCode + ", middleRegionCode="
				+ middleRegionCode + ", remainRegion=" + remainRegion
				+ ", flag=" + flag + ", certificationCode=" + certificationCode
				+ "]";
	}
	   
	
}
