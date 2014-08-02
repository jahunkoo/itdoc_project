package com.kmbridge.itdoc.util;

public class Sentence {


	/**
	 * Intro
	 */ 
	public static final String networkErrorMessage = "네트워크 상태를 확인하세요";

	/**
	 * Main
	 */
	public static final String networkErrorPleaseRetry= "네트워크 상태가 고르지 않아요. 다시 눌러보세요";
	public static final String closeAppMessage = "'뒤로'버튼을 한번 더 누르면 종료됩니다.";


	
	public static final String noInfomationMessage = "모든 정보는 필수 입력사항 입니다.";
	public static final String noEmailMessage = "이메일을 입력해주세요.";
	public static final String noPwdMessage = "비밀번호를 입력해주세요.";
	public static final String noLastNameMessage = "성을 입력해주세요.";
	public static final String noFirstNameMessage = "이름을 입력해주세요.";
	public static final String noPhoneMessage = "전화번호를 입력해주세요.";
	public static final String noAgeMessage = "나이를 입력해주세요.";
	public static final String notEmailType = "이메일 형식이 아닙니다.";
	public static final String notPwdType = "비밀번호는 최소 6자리 이상입니다.";
	public static final String existEmail = "이미 등록된 이메일 입니다.";
	public static final String joinFail = "회원가입 실패";
	public static final String successJoin = "회원가입 완료!";
	public static final String successLogin = "로그인 되었습니다.";
	public static final String errorLogin = "로그인 실패! 아이디와 비밀번호를 확인해 주세요.";
	public static final String successProfileImage = "사진등록 완료!";

	/**
	 * DialogFragments
	 */
	public static final String birthDialogTitle = "출생 연도";
	public static final String consultableDialogTitle = "협의 여부";
	public static final String contact_call = "전화 걸기";
	public static final String contact_sms = "문자 보내기";
	public static final String contact_cancel = "취소";
	public static final String genderDialogTitle = "성별 선택";
	public static final String gradeDialogTitle = "학년 선택";
	public static final String locationDialogTitle = "과외 지역 선택";
	public static final String priceDialogTitle = "과외 가격";
	public static final String schoolDialogTitle = "학교 선택";
	public static final String subjectDialogTitle = "과외 과목 선택";

	/**
	 * T_oauth
	 */
	public static final String oauth_success = "인증 되셨어요!"; 
	public static final String oauth_wrongNum = "번호가 틀리셨어요!";
	public static final String send_sms_seccess = "인증번호가 발송되었어요";

	/**
	 * profileImg Select dialog 
	 */
	public static final String btnGallary = "사진 앨범";
	public static final String btnCamera = "프로필 삭제";
	public static final String btnDelete = "취소";
	public static final String isDelete = "정말로 삭제합니까?";

	/**
	 *  GetTeacherListsTask
	 */
	public static final String ERROR_MESSAGE = "서버와의 통신이 원할하지 않아요. 네트워크 상태를 확인해주세요";

	/**
	 * D_serverCheckFragment
	 */
	public static final String UPDATE_RECOMMENDATION = "더 좋은 기능이 숨어있는 새로운 버전이 등장했어요!";



	/**
	 * Teacher speciality verification Regular Expression
	 */
	public static final String teacherSpecialityReg = "\\d{3}\\s\\d{4}\\s\\d{4}|([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})|[0-9]{3}[-:]?[0-9]{4}[-:]?[0-9]{4}|[가-하]{3}[-:]?[가-하]{4}[-:]?[가-하]{4}|카톡|까톡|카카오톡|katok|katalk";

}