package com.kmbridge.itdoc.util;

import android.content.Context;
import android.content.SharedPreferences;


//사용자 정보(이메일, 비밀번호를)를 받는다. SharedPrefenrce에 관련된건 여기서 작업.
public class SharedPreferenceUtil extends ItDocUtil{

	/**
	 * 해당 프로세스(어플리케이션)내에 File 형태로 데이터를 저장해준다. (파일이름, Key, Value)
	 * @param context
	 * @param name
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setData(Context context, String name, String key, String value){
		SharedPreferences pref = context.getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
		
		return true;
	}
	
	/**
	 * 해당 프로세스(어플리케이션)내에 File에 저장된 값(value)을 가져온다. (파일이름, Key)
	 * @param context
	 * @param name
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean getData(Context context, String name, String key){
		SharedPreferences pref = context.getSharedPreferences(name, 0);
		pref.getString(key, "");
		
		return true;
	}
}
