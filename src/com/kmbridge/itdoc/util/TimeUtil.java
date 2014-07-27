package com.kmbridge.itdoc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil extends ItDocUtil{
	
	public String getCurrentDate(){
		 SimpleDateFormat simpleDateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String strCurrentDate = simpleDateFmt.format(new Date());
		 return strCurrentDate;
	}
	
	public int getdiffTimes(String certainDateStr) throws ParseException {
		Date currentDate = Calendar.getInstance().getTime();
		Date certainDate = new Date();
		SimpleDateFormat DateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		certainDate = DateFormat.parse(certainDateStr);
		
		// 시간 계산
		long diff = currentDate.getTime() - certainDate.getTime();
		long diffTimes = diff / (24 * 60 * 60 * 1000);
						    
		return (int) diffTimes;
	}
	
}
