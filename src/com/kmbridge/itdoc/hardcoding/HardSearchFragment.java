package com.kmbridge.itdoc.hardcoding;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.adapter.SearchAdapter;
import com.kmbridge.itdoc.connect.ConnectionBridge;
import com.kmbridge.itdoc.dto.KmClinicView;
import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.fragment.SearchResultClinicListFragment;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

public class HardSearchFragment extends Fragment implements OnClickListener, OnItemClickListener {

	ArrayList<String> allKeywordsList = new ArrayList<String>();
	private Context context;

	AutoCompleteTextView search;
	ImageButton searchButton;

	ListView recentListView;

	String[] keywords1 = null;
	SearchAdapter searchAdapter;

	SharedPreferenceUtil share = new SharedPreferenceUtil();

	public static final String RECENT_KEYWORD = "recentKeyword";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_search, container, false);
		
		share.setData(context, RECENT_KEYWORD, "한의콕" + ",");
		
		ConnectionBridge keywordConnection = new ConnectionBridge();

		search = (AutoCompleteTextView) rootView.findViewById(R.id.autocomplete_textview_fragment_search);
		searchButton = (ImageButton) rootView.findViewById(R.id.button_fragment_search);
		recentListView = (ListView) rootView.findViewById(R.id.listview_fragment_search_recent_search);

		searchButton.setOnClickListener(this);
		StringBuffer keywordBuffer = new StringBuffer();
		keywordBuffer.append("M자 탈모, 열성, 원형, 지루성 탈모,비만 다이어트, 피부, 교통사고 후유증, 전신관절통증, 한약, 보약,")
					.append("M자 탈모, 열성, 원형, 지루성 탈모,비만 다이어트, 피부, 교통사고 후유증, 전신관절통증, 한약, 보약,")
					.append("침, 물리치료, 봉약침, 여드름, 흉터, 편평사마귀, 모공각화증, 탈모치료, 피부질환,경락, 한방피부성형, 비만, 여성, 전통기공침, 보약클리닉,")
					.append("체중비만, 하체비만, 스트레스로 인한 제 질환, 직장인 증후군, 과민성장증후군 상담 치료,")
					.append("타하라디톡스, 통증, 탈모, 추나, 여성클리닉,")
					.append("한방다이어트약, 비만한약, 장쾌해독, 변비치료, 교통사고, 공진단, 만성피로체질개선,")
					.append("한방 다이어트전문, 지방분해 및 식욕억제 다이어트 한약, 한약재,")
					.append("면역치료, 류마티스관절염, 베체트병, 산후풍, 구내염, 강직성척추염, 소화기질환 진료 안내,")
					.append("성기능장애, 조루증, 발기부전, 성불감증, 불임,다이어트, 피부질환,남성, 여성, 원형, 정수리, 지루성 탈모치료,")
					.append("한의원, 성조숙증, 성장저해질환, 소아비만, 비염, 보약,")
					.append("안구건조증, 안구질환 치료, 성형후관리, 피부클리닉, 비만클리닉, 교정클리닉, 여성클리닉,")
					.append("비만, 여드름, 피부질환, 에스테틱,침톡스, 한방성형, 다이어트, 안면비대칭교정, 주름, 가슴힙, 코성형, 붓기관리, 안면홍조, 통증,")
					.append("위대장, 호흡기, 피부 질환, 24체질연구소, 체칠침,한방 다이어트, 성형, 섭식장애 교정, 일대일 맞춤 컨설팅, 다이어리 관리, 단계별 탕약,")
					.append("성조숙증 치료, 한방부읜과전문의, 초경지연,양한방 협진, 목, 허리디스크, 신경통증, 근골격계 질환,")
					.append("위염, 역류성식도염, 신경성소화불량, 만성장염,천식, 폐질환, 대장염, 아토피, 피부염,")
					.append("질염, 방광염, 질건조증, 산후몸조리, 한방성형,한방 각과, 침구과, 사상체질과,")
					.append("편두통, 신경성 두통, 아토피성 피부질환, 체력저하,여드름, 비염, 스테미너,비만, 성장, 탈모, 비염, 피부,")
					.append("산후 다이어트, 비만클리닉, 산후보약, 근육통 치료,비만, 여드름, 생리통,")
					.append("난치성 여드름 클리닉, 화농성, 좁쌀 여드름,비수술 척추, 체형, 골반교정, 턱관절, 일자목, 산후관리, 교통사고후유증,추나요법");
		String strings = keywordBuffer.toString().trim();
		String[] stringArr = strings.split(",");
		for(int i=0;i<stringArr.length;i++){
			allKeywordsList.add(stringArr[i]);
		}
		
		
		
		//allKeywordsList = keywordConnection.getAllKeywords("getAllKeywords", context);
		ArrayAdapter<String> keywordsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, allKeywordsList);
		search.setAdapter(keywordsAdapter);

		try {
			keywords1 = share.getData(context, RECENT_KEYWORD).split(",");
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		searchAdapter = new SearchAdapter(context, keywords1);
		recentListView.setAdapter(searchAdapter);
		recentListView.setOnItemClickListener(this);

		return rootView;
	}

	public static HardSearchFragment create(Context context) {
		HardSearchFragment searchFragment = new HardSearchFragment();

		searchFragment.setContext(context);

		return searchFragment;
	}

	private void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {

		String keywords;

		switch (v.getId()) {
		case R.id.button_fragment_search:

			String text = search.getText().toString();

			ConnectionBridge conn = new ConnectionBridge();
			ArrayList<KmClinicView> kmClinicViewList = conn.getAllKmClinicListKeyword("getKmClinicListByKeyword", context, text);
			kmClinicViewList.toString();

			if (share.isExist(context, RECENT_KEYWORD)) {
				try {
					keywords = share.getData(context, RECENT_KEYWORD);

					keywords = keywords + text + ",";

					share.setData(context, RECENT_KEYWORD, keywords);

				} catch (RecordNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				share.setData(context, RECENT_KEYWORD, text + ",");
			}

			try {
				keywords1 = share.getData(context, RECENT_KEYWORD).split(",");
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
			
			Fragment fragment = SearchResultClinicListFragment.create(context, kmClinicViewList);
			FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

			// 검색 버튼을 누르면 바로 추가가 되도록 하는 코드 이지만
			// 나중에는 어차피 검색 버튼을 누르면 화면이 바로 넘어가서 검색 결과를 보여 줄 예정이므로, 상관 없음.
			// searchAdapter = new SearchAdapter(context, keywords1);
			// recentListView.setAdapter(searchAdapter);

			break;

		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		Log.d("kim", "itemClick item is " + searchAdapter.getItem(position));

		search.setText((CharSequence) searchAdapter.getItem(keywords1.length - position - 1));

	}

}
