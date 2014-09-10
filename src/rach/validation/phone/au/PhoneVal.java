package rach.validation.phone.au;


public class PhoneVal {

	private final static String PHONE_EXPRESSION = "^\\({0,1}((0|\\+61)(2|4|3|7|8)){0,1}\\){0,1}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{1}(\\ |-){0,1}[0-9]{3}$";
	private final static String STATE_ABBREVIATIONS[] = {"VIC","NSW","ACT","QLD","NT","WA","SA","TAS"};
	private final static String STATE_CODES[] = {"03","02","02","07","08","08","08","03"};

	private final static String AU_COUNTRY_CODE_PREFIX = "61";
	private final static int AU_COUNTRY_CODE_LENGTH = 2;

	private final static String AU_MOBILE_PREFIX = "04";
	private final static int AU_MOBILE_PREFIX_LENGTH = 2;

	private final static String AU_MOBILE_WITH_COUNTRY_CODE_PREFIX = "614";
	private final static int AU_MOBILE_WITH_COUNTRY_CODE_LENGTH_PREFIX = 11;

	private final static int LANDLINE_LENGTH = 8;
	private final static int MOBILE_LENGTH = 8;
	private final static int AREA_CODE_LENGTH = 2;

	private final static int LANDLINE_WITH_AREA_CODE_LENGTH = LANDLINE_LENGTH + AREA_CODE_LENGTH;
	private final static int LANDLINE_WITH_COUNTRY_CODE_LENGTH = 11;
	private final static int LANDLINE_WITH_COUNTRY_CODE_PREFIX_LENGTH = 3;
	
	private final static String EXAMPLE_VALID_PHONE_NUMBERS[] = {"+612 3892 1111","0411 234 567","(02) 3892 1111","38921111","0411234567","0411-234-567","+61411-234-567"};
	private final static String EXAMPLE_INVALID_PHONE_NUMBERS[] = {"612 3892 1111","+61 2 3892 1111","+61 (02) 3892 1111","3892 11","diane 0411 234 567","bob","(02) 3892 - 1111","(02) 3892 -1111"};



	public static boolean validateAUNumber(String phoneNumber) {
		if (phoneNumber == null) return false;
		return phoneNumber.matches(PHONE_EXPRESSION);
	}

	public static boolean validateAUAreaCode(String areaCode, String state) {
		if (areaCode == null) return false;
		if (state == null) return false;
		for (int x = 0; x < STATE_ABBREVIATIONS.length; x ++) {
			if (STATE_ABBREVIATIONS[x].equals(state.toUpperCase())) {
				if (STATE_CODES[x].equals(areaCode)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isMobile(String phoneNumber) {
		if (phoneNumber == null) return false;
		String pn = stripStringToNumbers(phoneNumber);
		if (pn.length() != MOBILE_LENGTH) return false;
		String prefix = getNumberPrefix(pn, AU_MOBILE_PREFIX_LENGTH);
		if (prefix.equals(AU_MOBILE_PREFIX)) return true;
		return false;
	}

	public static boolean isMobileWithAUCountryCode(String phoneNumber) {
		if (phoneNumber == null) return false;
		String pn = stripStringToNumbers(phoneNumber);
		if (pn.length() != AU_MOBILE_WITH_COUNTRY_CODE_LENGTH_PREFIX) return false;
		String prefix = getNumberPrefix(pn,AU_MOBILE_WITH_COUNTRY_CODE_LENGTH_PREFIX);
		if (prefix.equals(AU_MOBILE_WITH_COUNTRY_CODE_PREFIX)) return true;
		return false;
	}

	public static boolean isLandLine(String phoneNumber) {
		if (phoneNumber == null) return false;
		String pn = stripStringToNumbers(phoneNumber);
		if (pn.length() != LANDLINE_LENGTH) return false;
		return true;
	}

	public static boolean isLandLineWithAreaCode(String phoneNumber) {
		if (phoneNumber == null) return false;
		String pn = stripStringToNumbers(phoneNumber);
		if (pn.length() != LANDLINE_WITH_AREA_CODE_LENGTH) return false;
		String prefix = getNumberPrefix(pn,LANDLINE_WITH_AREA_CODE_LENGTH);
		for (String s : STATE_CODES) {
			if (s.equals(prefix)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isLandLineWithCountryCode(String phoneNumber) {
		if (phoneNumber == null) return false;
		String pn = stripStringToNumbers(phoneNumber);
		if (pn.length() != LANDLINE_WITH_COUNTRY_CODE_LENGTH) return false;
		String completePrefix = getNumberPrefix(pn,LANDLINE_WITH_COUNTRY_CODE_PREFIX_LENGTH);
		String cPrefix = completePrefix.charAt(0) + "" + completePrefix.charAt(1);
		if (!cPrefix.equals(AU_COUNTRY_CODE_PREFIX)) return false;
		char codeNum = pn.charAt(2);
		for (String s : STATE_CODES) {
			if (s.charAt(1) == codeNum) {
				return true;
			}
		}
		return false;
	}

	public static String getNumberPrefix(String phoneNumber, int lengthRequired) {
		if (phoneNumber == null) return "no prefix";
		if (lengthRequired <= 0) return "invalid length";
		String pn = stripStringToNumbers(phoneNumber);
		if (!(phoneNumber.length() >= (LANDLINE_LENGTH + AREA_CODE_LENGTH))) return "no prefix";
		String prefix = "";
		for (int x = 0; x < lengthRequired; x ++) {
			prefix += pn.charAt(x);
		}
		return prefix;
	}

	public static String stripStringToNumbers(String phoneNumber) {
		if (phoneNumber == null) return "";
		String num = phoneNumber.trim();
		char nums[] = num.toCharArray();
		String strippedNum = "";
		for (char c : nums) {
			if (c >= '0' && c <= '9') {
				strippedNum += c;
			}
		}
		return strippedNum;
	}
	
	public static String getExampleValidPhoneNumbers() {
		String str = "Examples of phone numbers that are valid...\n";
		for (String s : EXAMPLE_VALID_PHONE_NUMBERS) {
			str += (s + "\n");
		}
		return str;
	}
	
	public static String getExampleInvalidPhoneNumbers() {
		String str = "Examples of phone numbers that are invalid...\n";
		for (String s : EXAMPLE_INVALID_PHONE_NUMBERS) {
			str += (s + "\n");
		}
		return str;
	}


}
