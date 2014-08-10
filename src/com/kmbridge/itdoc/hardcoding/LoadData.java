package com.kmbridge.itdoc.hardcoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.kmbridge.itdoc.dto.BigRegion;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.MiddleRegion;
import com.kmbridge.itdoc.dto.ReviewKeyword;
import com.kmbridge.itdoc.dto.ReviewView;
import com.kmbridge.itdoc.dto.Time;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.dto.Week;

public class LoadData {
	
	private Context context;
	
	public LoadData(Context context) {
		this.context= context;
	}


	
	public String getJsonFromFile(Context context,String fileName) throws IOException{
		String assetPath = "tables/"+fileName;
		InputStream fin = context.getAssets().open(assetPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));

		StringBuilder buffer = new StringBuilder();
		String line;
		while ((line=br.readLine())!=null) {
			buffer.append(line);
		}
		fin.close();
		
		
		return buffer.toString();
	}
	public ArrayList<ReviewView> getAllReviewView(){
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		ArrayList<ReviewView> reviewList = new ArrayList<ReviewView>();
		
		try{
			json = getJsonFromFile(context,"review_view.json");
			jsonObj = new JSONObject(json);
			jsonArr = jsonObj.getJSONArray("ReviewView");
			for(int i=0;i<jsonArr.length();i++){
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
				for(int j=0;j<innerArr.length();j++){
					JSONObject innerObj = innerArr.getJSONObject(j);
					ReviewKeyword rk = new ReviewKeyword();
					rk.setId(innerObj.getInt("id"));
					rk.setKeyword(innerObj.getString("keyword"));
					reviewKeywordList.add(rk);
				}
				
				reviewList.add(review);
				
			}
			
		}catch(Exception e){
			
		}
		
		
		return reviewList;
	}
	
	public List<UserSimpleInfo> getAllUserSimpleInfo(){
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		List<UserSimpleInfo> userList = new ArrayList<UserSimpleInfo>();
		try {
			json = getJsonFromFile(context,"user_simple.json");
			jsonObj = new JSONObject(json);
			jsonArr = jsonObj.getJSONArray("UserLikeKmClinic");
			for(int i=0;i<jsonArr.length();i++){
				jsonObj = jsonArr.getJSONObject(i);
				UserSimpleInfo user = new UserSimpleInfo();
				user.setEmail(jsonObj.getString("email"));
				user.setName(jsonObj.getString("name"));
				user.setPicturePath("picturePath");
				userList.add(user);
			}
			
		}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		return userList;
	}
	
	
	/**
	 * 
	 * 한의원 아이디를 넣으면 해당 한의원의 상세정보 객체를 가져옴
	 * 보성한의원id 	:2
	 * 고당비한의원id	:7 
	 * 동방한의원id	:11 
	 * 미아체한의원id :14 
	 * 청구경희한의원id:30 
	 * @param kmClinicId
	 * @return
	 */
	public KmClinicDetailView getKmClinicDetailView(int kmClinicId){
		KmClinicDetailView view = new KmClinicDetailView();
		String json = null;
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		try {
			switch(kmClinicId){
			case 1: json = getJsonFromFile(context,"kmclinic_detail_1.json");break;
			case 2: json = getJsonFromFile(context,"kmclinic_detail_2.json");break;
			case 3: json = getJsonFromFile(context,"kmclinic_detail_3.json");break;
			case 4: json = getJsonFromFile(context,"kmclinic_detail_4.json");break;
			case 5: json = getJsonFromFile(context,"kmclinic_detail_5.json");break;
			case 6: json = getJsonFromFile(context,"kmclinic_detail_6.json");break;
			case 7: json = getJsonFromFile(context,"kmclinic_detail_7.json");break;
			case 8: json = getJsonFromFile(context,"kmclinic_detail_8.json");break;
			case 9: json = getJsonFromFile(context,"kmclinic_detail_9.json");break;
			case 10: json = getJsonFromFile(context,"kmclinic_detail_10.json");break;
			case 11: json = getJsonFromFile(context,"kmclinic_detail_11.json");break;
			case 12: json = getJsonFromFile(context,"kmclinic_detail_12.json");break;
			case 13: json = getJsonFromFile(context,"kmclinic_detail_13.json");break;
			case 14: json = getJsonFromFile(context,"kmclinic_detail_14.json");break;
			case 15: json = getJsonFromFile(context,"kmclinic_detail_15.json");break;
			case 16: json = getJsonFromFile(context,"kmclinic_detail_16.json");break;
			case 17: json = getJsonFromFile(context,"kmclinic_detail_17.json");break;
			case 18: json = getJsonFromFile(context,"kmclinic_detail_18.json");break;
			case 19: json = getJsonFromFile(context,"kmclinic_detail_19.json");break;
			case 20: json = getJsonFromFile(context,"kmclinic_detail_20.json");break;
			case 21: json = getJsonFromFile(context,"kmclinic_detail_21.json");break;
			case 22: json = getJsonFromFile(context,"kmclinic_detail_22.json");break;
			case 23: json = getJsonFromFile(context,"kmclinic_detail_23.json");break;
			case 24: json = getJsonFromFile(context,"kmclinic_detail_24.json");break;
			case 25: json = getJsonFromFile(context,"kmclinic_detail_25.json");break;
			case 26: json = getJsonFromFile(context,"kmclinic_detail_26.json");break;
			case 27: json = getJsonFromFile(context,"kmclinic_detail_27.json");break;
			case 28: json = getJsonFromFile(context,"kmclinic_detail_28.json");break;
			case 29: json = getJsonFromFile(context,"kmclinic_detail_29.json");break;
			case 30: json = getJsonFromFile(context,"kmclinic_detail_30.json");break;
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
			
			switch(kmClinicId){
			case 1: view.setPicturePath("biman_bosung");break;
			case 2: view.setPicturePath("biman_bosung");break;
			case 7: view.setPicturePath("biman_godangbi");break;	//고당비한의원id: 7
			case 11: view.setPicturePath("biman_dongbang");break;	//동방한의원id: 11
			case 14: view.setPicturePath("body_miache");break;		//미아체한의원 : 14
			case 30: view.setPicturePath("body_kyunghee");break;	//청구경희한의원: 30
			}
			
			jsonArr = jsonObj.getJSONArray("keywordList");
			List<String> keywords = new ArrayList<String>();
			for(int i=0;i<jsonArr.length();i++){
				String keyword = (String) jsonArr.get(i);
				keywords.add(keyword);
			}
			List<UserSimpleInfo> userList = getAllUserSimpleInfo();
			view.setUserSimpleInfoList(userList);
			
			view.setReviewList(getAllReviewView());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("koo", "start||"+view.toString());
		
		return view;
	}
	
	
	public ArrayList<Time> getTimeList(){
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

	public ArrayList<Week> getWeekList(){
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
	
	public ArrayList<BigRegion> getBigRegionList(){
		BigRegion bigRegion1 = new BigRegion(1, "서울");
		ArrayList<BigRegion> list = new ArrayList<BigRegion>();
		list.add(bigRegion1);
		
		return list; 
	}
		
	public ArrayList<MiddleRegion> getMiddleRegionList(){
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
	

	
}