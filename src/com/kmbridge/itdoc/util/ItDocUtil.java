package com.kmbridge.itdoc.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.json.JSONException;
import org.json.JSONObject;

public class ItDocUtil {
	
	private final String REGEX_AT = "@"; 
	private final String REGEX_SHARP = "#";
	private final String REGEX_DOT = ".";
	
	
	
	public List<String> parseStringToList(String data){
		List<String> dataList = new ArrayList<String>();
		String[] dataArr = data.split(",");
		dataList = Arrays.asList(dataArr);
		return dataList;
	}
	
	public JSONObject putResult(JSONObject jsonObj, int resultCode) throws JSONException{
		if(resultCode == ItDocConstants.CODE_ERROR) jsonObj.put(ItDocConstants.RESULT, ItDocConstants.ERROR);
		if(resultCode == ItDocConstants.CODE_EXIST) jsonObj.put(ItDocConstants.RESULT, ItDocConstants.EXIST);
		if(resultCode == ItDocConstants.CODE_NOT_EXIST) jsonObj.put(ItDocConstants.RESULT, ItDocConstants.NOT_EXIST);
		if(resultCode == ItDocConstants.CODE_SUCCESS) jsonObj.put(ItDocConstants.RESULT, ItDocConstants.SUCCESS);
		return jsonObj;
	}
	
	public String createPictureDirPath(int objectTypeCode){
		String dirPath =  ItDocConstants.IMG_PATH_BASIC;
		
		if(objectTypeCode == ItDocConstants.OBJECT_TYPE_USER){
			dirPath += ItDocConstants.IMG_PATH_USER;
		}else if(objectTypeCode == ItDocConstants.OBJECT_TYPE_KM_CLINIC){
			dirPath += ItDocConstants.IMG_PATH_KM_CLINIC;
		}else if(objectTypeCode == ItDocConstants.OBJECT_TYPE_KM_DOCTOR){
			dirPath += ItDocConstants.IMG_PATH_KM_DOCTOR;
		}else if(objectTypeCode == ItDocConstants.OBJECT_TYPE_REVIEW){
			dirPath += ItDocConstants.IMG_PATH_REVIEW;
		}

		return dirPath;
	}
	
	public String createPicturePath(String email, String originalFileName){
		String[] fileNameArr = originalFileName.split("[.]");
		String[] tmpArr = email.split(REGEX_AT);
		String replacedString = null;
		if(tmpArr[0].contains(REGEX_DOT)){
			replacedString = replaceDotToSharp(tmpArr[0]);
		}else {
			replacedString = tmpArr[0];
		}
		String picturePath = replacedString+getTimeStr()+"."+fileNameArr[1];
		
		return picturePath;
	}
	
	private String replaceDotToSharp(String originalString){
		int length = originalString.length();
		int dotPosition = originalString.indexOf(REGEX_DOT);
		System.out.println(dotPosition);
		String front = originalString.substring(0,dotPosition);
		String end = originalString.substring(dotPosition+1,length);
		String replacedString = front+REGEX_SHARP+end;
		return replacedString;
	}
	
	//연+월+날짜+시간(24)+분+초+밀리초+임의의 수(0~100)
	private String getTimeStr(){
		StringBuffer timeStr = new StringBuffer();
		Date currentDate = Calendar.getInstance().getTime();
		long currentTime = currentDate.getTime();
		timeStr.append(currentTime);
		timeStr.append(createRandomNum());
		return  timeStr.toString();
	}
	
	// 0~100 사이의 임의의 수 반환
	private final int MULTIPLY_NUM = 100;
	private String createRandomNum(){
		int randomNum = (int) (Math.random()* MULTIPLY_NUM);
		return String.valueOf(randomNum);
	}
	
	
	
}
