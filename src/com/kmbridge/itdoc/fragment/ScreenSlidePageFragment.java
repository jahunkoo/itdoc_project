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

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.ScreenSlideActivity;
import com.kmbridge.itdoc.image.ImageManager;



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
	
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber=0;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber, Adapter adapter) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        fragment.setAdapter(adapter);
        return fragment;
    }
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }
    
    private void setAdapter(Adapter adapter) {
		// TODO Auto-generated method stub
		
	}

	public ScreenSlidePageFragment() {
    }

	private ImageManager imageManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        imageManager = new ImageManager();
    }
    //private ViewGroup container;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
    	Log.d("koo", "onCreateView start");
    	ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    	
        //mPageNumber는 0부터 시작된다. 
        String imageName = getResources().getStringArray(R.array.screenslide_imagename_array)[mPageNumber];
        String comment = getResources().getStringArray(R.array.screenslide_comment_array)[mPageNumber];
        int imageId = getResources().getIdentifier(imageName.toLowerCase(Locale.getDefault()), "drawable", getActivity().getPackageName());
        
        ImageView slideImage = ((ImageView) rootView.findViewById(R.id.imageview_fragment_screen_slide_page)); 
        Log.d("koo", "size:width"+ImageManager.screenWidth/2+" height:"+ImageManager.screenHeight/2);
        slideImage.setImageBitmap(ImageManager.decodeSampledBitmapFromResource(getResources(), imageId, ImageManager.screenWidth/3, ImageManager.screenHeight/3));
       
        //slideImage.setImageResource(imageId);
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
    
    //// ScreenSlideActivity 로 데이터를 전달할 커스텀 리스너
    private CustomOnClickListener customJoinListener;
    
    // ScreenSlideActivity 로 데이터를 전달할 커스텀 리스너의 인터페이스
    public interface CustomOnClickListener{
        public void onClicked(int id);
    }
    
    //private String TAG_FRAGMENT;
    @Override
   	public void onClick(View v) {
    	// 버튼에 설정한 OnClickListener의 구현, 버튼이 클릭 될 때마다 {ScreenSlidActivity의 커스텀 리스너를 호출함
    	
    	customJoinListener.onClicked(v.getId());
    	Log.d("debug","check1");
   		/*switch (v.getId()) {
   		// 회원가입
   		case R.id.button_fragment_screen_slide_register:
   			Intent intent = new Intent(getActivity(), UserManagerActivity.class);
   			intent.putExtra(ItDocConstants.TAG_FRAGMENT_JOIN, 1);
   			startActivity(intent);
   			joinLayoutElement();
   			FragmentManager fm = getActivity().getSupportFragmentManager();
   	    	Fragment joinFragment = new JoinFragment();
   	    	TAG_FRAGMENT = ItDocConstants.TAG_FRAGMENT_JOIN;
   	    	fm.beginTransaction().replace(container.getId(), joinFragment,TAG_FRAGMENT).commit();
   			break;
   		
   		case R.id.button_fragment_screen_slide_login:
   			Intent intent2 = new Intent(getActivity(), UserManagerActivity.class);
   			intent2.putExtra(ItDocConstants.TAG_FRAGMENT_LOGIN, 2);
   			startActivity(intent2);
   			loginLayoutElement();
   			break;
   		}*/
    }
    
    // ScreenSlideActivity 로 데이터를 전달할 커스텀 리스너를 연결
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        customJoinListener = (CustomOnClickListener)activity;
    }
}
