package com.kmbridge.itdoc.activity;

import android.support.v7.app.ActionBar;

public interface CommonMethods {
	/**
	 * 액티비티의 onCreate메서드에서 해당 메서드를 호출하여 액션바 세팅을 하도록 하자. 
	 * @param actionBar
	 */
	public abstract void setActionBar(ActionBar actionBar);
}
