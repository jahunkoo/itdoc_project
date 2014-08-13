package com.kmbridge.itdoc.hardcoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.kmbridge.itdoc.dto.BigRegion;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.dto.KmDoctor;
import com.kmbridge.itdoc.dto.MiddleRegion;
import com.kmbridge.itdoc.dto.ReviewKeyword;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.dto.Time;
import com.kmbridge.itdoc.dto.TimeTable;
import com.kmbridge.itdoc.dto.UserSimpleFollow;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.dto.Week;

public class LoadData {

	private Context context;
	private Map<Integer, String> keywordMap;

	public LoadData(Context context) {
		this.context = context;

		createKeywordMap();
	}
	
	private void createKeywordMap(){
		keywordMap = new HashMap<Integer, String>();
		keywordMap.put(1, "M자탈모,열성,원형,지루성 탈모");
		keywordMap.put(2, "비만 다이어트,피부,교통사고,전신관절통증,한약,보약,침,물리치료,봉약침");
		// keywordMap.put(2, "비만다이어트피부교통사고전신관절통증한약보약침물리치료봉약침");
		keywordMap.put(3, "여드름,흉터,편평사마귀,모공각화증,탈모치료,피부질환");
		keywordMap.put(4, "경락,한방피부성형,비만,여성,전통기공침,보약클리닉");
		keywordMap.put(5, "체중비만,하체비만,스트레스,직장인 증후군,과민성장증후군");
		keywordMap.put(6, "타하라디톡스,통증,탈모,추나,여성클리닉");
		keywordMap.put(7, "한방다이어트약,비만한약,장쾌해독,변비치료,교통사고,공진단,만성피로");
		keywordMap.put(8, "한방 다이어트,지방분해 식욕억제 다이어트한약,한약재");
		keywordMap.put(9, "면역치료,류마티스관절염,베체트병,산후풍,구내염,강직성척추염,소화기질환");
		keywordMap.put(10, "성기능장애,조루증,발기부전,성불감증,불임");
		keywordMap.put(11, "다이어트,피부질환");
		keywordMap.put(12, "남성,여성,원형,정수리,지루성 탈모치료");
		keywordMap.put(13, "한의원,성조숙증,성장저해질환,소아비만,비염,보약");
		keywordMap.put(14, "안구건조증,안구질환,성형후관리,피부클리닉,비만클리닉,교정클리닉,여성클리닉");
		keywordMap.put(15, "비만,여드름,피부질환,에스테틱");
		keywordMap.put(16, "침톡스,한방성형,다이어트,안면비대칭교정,주름,가슴힙,코성형,붓기관리,안면홍조,통증");
		keywordMap.put(17, "위대장,호흡기,피부 질환,24체질연구소,체칠침");
		keywordMap.put(18, "한방 다이어트,성형,섭식장애 교정,일대일 맞춤 컨설팅,다이어리 관리,탕약");
		keywordMap.put(19, "성조숙증 치료,한방부읜과전문의,초경지연");
		keywordMap.put(20, "양한방 협진,목,허리디스크,신경통증,근골격계 질환");
		keywordMap.put(21, "위염,역류성식도염,신경성소화불량,만성장염");
		keywordMap.put(22, "천식,폐질환,대장염,아토피,피부염");
		keywordMap.put(23, "질염,방광염,질건조증,산후몸조리,한방성형");
		keywordMap.put(24, "한방 각과,침구과,사상체질과");
		keywordMap.put(25, "편두통,신경성 두통,아토피성 피부,체력저하,여드름,비염,스테미너");
		keywordMap.put(26, "비만,성장,탈모,비염,피부");
		keywordMap.put(27, "산후 다이어트,비만,산후보약,근육통");
		keywordMap.put(28, "비만,여드름,생리");
		keywordMap.put(29, "난치성 여드름,화농성,좁쌀 여드름");
		keywordMap.put(30, "비수술 척추,체형,골반교정,턱관절,일자목,산후관리,교통사고후유증,추나요법");
	}

	/*
	 * 병원 아이디랑 키워드랑 묶자
	 */
	public String getJsonFromFile(Context context, String fileName) throws IOException {
		String assetPath = "tables/" + fileName;
		InputStream fin = context.getAssets().open(assetPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));

		StringBuilder buffer = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		fin.close();

		return buffer.toString();
	}

	public ArrayList<ReviewView> getAllReviewView() {
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		ArrayList<ReviewView> reviewList = new ArrayList<ReviewView>();

		try {
			json = getJsonFromFile(context, "review_view.json");
			jsonObj = new JSONObject(json);
			jsonArr = jsonObj.getJSONArray("ReviewView");
			for (int i = 0; i < jsonArr.length(); i++) {
				ReviewView review = new ReviewView();
				jsonObj = jsonArr.getJSONObject(i);
				review.setReviewTime(jsonObj.getString("reviewTime"));
				review.setKmClinicName(jsonObj.getString("kmClinicName"));
				review.setKmClinicId(jsonObj.getInt("kmClinicId"));
				review.setUserEmail(jsonObj.getString("userEmail"));
				review.setKmClinicBigRegionName(jsonObj.getString("kmClinicBigRegionName"));
				review.setReviewId(jsonObj.getInt("reviewId"));
				review.setFavoriteType(jsonObj.getInt("favoriteType"));
				review.setKmClinicRemainRegion(jsonObj.getString("kmClinicRemainRegion"));
				review.setKmClinicBigRegionCode(jsonObj.getInt("kmClinicBigRegionCode"));
				review.setKmClinicMiddleRegionCode(jsonObj.getInt("kmClinicMiddleRegionCode"));
				review.setUserName(jsonObj.getString("userName"));
				review.setKmClinicMiddleRegionName(jsonObj.getString("kmClinicMiddleRegionName"));
				review.setComment(jsonObj.getString("comment"));

				JSONArray innerArr = jsonObj.getJSONArray("reviewKeywordList");
				List<ReviewKeyword> reviewKeywordList = new ArrayList<ReviewKeyword>();
				for (int j = 0; j < innerArr.length(); j++) {
					JSONObject innerObj = innerArr.getJSONObject(j);
					ReviewKeyword rk = new ReviewKeyword();
					rk.setId(innerObj.getInt("id"));
					rk.setKeyword(innerObj.getString("keyword"));
					reviewKeywordList.add(rk);
				}

				reviewList.add(review);

			}

		} catch (Exception e) {

		}

		return reviewList;
	}

	public List<UserSimpleInfo> getAllUserSimpleInfo() {
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		List<UserSimpleInfo> userList = new ArrayList<UserSimpleInfo>();
		try {
			json = getJsonFromFile(context, "user_simple.json");
			jsonObj = new JSONObject(json);
			jsonArr = jsonObj.getJSONArray("UserLikeKmClinic");
			for (int i = 0; i < jsonArr.length(); i++) {
				jsonObj = jsonArr.getJSONObject(i);
				UserSimpleInfo user = new UserSimpleInfo();
				user.setEmail(jsonObj.getString("email"));
				user.setName(jsonObj.getString("name"));
				user.setPicturePath(jsonObj.getString("picturePath"));
				userList.add(user);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	public ArrayList<KmClinicView> searchClinicListByKeyword(String keyword) {

		Map<Integer, KmClinicView> allMap = getKmClinicViewMap();

		ArrayList<KmClinicView> clinicList = new ArrayList<KmClinicView>();
		for (int i = 0; i < keywordMap.size(); i++) {
			Integer integer = new Integer(i + 1);
			String keywords = keywordMap.get(integer);
			Log.d("koo", "========================");
			Log.d("koo", "keywords:" + keywords);
			Log.d("koo", "keyword:" + keyword);
			if (keywords.contains(keyword)) {
				clinicList.add(allMap.get(integer));
				Log.d("koo", String.valueOf(i + 1));
			}
		}
		Log.d("koo", "========================");
		/*
		 * for(KmClinicView tmpView : clinicList){ Log.d("koo",
		 * tmpView.toString()); }
		 */
		return clinicList;
	}

	public Map<Integer, KmClinicView> getKmClinicViewMap() {
		ArrayList<KmClinicView> viewList = getAllKmClinicView();
		Map<Integer, KmClinicView> viewMap = new HashMap<Integer, KmClinicView>();
		for (int i = 0; i < viewList.size(); i++) {
			// Log.d("koo", "test getKmClinicViewMap "+i);
			viewMap.put(i + 1, viewList.get(i));
		}
		return viewMap;
	}

	public ArrayList<KmClinicView> getAllKmClinicView() {
		ArrayList<KmClinicView> clinicList = new ArrayList<KmClinicView>();
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		try {
			json = getJsonFromFile(context, "kmclinic_all_list.json");
			// ok
			jsonObj = new JSONObject(json);
			jsonArr = jsonObj.getJSONArray("KmClinicView");
			for (int i = 0; i < jsonArr.length(); i++) {
				jsonObj = jsonArr.getJSONObject(i);
				// Log.d("koo", "test getAllKmClinicView "+jsonObj.toString());
				KmClinicView view = new KmClinicView();
				view.setId(jsonObj.getInt("id"));
				view.setFollowNum(jsonObj.getInt("followNum"));
				view.setBigRegionCode(jsonObj.getInt("bigRegionCode"));
				view.setBigRegionName(jsonObj.getString("bigRegionName"));
				view.setMiddleRegionCode(jsonObj.getInt("middleRegionCode"));
				view.setMiddleRegionName(jsonObj.getString("middleRegionName"));
				view.setRemainRegion(jsonObj.getString("remainRegion"));
				view.setPicturePath(jsonObj.getString("picturePath"));
				view.setName(jsonObj.getString("name"));

				JSONArray innerArr = new JSONArray();
				List<String> keywordList = new ArrayList<String>();
				innerArr = jsonObj.getJSONArray("keywordList");
				for (int j = 0; j < innerArr.length(); j++) {
					String keyword = (String) innerArr.get(j);
					keywordList.add(keyword);
				}
				view.setKeywordList(keywordList);

				List<UserSimpleInfo> simpleList = new ArrayList<UserSimpleInfo>();
				innerArr = jsonObj.getJSONArray("userSimpleInfoList");
				for (int j = 0; j < innerArr.length(); j++) {
					JSONObject innerObj = innerArr.getJSONObject(j);
					UserSimpleInfo info = new UserSimpleInfo();
					info.setEmail(innerObj.getString("email"));
					Log.d("koo", "||" + innerObj.getString("picturePath"));
					info.setPicturePath(innerObj.getString("picturePath"));
					info.setName(innerObj.getString("name"));
					simpleList.add(info);
				}
				view.setUserSimpleInfoList(simpleList);

				clinicList.add(view);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinicList;
	}

	private int getIdFromUserEmail(String email) {
		int id = 0;
		if (email.equals("test@gmail.com"))
			id = 0;
		else if (email.equals("user1@gmail.com"))
			id = 1;
		else if (email.equals("user2@gmail.com"))
			id = 2;
		else if (email.equals("user3@gmail.com"))
			id = 3;
		else if (email.equals("user4@gmail.com"))
			id = 4;
		else if (email.equals("user5@gmail.com"))
			id = 5;
		else if (email.equals("user6@gmail.com"))
			id = 6;
		else if (email.equals("user7@gmail.com"))
			id = 7;
		else if (email.equals("user8@gmail.com"))
			id = 8;
		else if (email.equals("user9@gmail.com"))
			id = 9;
		else if (email.equals("user10@gmail.com"))
			id = 10;
		else if (email.equals("user11@gmail.com"))
			id = 11;
		else if (email.equals("user12@gmail.com"))
			id = 12;
		else if (email.equals("user13@gmail.com"))
			id = 13;
		else if (email.equals("user14@gmail.com"))
			id = 14;
		else if (email.equals("user15@gmail.com"))
			id = 15;

		return id;
	}

	/**
	 * 
	 * @param userNum
	 * @return
	 */
	public UserView getUserView(String userEmail) {
		UserView userView = new UserView();
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;

		int userNum = getIdFromUserEmail(userEmail);
		try {

			switch (userNum) {
			case 0:
				json = getJsonFromFile(context, "profile_user0.json");
				break;
			case 1:
				json = getJsonFromFile(context, "profile_user1.json");
				break;
			case 2:
				json = getJsonFromFile(context, "profile_user2.json");
				break;
			case 3:
				json = getJsonFromFile(context, "profile_user3.json");
				break;
			case 4:
				json = getJsonFromFile(context, "profile_user4.json");
				break;
			case 5:
				json = getJsonFromFile(context, "profile_user5.json");
				break;
			case 6:
				json = getJsonFromFile(context, "profile_user6.json");
				break;
			case 7:
				json = getJsonFromFile(context, "profile_user7.json");
				break;
			case 8:
				json = getJsonFromFile(context, "profile_user8.json");
				break;
			case 9:
				json = getJsonFromFile(context, "profile_user9.json");
				break;
			case 10:
				json = getJsonFromFile(context, "profile_user10.json");
				break;
			case 11:
				json = getJsonFromFile(context, "profile_user11.json");
				break;
			case 12:
				json = getJsonFromFile(context, "profile_user12.json");
				break;
			case 13:
				json = getJsonFromFile(context, "profile_user13.json");
				break;
			case 14:
				json = getJsonFromFile(context, "profile_user14.json");
				break;
			case 15:
				json = getJsonFromFile(context, "profile_user15.json");
				break;
			}

			jsonObj = new JSONObject(json);
			jsonArr = jsonObj.getJSONArray("UserView");
			jsonObj = jsonArr.getJSONObject(0);

			userView.setFollowNum(jsonObj.getInt("followNum"));
			userView.setBirthday(jsonObj.getInt("birthday"));
			userView.setBigRegionCode(jsonObj.getInt("bigRegionCode"));
			userView.setRegisterDate(jsonObj.getString("registerDate"));
			userView.setCellPhone(jsonObj.getString("cellPhone"));
			userView.setRemainRegion(jsonObj.getString("remainRegion"));
			userView.setFollowingNum(jsonObj.getInt("followingNum"));
			userView.setPicturePath(jsonObj.getString("picturePath"));
			userView.setEmail(jsonObj.getString("email"));
			userView.setMiddleRegionCode(jsonObj.getInt("middleRegionCode"));
			userView.setName(jsonObj.getString("name"));
			userView.setCertificationCode(jsonObj.getInt("certificationCode"));
			userView.setGender(jsonObj.getInt("gender"));
			userView.setFollow(jsonObj.getBoolean("follow"));
			userView.setGradeCode(jsonObj.getInt("gradeCode"));

			jsonArr = jsonObj.getJSONArray("followList");
			List<UserSimpleFollow> usfList = new ArrayList<UserSimpleFollow>();
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject innerObj = jsonArr.getJSONObject(i);
				UserSimpleFollow uf = new UserSimpleFollow();
				uf.setEmail(innerObj.getString("email"));
				uf.setName(innerObj.getString("name"));
				uf.setPicturePath(innerObj.getString("picturePath"));
				uf.setFollowNum(innerObj.getInt("followNum"));
				Log.d("koo", "LoadData 328:" + uf.toString());
				usfList.add(uf);
			}
			userView.setFollowList(usfList);

			jsonArr = jsonObj.getJSONArray("followingList");
			List<UserSimpleFollow> followingList = new ArrayList<UserSimpleFollow>();
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject innerObj = jsonArr.getJSONObject(i);
				UserSimpleFollow uf = new UserSimpleFollow();
				uf.setEmail(innerObj.getString("email"));
				uf.setName(innerObj.getString("name"));
				uf.setPicturePath(innerObj.getString("picturePath"));
				uf.setFollowNum(innerObj.getInt("followNum"));
				Log.d("koo", "LoadData 342:" + uf.toString());
				followingList.add(uf);
			}

			List<ReviewView> reviewList = new ArrayList<ReviewView>();
			jsonArr = jsonObj.getJSONArray("reviewViewList");
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject innerObj = jsonArr.getJSONObject(i);
				ReviewView review = new ReviewView();
				review.setReviewTime(innerObj.getString("reviewTime"));
				review.setKmClinicName(innerObj.getString("kmClinicName"));
				review.setKmClinicId(innerObj.getInt("kmClinicId"));
				review.setUserEmail(innerObj.getString("userEmail"));
				review.setKmClinicBigRegionName(innerObj.getString("kmClinicBigRegionName"));
				review.setReviewId(innerObj.getInt("reviewId"));
				review.setFavoriteType(innerObj.getInt("favoriteType"));
				review.setKmClinicRemainRegion(innerObj.getString("kmClinicRemainRegion"));
				review.setKmClinicBigRegionCode(innerObj.getInt("kmClinicBigRegionCode"));
				review.setKmClinicMiddleRegionCode(innerObj.getInt("kmClinicMiddleRegionCode"));
				review.setUserName(innerObj.getString("userName"));
				review.setKmClinicMiddleRegionName(innerObj.getString("kmClinicMiddleRegionName"));
				review.setComment(innerObj.getString("comment"));

				JSONArray innerArr = innerObj.getJSONArray("reviewKeywordList");
				List<ReviewKeyword> reviewKeywordList = new ArrayList<ReviewKeyword>();
				for (int j = 0; j < innerArr.length(); j++) {
					JSONObject inner2Obj = innerArr.getJSONObject(j);
					ReviewKeyword rk = new ReviewKeyword();
					rk.setId(inner2Obj.getInt("id"));
					rk.setKeyword(inner2Obj.getString("keyword"));
					reviewKeywordList.add(rk);
				}
				reviewList.add(review);
				Log.d("koo", "LoadData 375:" + review.toString());
			}
			userView.setReviewViewList(reviewList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userView;
	}

	/**
	 * 
	 * 한의원 아이디를 넣으면 해당 한의원의 상세정보 객체를 가져옴 보성한의원id :2 고당비한의원id :7 동방한의원id :11
	 * 미아체한의원id :14 청구경희한의원id:30
	 * 
	 * @param kmClinicId
	 * @return
	 */
	public KmClinicDetailView getKmClinicDetailView(int kmClinicId) {
		KmClinicDetailView view = new KmClinicDetailView();
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		try {
			switch (kmClinicId) {
			case 1:
				json = getJsonFromFile(context, "kmclinic_detail_1.json");
				break;
			case 2:
				json = getJsonFromFile(context, "kmclinic_detail_2.json");
				break;
			case 3:
				json = getJsonFromFile(context, "kmclinic_detail_3.json");
				break;
			case 4:
				json = getJsonFromFile(context, "kmclinic_detail_4.json");
				break;
			case 5:
				json = getJsonFromFile(context, "kmclinic_detail_5.json");
				break;
			case 6:
				json = getJsonFromFile(context, "kmclinic_detail_6.json");
				break;
			case 7:
				json = getJsonFromFile(context, "kmclinic_detail_7.json");
				break;
			case 8:
				json = getJsonFromFile(context, "kmclinic_detail_8.json");
				break;
			case 9:
				json = getJsonFromFile(context, "kmclinic_detail_9.json");
				break;
			case 10:
				json = getJsonFromFile(context, "kmclinic_detail_10.json");
				break;
			case 11:
				json = getJsonFromFile(context, "kmclinic_detail_11.json");
				break;
			case 12:
				json = getJsonFromFile(context, "kmclinic_detail_12.json");
				break;
			case 13:
				json = getJsonFromFile(context, "kmclinic_detail_13.json");
				break;
			case 14:
				json = getJsonFromFile(context, "kmclinic_detail_14.json");
				break;
			case 15:
				json = getJsonFromFile(context, "kmclinic_detail_15.json");
				break;
			case 16:
				json = getJsonFromFile(context, "kmclinic_detail_16.json");
				break;
			case 17:
				json = getJsonFromFile(context, "kmclinic_detail_17.json");
				break;
			case 18:
				json = getJsonFromFile(context, "kmclinic_detail_18.json");
				break;
			case 19:
				json = getJsonFromFile(context, "kmclinic_detail_19.json");
				break;
			case 20:
				json = getJsonFromFile(context, "kmclinic_detail_20.json");
				break;
			case 21:
				json = getJsonFromFile(context, "kmclinic_detail_21.json");
				break;
			case 22:
				json = getJsonFromFile(context, "kmclinic_detail_22.json");
				break;
			case 23:
				json = getJsonFromFile(context, "kmclinic_detail_23.json");
				break;
			case 24:
				json = getJsonFromFile(context, "kmclinic_detail_24.json");
				break;
			case 25:
				json = getJsonFromFile(context, "kmclinic_detail_25.json");
				break;
			case 26:
				json = getJsonFromFile(context, "kmclinic_detail_26.json");
				break;
			case 27:
				json = getJsonFromFile(context, "kmclinic_detail_27.json");
				break;
			case 28:
				json = getJsonFromFile(context, "kmclinic_detail_28.json");
				break;
			case 29:
				json = getJsonFromFile(context, "kmclinic_detail_29.json");
				break;
			case 30:
				json = getJsonFromFile(context, "kmclinic_detail_30.json");
				break;
			}

			jsonObj = new JSONObject(json);
			jsonArr = jsonObj.getJSONArray("KmClinicDetailView");
			jsonObj = jsonArr.getJSONObject(0);
			view.setId(jsonObj.getInt("id"));
			view.setName(jsonObj.getString("name"));
			view.setBigRegionName("서울시");
			view.setMiddleRegionName(jsonObj.getString("middleRegionName"));
			view.setRemainRegion(jsonObj.getString("remainRegion"));
			view.setFollowNum(12);
			view.setHomepage(jsonObj.getString("homepage"));
			view.setLinePhone(jsonObj.getString("linePhone"));
			view.setDetails(jsonObj.getString("details"));
			view.setType(jsonObj.getInt("type"));
			view.setPicturePath(jsonObj.getString("picturePath"));
			view.setMapPoint(jsonObj.getString("mapPoint"));
			/*
			switch(kmClinicId){
			case 1: view.setPicturePath("biman_bosung.png");break;
			case 2: view.setPicturePath("biman_bosung.png");break;
			case 7: view.setPicturePath("biman_godangbi.png");break;	//고당비한의원id: 7
			case 11: view.setPicturePath("biman_dongbang.png");break;	//동방한의원id: 11
			case 14: view.setPicturePath("body_miache.png");break;		//미아체한의원 : 14
			case 30: view.setPicturePath("body_kyunghee.png");break;	//청구경희한의원: 30
			}
*/
			jsonArr = jsonObj.getJSONArray("keywordList");
			List<String> keywords = new ArrayList<String>();
			for (int i = 0; i < jsonArr.length(); i++) {
				String keyword = (String) jsonArr.get(i);
				keywords.add(keyword);
			}
			List<UserSimpleInfo> userList = getAllUserSimpleInfo();
			view.setUserSimpleInfoList(userList);


			
			Map<Integer, ArrayList<TimeTable>> timeTableMap = getKmClinicTimeTable();
			ArrayList<TimeTable> timeList = timeTableMap.get(kmClinicId);
			view.setTimeTableList(timeList);
			
			view.setReviewList(getAllReviewView());
			
			List<KmDoctor> doctorList = new ArrayList<KmDoctor>();
			if(jsonObj.has("doctorList")){
				jsonArr = jsonObj.getJSONArray("doctorList");
				for(int j=0;j<jsonArr.length();j++){
					KmDoctor doctor = new KmDoctor();
					JSONObject innerJson = jsonArr.getJSONObject(j);
					if(innerJson.has("id"))				doctor.setId(innerJson.getInt("id"));
					if(innerJson.has("kmClinicId"))		doctor.setKmClinicId(innerJson.getInt("kmClinicId"));
					if(innerJson.has("name"))			doctor.setName(innerJson.getString("name"));
					if(innerJson.has("career"))			doctor.setCareer(innerJson.getString("career"));
					if(innerJson.has("major"))			doctor.setMajor(innerJson.getString("major"));
					if(innerJson.has("academy"))			doctor.setAcademy(innerJson.getString("academy"));
					
					doctorList.add(doctor);
				}
			}
			
			view.setDoctorList(doctorList);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}

	public ArrayList<Time> getTimeList() {
		ArrayList<Time> timeList = new ArrayList<Time>();
		Time time1 = new Time(1, "00:00");
		Time time2 = new Time(2, "00:30");
		Time time3 = new Time(3, "01:00");
		Time time4 = new Time(4, "01:30");
		Time time5 = new Time(5, "02:00");
		Time time6 = new Time(6, "02:30");
		Time time7 = new Time(7, "03:00");
		Time time8 = new Time(8, "03:30");
		Time time9 = new Time(9, "04:00");
		Time time10 = new Time(10, "04:30");

		Time time11 = new Time(11, "05:00");
		Time time12 = new Time(12, "05:30");
		Time time13 = new Time(13, "06:00");
		Time time14 = new Time(14, "06:30");
		Time time15 = new Time(15, "07:00");
		Time time16 = new Time(16, "07:30");
		Time time17 = new Time(17, "08:00");
		Time time18 = new Time(18, "08:30");
		Time time19 = new Time(19, "09:00");
		Time time20 = new Time(20, "09:30");

		Time time21 = new Time(21, "10:00");
		Time time22 = new Time(22, "10:30");
		Time time23 = new Time(23, "11:00");
		Time time24 = new Time(24, "11:30");
		Time time25 = new Time(25, "12:00");
		Time time26 = new Time(26, "12:30");
		Time time27 = new Time(27, "13:00");
		Time time28 = new Time(28, "13:30");
		Time time29 = new Time(29, "14:00");

		Time time30 = new Time(30, "14:30");
		Time time31 = new Time(31, "15:00");
		Time time32 = new Time(32, "15:30");
		Time time33 = new Time(33, "16:00");
		Time time34 = new Time(34, "16:30");
		Time time35 = new Time(35, "17:00");
		Time time36 = new Time(36, "17:30");
		Time time37 = new Time(37, "18:00");
		Time time38 = new Time(38, "18:30");
		Time time39 = new Time(39, "19:00");

		Time time40 = new Time(40, "19:30");
		Time time41 = new Time(41, "20:00");
		Time time42 = new Time(42, "20:30");
		Time time43 = new Time(43, "21:00");
		Time time44 = new Time(44, "21:30");
		Time time45 = new Time(45, "22:00");
		Time time46 = new Time(46, "22:30");
		Time time47 = new Time(47, "23:00");
		Time time48 = new Time(48, "23:30");
		Time time49 = new Time(49, "24:00");

		timeList.add(time1);
		timeList.add(time2);
		timeList.add(time3);
		timeList.add(time4);
		timeList.add(time5);
		timeList.add(time6);
		timeList.add(time7);
		timeList.add(time8);
		timeList.add(time9);
		timeList.add(time10);
		timeList.add(time11);
		timeList.add(time12);
		timeList.add(time13);
		timeList.add(time14);
		timeList.add(time15);
		timeList.add(time16);
		timeList.add(time17);
		timeList.add(time18);
		timeList.add(time19);
		timeList.add(time20);
		timeList.add(time21);
		timeList.add(time22);
		timeList.add(time23);
		timeList.add(time24);
		timeList.add(time25);
		timeList.add(time26);
		timeList.add(time27);
		timeList.add(time28);
		timeList.add(time29);
		timeList.add(time30);
		timeList.add(time31);
		timeList.add(time32);
		timeList.add(time33);
		timeList.add(time34);
		timeList.add(time35);
		timeList.add(time36);
		timeList.add(time37);
		timeList.add(time38);
		timeList.add(time39);
		timeList.add(time40);
		timeList.add(time41);
		timeList.add(time42);
		timeList.add(time43);
		timeList.add(time44);
		timeList.add(time45);
		timeList.add(time46);
		timeList.add(time47);
		timeList.add(time48);
		timeList.add(time49);

		return timeList;
	}

	public ArrayList<Week> getWeekList() {
		Week week1 = new Week(1, "월요일");
		Week week2 = new Week(1, "화요일");
		Week week3 = new Week(1, "수요일");
		Week week4 = new Week(1, "목요일");
		Week week5 = new Week(1, "금요일");
		Week week6 = new Week(1, "토요일");
		Week week7 = new Week(1, "일요일");

		ArrayList<Week> weekList = new ArrayList<Week>();
		weekList.add(week1);
		weekList.add(week2);
		weekList.add(week3);
		weekList.add(week4);
		weekList.add(week5);
		weekList.add(week6);
		weekList.add(week7);

		return weekList;
	}

	public ArrayList<BigRegion> getBigRegionList() {
		BigRegion bigRegion1 = new BigRegion(1, "서울");
		ArrayList<BigRegion> list = new ArrayList<BigRegion>();
		list.add(bigRegion1);

		return list;
	}

	public ArrayList<MiddleRegion> getMiddleRegionList() {
		MiddleRegion middle1 = new MiddleRegion(1, "종로구", 1);
		MiddleRegion middle2 = new MiddleRegion(1, "중구", 1);
		MiddleRegion middle3 = new MiddleRegion(1, "용산구", 1);
		MiddleRegion middle4 = new MiddleRegion(1, "성동구", 1);
		MiddleRegion middle5 = new MiddleRegion(1, "광진구", 1);
		MiddleRegion middle6 = new MiddleRegion(1, "동대문구", 1);
		MiddleRegion middle7 = new MiddleRegion(1, "중랑구", 1);
		MiddleRegion middle8 = new MiddleRegion(1, "성북구", 1);
		MiddleRegion middle9 = new MiddleRegion(1, "강북구", 1);
		MiddleRegion middle10 = new MiddleRegion(1, "도봉구", 1);
		MiddleRegion middle11 = new MiddleRegion(1, "노원구", 1);
		MiddleRegion middle12 = new MiddleRegion(1, "은평구", 1);
		MiddleRegion middle13 = new MiddleRegion(1, "서대문구", 1);
		MiddleRegion middle14 = new MiddleRegion(1, "마포구", 1);
		MiddleRegion middle15 = new MiddleRegion(1, "양천구", 1);
		MiddleRegion middle16 = new MiddleRegion(1, "강서구", 1);
		MiddleRegion middle17 = new MiddleRegion(1, "구로구", 1);
		MiddleRegion middle18 = new MiddleRegion(1, "금천구", 1);
		MiddleRegion middle19 = new MiddleRegion(1, "영등포구", 1);
		MiddleRegion middle20 = new MiddleRegion(1, "동작구", 1);
		MiddleRegion middle21 = new MiddleRegion(1, "관악구", 1);
		MiddleRegion middle22 = new MiddleRegion(1, "서초구", 1);
		MiddleRegion middle23 = new MiddleRegion(1, "강남구", 1);
		MiddleRegion middle24 = new MiddleRegion(1, "송파구", 1);
		MiddleRegion middle25 = new MiddleRegion(1, "강동구", 1);

		ArrayList<MiddleRegion> middleRegion = new ArrayList<MiddleRegion>();
		middleRegion.add(middle1);
		middleRegion.add(middle2);
		middleRegion.add(middle3);
		middleRegion.add(middle4);
		middleRegion.add(middle5);
		middleRegion.add(middle6);
		middleRegion.add(middle7);
		middleRegion.add(middle8);
		middleRegion.add(middle9);
		middleRegion.add(middle10);
		middleRegion.add(middle11);
		middleRegion.add(middle12);
		middleRegion.add(middle13);
		middleRegion.add(middle14);
		middleRegion.add(middle15);
		middleRegion.add(middle16);
		middleRegion.add(middle17);
		middleRegion.add(middle18);
		middleRegion.add(middle19);
		middleRegion.add(middle20);
		middleRegion.add(middle21);
		middleRegion.add(middle22);
		middleRegion.add(middle23);
		middleRegion.add(middle24);
		middleRegion.add(middle25);

		return middleRegion;
	}

	public ArrayList<UserSimpleInfo> getRandomUserSimpleInfoList(int seed) {

		ArrayList<UserSimpleInfo> randomList = new ArrayList<UserSimpleInfo>();

		Random rand = new Random(seed);

		LoadData loadData = new LoadData(context);

		List<UserSimpleInfo> AllUserSimpleInfoList = loadData.getAllUserSimpleInfo();

		int listSize = (int) rand.nextInt(3) % 3 + 3;

		int[] randomNumberList = new int[listSize];

		for (int i = 0; i < randomNumberList.length; i++) {
			int test = 0;
			int randomNumber = (int) rand.nextInt(AllUserSimpleInfoList.size()) % AllUserSimpleInfoList.size();

			for (int j = 0; j < randomNumberList.length; j++) {

				if (randomNumber == randomNumberList[j])
					test++;

			}

			if (test != 0) {
				i--;
				continue;
			}
			randomNumberList[i] = randomNumber;
		}

		for(int i = 0; i<randomNumberList.length; i++) {
			randomList.add(AllUserSimpleInfoList.get(randomNumberList[i]));
		}
		
		return randomList;

	}
	
	public ArrayList<ReviewView> getRandomReviewViewList(int seed) {

		ArrayList<ReviewView> randomList = new ArrayList<ReviewView>();

		Random rand = new Random(seed);

		LoadData loadData = new LoadData(context);

		List<ReviewView> AllReviewViewList = loadData.getAllReviewView();

		int listSize = (int) rand.nextInt(3) % 3 + 3;

		int[] randomNumberList = new int[listSize];

		for (int i = 0; i < randomNumberList.length; i++) {
			int test = 0;
			int randomNumber = (int) rand.nextInt(AllReviewViewList.size()) % AllReviewViewList.size();

			for (int j = 0; j < randomNumberList.length; j++) {

				if (randomNumber == randomNumberList[j])
					test++;

			}

			if (test != 0) {
				i--;
				continue;
			}
			randomNumberList[i] = randomNumber;
		}

		for(int i = 0; i<randomNumberList.length; i++) {
			randomList.add(AllReviewViewList.get(randomNumberList[i]));
		}
		
		return randomList;

	}

	public Map<Integer,ArrayList<TimeTable>> getKmClinicTimeTable(){
		
		Map<Integer,ArrayList<TimeTable>> timeMap = new HashMap<Integer, ArrayList<TimeTable>>();
		//1
		ArrayList<TimeTable> timeList1 = new ArrayList<TimeTable>();
		timeList1.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList1.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(1, timeList1);
		
		//2
		ArrayList<TimeTable> timeList2 = new ArrayList<TimeTable>();
		timeList2.add(new TimeTable("평일", "10:00", "20:00", "13:00", "14:00"));
		timeList2.add(new TimeTable("휴일", "10:00", "17:00", "13:00", "14:00"));
		timeMap.put(2, timeList2);
		
		//3
		ArrayList<TimeTable> timeList3 = new ArrayList<TimeTable>();
		timeList3.add(new TimeTable("평일", "11:00", "21:00", " 14:00", "15:00"));
		timeList3.add(new TimeTable("휴일", "10:00", "17:00", "12:00", "13:00"));
		timeMap.put(3, timeList3);
		
		//4
		ArrayList<TimeTable> timeList4 = new ArrayList<TimeTable>();
		timeList4.add(new TimeTable("평일", "10:00", "19:00", " 14:00", "15:00"));
		timeList4.add(new TimeTable("휴일", "10:00", "15:00", "12:00", "13:00"));
		timeMap.put(4, timeList4);
		
		//5
		ArrayList<TimeTable> timeList5 = new ArrayList<TimeTable>();
		timeList5.add(new TimeTable("평일", "10:00", "18:00", "12:00", "13:00"));
		timeList5.add(new TimeTable("휴일", "10:00", "17:00", "12:00", "13:00"));
		timeMap.put(5, timeList5);
		
		//6
		ArrayList<TimeTable> timeList6 = new ArrayList<TimeTable>();
		timeList6.add(new TimeTable("평일", "09:30", "21:00", "12:00", "13:00"));
		timeList6.add(new TimeTable("휴일", "09:30", "15:00", "12:00", "13:00"));
		timeMap.put(6, timeList6);
		
		//7
		ArrayList<TimeTable> timeList7 = new ArrayList<TimeTable>();
		timeList7.add(new TimeTable("평일", "09:30", "21:00", "12:00", "13:00"));
		timeList7.add(new TimeTable("휴일", "09:30", "15:00", "12:00", "13:00"));
		timeMap.put(7, timeList7);
	
		//8
		ArrayList<TimeTable> timeList8 = new ArrayList<TimeTable>();
		timeList8.add(new TimeTable("평일", "11:00", "20:00", "14:00", "15:00"));
		timeList8.add(new TimeTable("휴일", "11:00", "16:00", "12:00", "13:00"));
		timeMap.put(8, timeList8);
	
		//9
		ArrayList<TimeTable> timeList9 = new ArrayList<TimeTable>();
		timeList9.add(new TimeTable("평일", "09:00", "17:00", "12:00", "13:00"));
		timeList9.add(new TimeTable("휴일", "09:30", "16:00", "12:00", "13:00"));
		timeMap.put(9, timeList9);

		//10
		ArrayList<TimeTable> timeList10 = new ArrayList<TimeTable>();
		timeList10.add(new TimeTable("평일", "10:00", "18:00", "32:00", "14:00"));
		timeList10.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(10, timeList10);

		//11
		ArrayList<TimeTable> timeList11 = new ArrayList<TimeTable>();
		timeList11.add(new TimeTable("평일", "11:00", "20:00", "14:00", "15:00"));
		timeList11.add(new TimeTable("휴일", "11:00", "16:00", "12:00", "13:00"));
		timeMap.put(11, timeList11);

		//12
		ArrayList<TimeTable> timeList12 = new ArrayList<TimeTable>();
		timeList12.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList12.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(12, timeList12);

		//13
		ArrayList<TimeTable> timeList13 = new ArrayList<TimeTable>();
		timeList13.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList13.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(13, timeList13);

		//14
		ArrayList<TimeTable> timeList14 = new ArrayList<TimeTable>();
		timeList14.add(new TimeTable("평일", "10:00", "19:00", " 14:00", "15:00"));
		timeList14.add(new TimeTable("휴일", "10:00", "15:00", "12:00", "13:00"));
		timeMap.put(14, timeList14);

		//15
		ArrayList<TimeTable> timeList15 = new ArrayList<TimeTable>();
		timeList15.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList15.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(15, timeList15);

		//16
		ArrayList<TimeTable> timeList16 = new ArrayList<TimeTable>();
		timeList16.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList16.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(16, timeList16);

		//17
		ArrayList<TimeTable> timeList17 = new ArrayList<TimeTable>();
		timeList17.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList17.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(17, timeList17);

		//18
		ArrayList<TimeTable> timeList18 = new ArrayList<TimeTable>();
		timeList18.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList18.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(18, timeList18);
	
		//19
		ArrayList<TimeTable> timeList19 = new ArrayList<TimeTable>();
		timeList19.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList19.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(19, timeList19);

		//20
		ArrayList<TimeTable> timeList20 = new ArrayList<TimeTable>();
		timeList20.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList20.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(20, timeList20);

		//21
		ArrayList<TimeTable> timeList21 = new ArrayList<TimeTable>();
		timeList21.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList21.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(21, timeList21);
	
		//22
		ArrayList<TimeTable> timeList22 = new ArrayList<TimeTable>();
		timeList22.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList22.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(22, timeList22);

		//23
		ArrayList<TimeTable> timeList23 = new ArrayList<TimeTable>();
		timeList23.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList23.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(23, timeList23);

		//24
		ArrayList<TimeTable> timeList24 = new ArrayList<TimeTable>();
		timeList24.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList24.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(24, timeList24);

		//25
		ArrayList<TimeTable> timeList25 = new ArrayList<TimeTable>();
		timeList25.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList25.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(25, timeList25);

		
		//26
		ArrayList<TimeTable> timeList26 = new ArrayList<TimeTable>();
		timeList26.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList26.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(26, timeList26);

		//27
		ArrayList<TimeTable> timeList27 = new ArrayList<TimeTable>();
		timeList27.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList27.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(27, timeList27);//월 11:00~22:00

		//토 10:00~16:00

		
		
		//28
		ArrayList<TimeTable> timeList28 = new ArrayList<TimeTable>();
		timeList28.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList28.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(28, timeList28);

		//29
		ArrayList<TimeTable> timeList29 = new ArrayList<TimeTable>();
		timeList29.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList29.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(29, timeList29);

		//30
		ArrayList<TimeTable> timeList30 = new ArrayList<TimeTable>();
		timeList30.add(new TimeTable("평일", "09:00", "18:00", "12:00", "13:00"));
		timeList30.add(new TimeTable("휴일", "09:00", "17:00", "12:00", "13:00"));
		timeMap.put(30, timeList30);
		//월 09:00~21:00

		//토 10:00~16:00

		return timeMap;
	}
	
}