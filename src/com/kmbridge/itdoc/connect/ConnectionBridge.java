package com.kmbridge.itdoc.connect;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

import com.kmbridge.itdoc.dto.BigRegion;
import com.kmbridge.itdoc.dto.Grade;
import com.kmbridge.itdoc.dto.KmClinicDetailView;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.dto.MiddleRegion;
import com.kmbridge.itdoc.dto.Time;
import com.kmbridge.itdoc.dto.UserView;
import com.kmbridge.itdoc.dto.Week;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.ItDocUtil;

/**
 * 서버로부터 데이터를 내려받을 때,중간 다리 역할을 하는 클래스
 * 
 */
public class ConnectionBridge {

	public static final String MAIN_SERVER_ADDRESS = "http://www.itdoc.co.kr";
	public static final String MAIN_PROJECT_NAME = "ItDocServer";
	public static final String IMG_SERVER_ADDRESS = "http://yss159.cafe24.com:8080";
	public static final String IMG_PROJECT_NAME = "ItDocImgServer";

	/**
	 * naming rule: get+Class명(타입)+어떤자료구조
	 * 
	 * @param methodUrl
	 *            요청하고자 하는 method주소
	 * @param context
	 *            데이터 통신이 이루어지는 동안 로딩화면을 띄우기 위해 필요
	 */
	public ArrayList<BigRegion> getBigRegionList(String methodUrl,
			Context context) {
		ArrayList<BigRegion> bigRegionList = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME, methodUrl);
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.GET);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			bigRegionList = (ArrayList<BigRegion>) new JsonParser(methodUrl)
					.parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		return bigRegionList;
	}

	public ArrayList<MiddleRegion> getMiddleRegionList(String methodUrl,
			Context context) {
		ArrayList<MiddleRegion> middleRegionList = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.GET);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			middleRegionList = (ArrayList<MiddleRegion>) new JsonParser(
					methodUrl).parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		return middleRegionList;
	}

	public ArrayList<Grade> getGradeList(String methodUrl, Context context) {
		ArrayList<Grade> gradeList = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.GET);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			gradeList = (ArrayList<Grade>) new JsonParser(methodUrl)
					.parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		return gradeList;
	}

	public ArrayList<Week> getWeekList(String methodUrl, Context context) {
		ArrayList<Week> weekList = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.GET);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			weekList = (ArrayList<Week>) new JsonParser(methodUrl)
					.parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		return weekList;
	}

	public ArrayList<Time> getTimeList(String methodUrl, Context context) {
		ArrayList<Time> timeList = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.GET);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			timeList = (ArrayList<Time>) new JsonParser(methodUrl)
					.parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		return timeList;
	}

	public ArrayList<KmClinicView> getKmClinicViewList(String methodUrl,
			Context context, String email) {
		ArrayList<KmClinicView> KmClinicView = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.POST);
			Properties prop = new Properties();
			prop.setProperty("email", email);
			connection.setProperties(prop);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();

			KmClinicView = (ArrayList<KmClinicView>) new JsonParser(methodUrl)
					.parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return KmClinicView;
	}

	public ArrayList<KmClinicDetailView> getKmClinicDetailViewList(
			String methodUrl, Context context, int kmClinicId) {
		ArrayList<KmClinicDetailView> KmClinicDetailViewList = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);

		Properties prop = new Properties();
		prop.setProperty("kmClinicId", String.valueOf(kmClinicId));
		try {
			HttpConnectionModule connection = new HttpConnectionModule(context);
			connection.setMethod(HttpConnectionModule.POST);
			connection.setProperties(prop);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			KmClinicDetailViewList = (ArrayList<KmClinicDetailView>) new JsonParser(methodUrl).parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		} catch (UnsupportedEncodingException e) {
		}

		return KmClinicDetailViewList;
	}

	public ArrayList<String> getAllKeywords(String methodUrl, Context context) {
		ArrayList<String> AllKeywordsList = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);

		try {
			HttpConnectionModule connection = new HttpConnectionModule(context);
			connection.setMethod(HttpConnectionModule.GET);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			AllKeywordsList = (ArrayList<String>) new JsonParser(methodUrl)
					.parse(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		return AllKeywordsList;
	}

	public String register(String methodUrl, Properties props, Context context) {
		String result = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,
				methodUrl);
		Log.d("koo", targetUrl);
		// http://localhost:8080/ItDocServer/register?email=asd2323sd@gmail.com&password=asdasd
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.POST);
			connection.setProperties(props);
			Log.d("koo", props.toString());

			connection.downloadTask.execute(targetUrl);
			String data = connection.downloadTask.get();
			result = (String) new JsonParser(methodUrl).parse(data);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public Map<String, String> login(String methodUrl, Properties props, Context context) {
		//String result = null;
		//boolean result=false;
		Map<String, String> loginMap = new HashMap<String, String>();
		
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME,methodUrl);
		Log.d("koo", targetUrl);
		// http://localhost:8080/ItDocServer/register?email=asd2323sd@gmail.com&password=asdasd
		HttpConnectionModule connection = new HttpConnectionModule(context);
		try {
			connection.setMethod(HttpConnectionModule.POST);
			connection.setProperties(props);
			Log.d("koo", props.toString());

			connection.downloadTask.execute(targetUrl);
			String data = connection.downloadTask.get();
			//result =  (Boolean)new JsonParser(methodUrl).parse(data);
			loginMap = (Map)new JsonParser(methodUrl).parse(data);
			//Log.d("LoginIs",""+result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//return result;
		return loginMap;
	}
	
	public ArrayList<String> insertKmClinicFollow(String methodUrl, Context context,  String email,int clinicId) {
		ArrayList<String> insertKmClinicFollow = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME, methodUrl);
		
		try {
			HttpConnectionModule connection = new HttpConnectionModule(context);
			connection.setMethod(HttpConnectionModule.POST);
			
			Properties prop = new Properties();
			
			prop.setProperty("email", email);
			prop.setProperty("kmClinicId", toString().valueOf(clinicId));
			
			connection.setProperties(prop);
			
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			insertKmClinicFollow = (ArrayList<String>) new JsonParser(methodUrl).parse(result);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return insertKmClinicFollow;
	}
	
	public ArrayList<String> deleteKmClinicFollow(String methodUrl, Context context,  String email,int clinicId) {
		ArrayList<String> deleteKmClinicFollow = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME, methodUrl);
		
		try {
			HttpConnectionModule connection = new HttpConnectionModule(context);
			connection.setMethod(HttpConnectionModule.POST);
			
			Properties prop = new Properties();
			
			prop.setProperty("email", email);
			prop.setProperty("kmClinicId", toString().valueOf(clinicId));
			
			connection.setProperties(prop);
			
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			deleteKmClinicFollow = (ArrayList<String>) new JsonParser(methodUrl).parse(result);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return deleteKmClinicFollow;
	}

	
	public ArrayList<KmClinicView> getAllKmClinicListKeyword(String methodUrl, Context context,  String keyword) {
		ArrayList<KmClinicView> allKmClinicListKeyword = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME, methodUrl);
		
		try {
			HttpConnectionModule connection = new HttpConnectionModule(context);
			connection.setMethod(HttpConnectionModule.POST);
			
			Properties prop = new Properties();
			prop.setProperty("keyword", keyword);
			
			connection.setProperties(prop);
			
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			Log.d("kim","ConnectionBridge(430) result is " + result);
			allKmClinicListKeyword = (ArrayList<KmClinicView>) new JsonParser(methodUrl).parse(result);
			
			Log.d("kim","ConnectionBridge (434) keyword clinicList is " + allKmClinicListKeyword.toString());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return allKmClinicListKeyword;
	}
	
	
	// 이미지 업로드
	public String insertImage(String methodUrl, File uploadFile, Context context, String id) {
		String result = null;
		// String email = "koo10682@gmail.com";

		// String email = user_picture.txt_email.getText().toString();
		String targetUrl = getFullUrl(ItDocConstants.ADDRESS_IMG_SERVER_HOST,
				ItDocConstants.ADDRESS_IMG_SERVER_PROJECT, methodUrl);

		String picturePath = new ItDocUtil().createPicturePath(id,
				uploadFile.getName());

		HttpConnectionModule connection = new HttpConnectionModule(context);
		connection.setMethod(HttpConnectionModule.MULTIPART_POST);
		connection.setProfileImgFile(uploadFile, id, picturePath,
				ItDocConstants.OBJECT_TYPE_USER);

		connection.downloadTask.execute(targetUrl);

		try {
			String data = connection.downloadTask.get();
			// {"result","success"}
			// result = (String) new JsonParser(methodUrl).parse(data);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = ItDocConstants.SUCCESS;
		return result;
	}
	
	public UserView getUserViewByEmail(Context context,String methodUrl ,String myEmail ,String userEmail){
		UserView userView = null;
		String targetUrl = getFullUrl(MAIN_SERVER_ADDRESS, MAIN_PROJECT_NAME, methodUrl);
		
		HttpConnectionModule connection = new HttpConnectionModule(context);
		connection.setMethod(HttpConnectionModule.POST);
		Properties prop = new Properties();
		prop.setProperty("myEmail", myEmail);
		prop.setProperty("userEmail", userEmail);
		try {
			connection.setProperties(prop);
			connection.downloadTask.execute(targetUrl);
			String result = connection.downloadTask.get();
			userView = (UserView) new JsonParser(methodUrl).parse(result);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userView;
	}
	
	
	private String getFullUrl(String serverUrl, String projectUrl,
			String methodUrl) {
		return serverUrl + "/" + projectUrl + "/" + methodUrl;
	}
}
