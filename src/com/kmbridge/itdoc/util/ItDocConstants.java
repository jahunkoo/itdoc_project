package com.kmbridge.itdoc.util;

public class ItDocConstants extends BasicConstants{

	// 서버 method url
	public static final String METHOD_URL_GET_BIG_REGION_LIST = "getBigRegionList";
	public static final String METHOD_URL_GET_MIDDLE_REGION_LIST = "getMiddleRegionList";
	public static final String METHOD_URL_GET_SMALL_REGION_LIST = "getSmallRegionList";
	public static final String METHOD_URL_GET_GRADE_LIST = "getGradeList";
	public static final String METHOD_URL_GET_WEEK_LIST = "getWeekList";
	public static final String METHOD_URL_GET_TIME_LIST = "getTimeList";
	public static final String METHOD_URL_GET_ALLKMCLINIC_LIST = "getAllKmClinic";
	public static final String METHOD_URL_GET_DETAIL_KM_CLINIC = "getDetailKmClinic";
	public static final String METHOD_URL_GET_ALL_KEYWORDS = "getAllKeywords";
	public static final String METHOD_URL_REGISTER = "register";
	public static final String METHOD_URL_LOGIN = "login";
	public static final String METHOD_URL_INSERT_FOLLOW_NUM = "insertKmClinicFollow";
	public static final String METHOD_URL_DELETE_FOLLOW_NUM = "deleteKmClinicFollow";
	public static final String METHOD_URL_GET_USERVIEW_BY_EMAIL = "getUserViewByEmail";
	
	//SharedPreference_key list
	public static final String SHARED_KEY_EMAIL = "user_email";
	public static final String SHARED_KEY_PASSWORD = "user_pwd";
	public static final String SHARED_KEY_NAME = "user_name";
	public static final String SHARED_KEY_FIRST_CHECK = "first";
	
	//fragment tag list
	public static final String TAG_FRAGMENT_JOIN = "joinFragment";
	public static final String TAG_FRAGMENT_LOGIN = "loginFragment";
	
	//UserProfileActivity related 
	public static final String EMAIL = "email";
	public static final String MY_EMAIL = "myEmail";
	public static final String USER_EMAIL = "userEmail";
	public static final String UPDATE_PROFILE = "updateProfile";
	public static final String FOLLOWED = "followed";
	public static final String NOT_FOLLOW = "notFollow";
	public static final int FAVORITE_GOOD = 1;
	public static final int FAVORITE_SOSO = 2;
	public static final int FAVORITE_BAD = 3;
}
