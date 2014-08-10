package com.kmbridge.itdoc.util;

import java.util.HashMap;

import com.kmbridge.itdoc.exception.CertificationException;

public class CertificationUtil extends ItDocConstants {

	public static final String CERTIFICATION_KEY_EMAIL = "certification_key_email";
	public static final String CERTIFICATION_KEY_PHONE_NUMBER = "certification_key_phone_number";
	public static final String CERTIFICATION_KEY_FACEBOOK = "certification_key_facebook";

	// 이메일 - 4
	// 전화번호 - 2
	// 페이스북 - 1
	public static HashMap<String, Boolean> certificationCheck(int info) throws CertificationException {

		HashMap<String, Boolean> certification = new HashMap<String, Boolean>();

		switch (info) {

		case 0:
			certification.put(CERTIFICATION_KEY_EMAIL, false);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, false);
			certification.put(CERTIFICATION_KEY_FACEBOOK, false);
			break;

		case 1:
			certification.put(CERTIFICATION_KEY_EMAIL, false);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, false);
			certification.put(CERTIFICATION_KEY_FACEBOOK, true);
			break;

		case 2:
			certification.put(CERTIFICATION_KEY_EMAIL, false);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, true);
			certification.put(CERTIFICATION_KEY_FACEBOOK, false);
			break;

		case 3:
			certification.put(CERTIFICATION_KEY_EMAIL, false);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, true);
			certification.put(CERTIFICATION_KEY_FACEBOOK, true);
			break;

		case 4:
			certification.put(CERTIFICATION_KEY_EMAIL, true);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, true);
			certification.put(CERTIFICATION_KEY_FACEBOOK, true);
			break;

		case 5:
			certification.put(CERTIFICATION_KEY_EMAIL, true);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, false);
			certification.put(CERTIFICATION_KEY_FACEBOOK, true);
			break;

		case 6:
			certification.put(CERTIFICATION_KEY_EMAIL, true);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, true);
			certification.put(CERTIFICATION_KEY_FACEBOOK, false);
			break;

		case 7:
			certification.put(CERTIFICATION_KEY_EMAIL, true);
			certification.put(CERTIFICATION_KEY_PHONE_NUMBER, true);
			certification.put(CERTIFICATION_KEY_FACEBOOK, true);
			break;

		default:

			throw new CertificationException();

		}

		return certification;
	}
}
