package com.kmbridge.itdoc.hardcoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kmbridge.itdoc.dto.BigRegion;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.dto.MiddleRegion;
import com.kmbridge.itdoc.dto.Time;
import com.kmbridge.itdoc.dto.UserSimpleInfo;
import com.kmbridge.itdoc.dto.Week;

public class LoadData {
	
	/*public static ArrayList<KmClinicView> getKmClinicList(){
		ArrayList<KmClinicView> clinicList = new ArrayList<KmClinicView>();
		
		
		clinicList.add()
		
		
		return 
	}*/
	
	public static ArrayList<Time> getTimeList(){
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


	public static ArrayList<Week> getWeekList(){
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
	
	
	public static ArrayList<BigRegion> getBigRegionList(){
		BigRegion bigRegion1 = new BigRegion(1, "서울");
		ArrayList<BigRegion> list = new ArrayList<BigRegion>();
		list.add(bigRegion1);
		
		return list; 
	}
		
	public static ArrayList<MiddleRegion> getMiddleRegionList(){
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
	
	/*
	public static ArrayList<KmClinicView> getKmClinicViewList(){
		
		
		UserSimpleInfo user1 = new UserSimpleInfo("test01@gmail.com", "구자훈", null);
		UserSimpleInfo user2 = new UserSimpleInfo("test02@gmail.com", "김구라", null);
		UserSimpleInfo user3 = new UserSimpleInfo("test03@gmail.com", "홍세훈", null);
		UserSimpleInfo user4 = new UserSimpleInfo("test04@gmail.com", "김기리", null);
		UserSimpleInfo user5 = new UserSimpleInfo("test05@gmail.com", "나현우", null);
		UserSimpleInfo user6 = new UserSimpleInfo("test06@gmail.com", "유민상", null);
		UserSimpleInfo user7 = new UserSimpleInfo("test07@gmail.com", "탁재훈", null);
		UserSimpleInfo user8 = new UserSimpleInfo("test08@gmail.com", "홍승기", null);
		UserSimpleInfo user9 = new UserSimpleInfo("test09@gmail.com", "맹신지", null);
		UserSimpleInfo user10 = new UserSimpleInfo("test10@gmail.com", "고상호", null);
		UserSimpleInfo user11 = new UserSimpleInfo("test11@gmail.com", "대문호", null);
		UserSimpleInfo user12 = new UserSimpleInfo("test12@gmail.com", "문진환", null);
		UserSimpleInfo user13 = new UserSimpleInfo("test13@gmail.com", "배새진", null);
		UserSimpleInfo user14 = new UserSimpleInfo("test14@gmail.com", "이수진", null);
		UserSimpleInfo user15 = new UserSimpleInfo("test15@gmail.com", "박가연", null);
		
		List<UserSimpleInfo> userList1 = new ArrayList<UserSimpleInfo>();
		userList1.add(user1);
		userList1.add(user2);
		userList1.add(user3);
		
		List<UserSimpleInfo> userList2 = new ArrayList<UserSimpleInfo>();
		userList2.add(user1);
		userList2.add(user3);
		userList2.add(user4);
		userList2.add(user7);
		
		List<UserSimpleInfo> userList3 = new ArrayList<UserSimpleInfo>();
		userList3.add(user4);
		userList3.add(user5);
		userList3.add(user6);
		userList3.add(user7);
		userList3.add(user8);
		
		List<UserSimpleInfo> userList4 = new ArrayList<UserSimpleInfo>();
		userList4.add(user6);
		userList4.add(user7);
		userList4.add(user8);
		userList4.add(user9);
		userList4.add(user10);
		userList4.add(user11);
		userList4.add(user12);
		
		List<UserSimpleInfo> userList5 = new ArrayList<UserSimpleInfo>();
		userList5.add(user9);
		userList5.add(user10);
		userList5.add(user11);
		userList5.add(user12);
		userList5.add(user13);
		userList5.add(user14);
		userList5.add(user15);
		
		List<UserSimpleInfo> userList6 = new ArrayList<UserSimpleInfo>();
		userList6.add(user2);
		userList6.add(user3);
		userList6.add(user4);
		userList6.add(user5);
		userList6.add(user8);
		userList6.add(user10);
		userList6.add(user12);
		userList6.add(user13);
		
		List<UserSimpleInfo> userList7 = new ArrayList<UserSimpleInfo>();
		userList7.add(user3);
		userList7.add(user5);
		userList7.add(user7);
		userList7.add(user9);
		userList7.add(user11);
		
		
		List<String>keyword1 = new ArrayList<String>();
		keyword1.add("M자 탈모");
		keyword1.add("열성");
		keyword1.add("원형");
		keyword1.add("지루성 탈모");
		KmClinicView km1 = 	new KmClinicView(1, "강남발머스한의원", null, 1, "서울특별시" , 23, "강남구", "테헤란로14길 6 602호(역삼동, 남도빌딩)", 14, 5, null, 2, 10, keyword1 , userList1);
		
		List<String>keyword2 = new ArrayList<String>();
		//비만 다이어트, 피부, 교통사고 후유증, 전신관절통증, 한약, 보약, 침, 물리치료, 봉약침
		keyword2.add("비만");
		keyword2.add("다이어트");
		keyword2.add("피부");
		keyword2.add("교통사고 후유증");
		keyword2.add("전신관절통증");
		keyword2.add("한약");
		keyword2.add("보약");
		keyword2.add("침");
		keyword2.add("물리치료");
		keyword2.add("봉약침");
		KmClinicView km2 = new KmClinicView(2,"강남보성한의원 ", null, 1, "서울특별시", 23, "강남구", "선릉로 513 402호(역삼동, APEX타워) ", 6, 4, null, 2, 6, keyword2, userList2);	
		//new KmClinicView(2, "강남보성한의원 ", null, 1, "서울특별시", 23, "강남구", remainRegion, followNum, ratingNum, picturePath, 2, userLikeNum, keywordList, userSimpleInfoList)
		
		String keyword3 = "여드름, 흉터, 편평사마귀, 모공각화증, 탈모치료, 피부질환";
		keyword3 = keyword3.trim();
		String[] keywordArr3 = keyword3.split(",");
		List<String>keywordList3 = Arrays.asList(keywordArr3);
		KmClinicView km3 =  new KmClinicView(3, "강남화접몽한의원 ", null, 1, "서울특별시", 23, "강남구", "강남대로84길 8 9층 (역삼동)", 8, 5, null, 2, 19, keywordList3, userList3);
		
		String keyword4 = "경락, 한방피부성형, 비만, 여성, 전통기공침, 보약클리닉";
		keyword4 = keyword4.trim();
		String[] keywordArr4 = keyword3.split(",");
		List<String>keywordList4 = Arrays.asList(keywordArr3);
		KmClinicView km4 = new KmClinicView(4,"강석일한의원  ",null,1,"서울특별시",23,"강남구","봉은사로 304 2층(역삼동,동광빌딩)", 5, 5, null, 2, 49, keywordList4, userList4);
		
		
	}
	
	
	
	*/
	
	
}