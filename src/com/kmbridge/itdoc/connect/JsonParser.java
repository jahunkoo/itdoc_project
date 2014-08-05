package com.kmbridge.itdoc.connect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

import com.kmbridge.itdoc.dto.BigRegion;
import com.kmbridge.itdoc.dto.Grade;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.dto.MiddleRegion;
import com.kmbridge.itdoc.dto.ReviewKeyword;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.dto.Time;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.dto.Week;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;

public class JsonParser {

	private String methodUrl;

	/**
	 * 요청한 methodUrl에 따라 다르게 리턴되는 데이터를 파싱하기 위해서, 생성자에서 반드시 methodUrl을 넣도록 함
	 * 
	 * @param methodUrl
	 */
	public JsonParser(String methodUrl) {
		this.methodUrl = methodUrl;
	}

	public Object parse(String data) throws JSONException {
		Object obj = null;

		if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_BIG_REGION_LIST)) { // return
																				// 타입
																				// :
																				// HashMap<String,ArrayList>
			obj = parseBigRegionList(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_MIDDLE_REGION_LIST)) {
			obj = parseMiddleRegionList(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_REGISTER)) {
			obj = parserRegister(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_LOGIN)) {
			obj = parserLogin(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_GRADE_LIST)) {
			obj = parseGradeList(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_WEEK_LIST)) {
			obj = parseWeekList(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_TIME_LIST)) {
			obj = parseTimeList(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_ALLKMCLINIC_LIST)) {
			obj = parseKmClinicViewList(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_DETAIL_KM_CLINIC)) {
			obj = parseKmClinicDetailViewList(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_GET_ALL_KEYWORDS)) {
			obj = parseAllKeywords(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_INSERT_FOLLOW_NUM)) {
			obj = parseInsertKmClinicFollow(data);
		} else if (methodUrl.equals(ItDocConstants.METHOD_URL_DELETE_FOLLOW_NUM)) {
			obj = parseDeleteKmClinicFollow(data);
		}else if(methodUrl.equals(ItDocConstants.METHOD_URL_GET_USERVIEW_BY_EMAIL)){
			obj = parseGetUserViewByEmail(data);
		}
		return obj;
	}

	private UserView parseGetUserViewByEmail(String data) throws JSONException {
		UserView userView = new UserView();
		Log.d("koo", data);
		JSONObject jsonObj = new JSONObject(data.trim());
		Log.d("koo", jsonObj.getString(ItDocConstants.RESULT));
		if(isSuccess(jsonObj.getString(ItDocConstants.RESULT))){
			JSONArray jsonArray = jsonObj.getJSONArray("UserView");
			Log.d("koo", "line 80");
			jsonObj = (JSONObject) jsonArray.get(0);
			//ok
			userView.setFollowNum(jsonObj.getInt("followNum"));
			userView.setBirthday(jsonObj.getInt("birthday"));
			userView.setBigRegionCode(jsonObj.getInt("bigRegionCode"));
			userView.setRegisterDate(jsonObj.getString("registerDate"));
			userView.setIntroduce(jsonObj.getString("introduce"));
			userView.setJob(jsonObj.getString("job"));
			userView.setRemainRegion(jsonObj.getString("remainRegion"));
			userView.setFollowingNum(jsonObj.getInt("followingNum"));
			userView.setSchool(jsonObj.getString("school"));
			userView.setPicturePath(jsonObj.getString("picturePath"));
			userView.setEmail(jsonObj.getString("email"));
			userView.setMiddleRegionCode(jsonObj.getInt("middleRegionCode"));
			userView.setName(jsonObj.getString("name"));
			userView.setCertificationCode(jsonObj.getInt("certificationCode"));
			userView.setGender(jsonObj.getInt("gender"));
			userView.setGradeCode(jsonObj.getInt("gradeCode"));
			Log.d("koo", "follow:"+jsonObj.getBoolean("follow"));
			userView.setFollow(jsonObj.getBoolean("follow"));
			jsonArray = jsonObj.getJSONArray("reviewViewList");
			List<ReviewView> reviewViewList = new ArrayList<ReviewView>();
			for(int i=0;i<jsonArray.length();i++){
				
				ReviewView reviewView = new ReviewView();
				jsonObj = jsonArray.getJSONObject(i);
				String picturePath = null; 
				if(jsonObj.has("kmClinicPicturePath"))	picturePath = jsonObj.getString("kmClinicPicturePath");
				else									picturePath = ItDocConstants.IMG_PATH_KM_CLINIC;
				
				Log.d("koo", "picturePath"+picturePath);
				reviewView.setKmClinicPicturePath(jsonObj.getString("kmClinicPicturePath"));
				String reviewTime = jsonObj.getString("reviewTime");
				Log.d("koo", "reviewTime"+reviewTime);
				reviewView.setReviewTime(jsonObj.getString("reviewTime"));
				reviewView.setKmClinicName(jsonObj.getString("kmClinicName"));
				reviewView.setKmClinicId(jsonObj.getInt("kmClinicId"));
				reviewView.setUserEmail(jsonObj.getString("userEmail"));
				reviewView.setKmClinicBigRegionName(jsonObj.getString("kmClinicBigRegionName"));
				reviewView.setReviewId(jsonObj.getInt("reviewId"));
				reviewView.setKmClinicRemainRegion(jsonObj.getString("kmClinicRemainRegion"));
				reviewView.setFavoriteType(jsonObj.getInt("favoriteType"));
				reviewView.setKmClinicBigRegionCode(jsonObj.getInt("kmClinicBigRegionCode"));
				reviewView.setKmClinicMiddleRegionCode(jsonObj.getInt("kmClinicMiddleRegionCode"));
				reviewView.setKmClinicMiddleRegionName(jsonObj.getString("kmClinicMiddleRegionName"));
				reviewView.setUserName(jsonObj.getString("userName"));
				reviewView.setComment(jsonObj.getString("comment"));
				
				List<ReviewKeyword> reviewKeywordList = new ArrayList<ReviewKeyword>();
				JSONArray reviewKeywordJsonArray = jsonObj.getJSONArray("reviewKeywordList");
				for(int j=0;j<reviewKeywordJsonArray.length();j++){
					jsonObj = reviewKeywordJsonArray.getJSONObject(j);
					ReviewKeyword reviewKeyword = new ReviewKeyword();
					reviewKeyword.setId(jsonObj.getInt("id"));
					reviewKeyword.setKeyword(jsonObj.getString("keyword"));
					reviewKeyword.setReviewId(jsonObj.getInt("reviewId"));
					reviewKeyword.setObjectType(jsonObj.getInt("objectType"));
					reviewKeywordList.add(reviewKeyword);
				}
				reviewView.setReviewKeywordList(reviewKeywordList);
				
				reviewViewList.add(reviewView);
			}
			userView.setReviewViewList(reviewViewList);
			
		}else{
			
			
		}
		
		
		return userView;
	}
	
	private boolean isSuccess(String resultValue){
		boolean flag = false;
		if(resultValue.equals(ItDocConstants.SUCCESS)) 	flag = true;
		if(resultValue.equals(ItDocConstants.ERROR))	flag = false;
		Log.d("koo", "isSuccess:"+flag);
		return flag;	
	}
	
	private String parserRegister(String data) throws JSONException {
		JSONObject jsonObj = new JSONObject(data);
		String result = jsonObj.getString("result");
		return result;
	}

	
	private Map parserLogin(String data) throws JSONException {
		JSONObject jsonObj = new JSONObject(data);
		
		Map<String, String> loginMap = new HashMap<String, String>();
		
		String loginResult = jsonObj.getString("success");
		//Log.d("kim2","Parser : "+loginResult);
		
		String loginName  = null;
		if(jsonObj.has("name"))	loginName = jsonObj.getString("name");
		else 	loginName = "";
		
		String loginPicture  = null;
		if(jsonObj.has("picturePath"))	loginPicture = jsonObj.getString("picturePath");
		else 	loginPicture = "";
		
		//Log.d("kim2","Parser : "+loginName);
		loginMap.put(ItDocConstants.PARSE_LOGINRESULT, loginResult);
		loginMap.put(ItDocConstants.PARSE_NAME, loginName);
		loginMap.put(ItDocConstants.PARSE_LOGINPICTURE, loginPicture);
		
		Log.d("kim3",loginPicture);
		
		//map.containsKey(result);
		 return loginMap;
		
		//boolean isLogin = false;
		/*if (result.equals("true")) {

			isLogin = true;
			return isLogin;
		} else {
			isLogin = false;
			return isLogin;
		}*/
	}

	// 메서드 네이밍 : parse+클래스명+자료구조
	private ArrayList<BigRegion> parseBigRegionList(String data) throws JSONException {
		ArrayList<BigRegion> bigRegionList = new ArrayList<BigRegion>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("BigRegion");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject indexObj = jsonArray.getJSONObject(i);
			BigRegion bigRegion = new BigRegion();
			bigRegion.setRegionCode(indexObj.getInt("regionCode"));
			bigRegion.setRegionName(indexObj.getString("regionName"));
			bigRegionList.add(bigRegion);
		}

		return bigRegionList;
	}

	// 메서드 네이밍 : parse+클래스명+자료구조
	private ArrayList<MiddleRegion> parseMiddleRegionList(String data) throws JSONException {
		ArrayList<MiddleRegion> middleRegionList = new ArrayList<MiddleRegion>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("MiddleRegion");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject indexObj = jsonArray.getJSONObject(i);
			MiddleRegion middleRegion = new MiddleRegion();
			middleRegion.setRegionCode(indexObj.getInt("regionCode"));
			middleRegion.setRegionName(indexObj.getString("regionName"));
			middleRegion.setBigRegionCode(indexObj.getInt("bigRegionCode"));
			middleRegionList.add(middleRegion);
		}

		return middleRegionList;
	}

	// 메서드 네이밍 : parse+클래스명+자료구조
	private ArrayList<Grade> parseGradeList(String data) throws JSONException {
		ArrayList<Grade> gradeList = new ArrayList<Grade>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("Grade");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject indexObj = jsonArray.getJSONObject(i);
			Grade grade = new Grade();
			grade.setGradeCode(indexObj.getInt("gradeCode"));
			grade.setGradeName(indexObj.getString("gradeName"));
			gradeList.add(grade);
		}

		return gradeList;
	}

	private ArrayList<Week> parseWeekList(String data) throws JSONException {
		ArrayList<Week> weekList = new ArrayList<Week>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("Week");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject indexobj = jsonArray.getJSONObject(i);
			Week week = new Week();
			week.setWeekCode(indexobj.getInt("weekCode"));
			week.setWeekNameKor(indexobj.getString("weekNameKor"));
			weekList.add(week);
		}
		return weekList;
	}

	private ArrayList<Time> parseTimeList(String data) throws JSONException {
		ArrayList<Time> timeList = new ArrayList<Time>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("Time");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject indexobj = jsonArray.getJSONObject(i);
			Time time = new Time();
			time.setTimeCode(indexobj.getInt("timeCode"));
			time.setTimeHalf(indexobj.getString("timeHalf"));
			timeList.add(time);
		}
		return timeList;
	}

	// 메서드 네이밍 : parse+클래스명+자료구조
	private ArrayList<KmClinicDetailView> parseKmClinicDetailViewList(String data) throws JSONException {
		ArrayList<KmClinicDetailView> KmClinicDetailViewList = new ArrayList<KmClinicDetailView>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("KmClinicDetailView");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject indexobj = jsonArray.getJSONObject(i);
			KmClinicDetailView kmClinicDetailView = new KmClinicDetailView();
			kmClinicDetailView.setId(indexobj.getInt("id"));
			kmClinicDetailView.setName(indexobj.getString("name"));


			// keywordArray 받아와야 함 Array를 받아 와서 다시 string list 형태로 바꿔 주는 형태
			JSONArray JSONindexArray = new JSONArray();

			JSONindexArray = indexobj.getJSONArray("keywordList");

			List<String> keywordList = new ArrayList();

			try {

				for (int idx = 0; idx < JSONindexArray.length(); idx++) {

					keywordList.add(JSONindexArray.getString(idx));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			kmClinicDetailView.setKeywordList(keywordList);
			// 기본적 파싱

			kmClinicDetailView.setDetails(indexobj.getString("details"));
			kmClinicDetailView.setLinePhone(indexobj.getString("linePhone"));
			kmClinicDetailView.setBigRegionCode(indexobj.getString("bigRegionCode"));
			kmClinicDetailView.setBigRegionName(indexobj.getString("bigRegionName"));
			kmClinicDetailView.setMiddleRegionCode(indexobj.getString("middleRegionCode"));
			kmClinicDetailView.setMiddleRegionName(indexobj.getString("middleRegionName"));
			kmClinicDetailView.setRemainRegion(indexobj.getString("remainRegion"));
			kmClinicDetailView.setMapPoint(indexobj.getString("mapPoint"));
			kmClinicDetailView.setHomepage(indexobj.getString("homepage"));
			kmClinicDetailView.setType(indexobj.getInt("type"));
			// kmClinicDetailView.setFollowNum(indexobj.getInt("followNum"));

			// userSimpleInfoArray 받아와야 함 받아와서 다시 내부에서 재 파싱 하는 형태
			JSONindexArray = indexobj.getJSONArray("userSimpleInfoList");
			List<UserSimpleInfo> userSimpleInfoList = new ArrayList();

			try {
				for (int idx = 0; idx < JSONindexArray.length(); idx++) {
					indexobj = JSONindexArray.getJSONObject(idx);

					UserSimpleInfo userSimpleInfo = new UserSimpleInfo();
					userSimpleInfo.setEmail(indexobj.getString("email"));
					userSimpleInfo.setName(indexobj.getString("name"));
					userSimpleInfo.setPicturePath(indexobj.getString("picturePath"));
					userSimpleInfoList.add(userSimpleInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			kmClinicDetailView.setUserSimpleInfoList(userSimpleInfoList);

			// reviewArray 받아 와야 함. 현재 미구현 review 자체가 현재 미구현 상태

			KmClinicDetailViewList.add(kmClinicDetailView);
		}

		return KmClinicDetailViewList;
	}

	private ArrayList<KmClinicView> parseKmClinicViewList(String data) throws JSONException {
		ArrayList<KmClinicView> kmClinicViewList = new ArrayList<KmClinicView>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("KmClinicView");

		for (int i = 0; i < jsonArray.length(); i++) {
			KmClinicView kmClinicView = new KmClinicView();
			JSONObject indexobj = jsonArray.getJSONObject(i);

			kmClinicView.setId(indexobj.getInt("id"));
			kmClinicView.setName(indexobj.getString("name"));
			// 맵포인트는 아직 존재하지 않아서 받아오지 않음
			// kmClinicView.setMapPoint(indexobj.getString("mapPoint"));
			kmClinicView.setBigRegionCode(indexobj.getInt("bigRegionCode"));
			kmClinicView.setBigRegionName(indexobj.getString("bigRegionName"));
			kmClinicView.setMiddleRegionCode(indexobj.getInt("middleRegionCode"));
			kmClinicView.setMiddleRegionName(indexobj.getString("middleRegionName"));
			kmClinicView.setRemainRegion(indexobj.getString("remainRegion"));
			kmClinicView.setFollowNum(indexobj.getInt("followNum"));
			kmClinicView.setRatingNum(indexobj.getInt("ratingNum"));
			kmClinicView.setPicturePath(indexobj.getString("picturePath"));
			kmClinicView.setUserLikeNum(indexobj.getInt("userLikeNum"));
			kmClinicView.setType(indexobj.getInt("type"));


			JSONArray JSONindexArray = new JSONArray();
			JSONindexArray = indexobj.getJSONArray("keywordList");
			List<String> keywordList = new ArrayList<String>();
			try {
				for (int idx = 0; idx < JSONindexArray.length(); idx++) {
					keywordList.add(JSONindexArray.getString(idx));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			kmClinicView.setKeywordList(keywordList);

			JSONindexArray = indexobj.getJSONArray("userSimpleInfoList");
			List<UserSimpleInfo> userSimpleInfoList = new ArrayList();

			try {
				for (int idx = 0; idx < JSONindexArray.length(); idx++) {
					indexobj = JSONindexArray.getJSONObject(i);

					UserSimpleInfo userSimpleInfo = new UserSimpleInfo();

					userSimpleInfo.setEmail(indexobj.getString("email"));
					userSimpleInfo.setName(indexobj.getString("name"));
					userSimpleInfo.setPicturePath(indexobj.getString("picturePath"));

					userSimpleInfoList.add(userSimpleInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			kmClinicView.setUserSimpleInfoList(userSimpleInfoList);

			kmClinicViewList.add(kmClinicView);
		}

		return kmClinicViewList;
	}

	// 메서드 네이밍 : parse+클래스명+자료구조
	private ArrayList<String> parseAllKeywords(String data) throws JSONException {
		ArrayList<String> AllKeywordsList = new ArrayList<String>();

		JSONObject jsonObj = new JSONObject(data);
		JSONArray jsonArray = jsonObj.getJSONArray("keywords");
		for (int i = 0; i < jsonArray.length(); i++) {

			AllKeywordsList.add(jsonArray.getString(i));

		}

		return AllKeywordsList;

	}

	private ArrayList<String> parseInsertKmClinicFollow(String data) throws JSONException {
		ArrayList<String> insertKmClinicFollow = new ArrayList<String>();

		JSONObject jsonObj = new JSONObject(data);

		String result;
		result = jsonObj.getString("result");

		insertKmClinicFollow.add(result);

		return insertKmClinicFollow;
	}

	private ArrayList<String> parseDeleteKmClinicFollow(String data) throws JSONException {
		ArrayList<String> deleteKmClinicFollow = new ArrayList<String>();

		JSONObject jsonObj = new JSONObject(data);

		String result;
		result = jsonObj.getString("result");

		deleteKmClinicFollow.add(result);

		return deleteKmClinicFollow;
	}

	
}
