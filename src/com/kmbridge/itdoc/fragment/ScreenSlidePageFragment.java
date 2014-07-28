/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kmbridge.itdoc.fragment;

import java.util.Locale;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.ScreenSlideActivity;
import com.kmbridge.itdoc.activity.UserManagerActivity;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * ScreenSlideActivity} samples.</p>
 */
public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener{
	private Button btn_activity_screen_slide_button_fragment_screen_slide_register;
	private Button btn_activity_screen_slide_button_fragment_screen_slide_login;
	
    /**o
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
    	Log.d("koo", "onCreateView start");
    	ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        //mPageNumber는 0부터 시작된다. 
        String imageName = getResources().getStringArray(R.array.screenslide_imagename_array)[mPageNumber];
        String comment = getResources().getStringArray(R.array.screenslide_comment_array)[mPageNumber];
        int imageId = getResources().getIdentifier(imageName.toLowerCase(Locale.getDefault()),
                        "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.imageview_fragment_screen_slide_page)).setImageResource(imageId);
        ((TextView) rootView.findViewById(R.id.textview_slide_screen_comment)).setText(comment);
        
        //회원가입 버튼
        btn_activity_screen_slide_button_fragment_screen_slide_register = (Button) rootView.findViewById(R.id.button_fragment_screen_slide_register);
        btn_activity_screen_slide_button_fragment_screen_slide_register.setOnClickListener(this);
        
        //로그인 버튼
        btn_activity_screen_slide_button_fragment_screen_slide_login = (Button) rootView.findViewById(R.id.button_fragment_screen_slide_login);
        btn_activity_screen_slide_button_fragment_screen_slide_login.setOnClickListener(this);

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
    
    @Override
   	public void onClick(View v) {
   		switch (v.getId()) {
   		// 회원가입
   		case R.id.button_fragment_screen_slide_register:
   			Intent intent = new Intent(getActivity(), UserManagerActivity.class);
   			startActivity(intent);
   			break;
   		
   		case R.id.button_fragment_screen_slide_login:
   			Intent intent2 = new Intent(getActivity(), UserManagerActivity.class);
   			startActivity(intent2);
   			//UserManagerActivity uma = new UserManagerActivity();
   			//uma.loginLayoutElement();
   			break;
   		}
    }
}
