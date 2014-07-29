package com.kmbridge.itdoc.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.kmbridge.itdoc.exception.RecordNotFoundException;


//사용자 정보(이메일, 비밀번호를)를 받는다. SharedPrefenrce에 관련된건 여기서 작업.
public class SharedPreferenceUtil extends ItDocUtil{
	
	//SharedPreference에 사용되는 변수
	private final String PREF_NAME = "com.kmbridge.itdoc.util.pref";
	//public final static String PREF_INTRO_USER_AGREEMENT = "PREF_USER_AGREEMENT";
	//public final static String PREF_MAIN_VALUE = "PREF_MAIN_VALUE";

	/**
	 * 해당 프로세스(어플리케이션)내에 File 형태로 데이터를 저장해준다. (Key, Value)
	 * @param context
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setData(Context context, String key, String value){
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
		return true;
	}
	
	/**
	 * 해당 프로세스(어플리케이션)내에 File에 저장된 값(value)이 있는지를 체크 한다. (Key)
	 * @param context
	 * @param key
	 * @return
	 */
	public boolean isExist(Context context, String key){
		String defalutValue;
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
		defalutValue=pref.getString(key, "");
		if(defalutValue=="")
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 해당 프로세스(어플리케이션)내에 File에 저장된 값 value(String타입)을 가져온다. (Key)
	 * @param context
	 * @param key
	 * @return
	 */
	public String getData(Context context, String key) throws RecordNotFoundException{
		if(!isExist(context, key)) throw new RecordNotFoundException();
		String Value;
		String error="error";
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
		Value=pref.getString(key, "");
		if(Value=="")
		{
			return error;
		}
		else
		{
			return Value;
		}
	}
}
