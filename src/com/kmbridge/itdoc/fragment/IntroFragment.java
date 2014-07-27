package com.kmbridge.itdoc.fragment;

import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class IntroFragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";

	/**
	 * fragment가 activity에 붙을 때 수행되는 메서드 순서 onAttach() : Fragment가 Activity에 붙을때
	 * 호출 된다. onCreate() : Activity에서의 onCreate()와 비슷하나, ui관련 작업은 할 수 없다.
	 * onCreateView(): Layout을 inflater을하여 View작업을 하는곳이다. onActivityCreated() :
	 * Activity에서 Fragment를 모두 생성하고 난다음 호출 된다. Activity의 onCreate()에서
	 * setContentView()한 다음이라고 생각 하면 쉽게 이해 될것 같다. 여기서 부터는 ui변경작업이 가능하다.
	 * 
	 * 출처 :
	 * http://www.kmshack.kr/fragment-%ED%8C%8C%ED%97%A4%EC%B9%98%EA%B8%B0-2
	 * -fragment-lifecycle%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// view가져오고
		View introView = inflater.inflate(R.layout.fragment_intro, container,
				false);
		int imageId = getResources().getIdentifier("intro", "drawable",
				getActivity().getPackageName());
		((ImageView) introView.findViewById(R.id.image_intro))
				.setImageResource(imageId);
		// getActivity().setTitle(planet);
		return introView;
	}

}
