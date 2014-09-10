package rach.validation.phone.au;

public class Start {

	public static void main(String[] args) {

		String validAUNums[] = {"+612 3892 1111","0411 234 567","(02) 3892 1111","38921111","0411234567","0411-234-567","+61411-234-567"};
		String invalidAUNums[] = {"612 3892 1111","+61 2 3892 1111","+61 (02) 3892 1111","3892 11","diane 0411 234 567","bob","(02) 3892 - 1111","(02) 3892 -1111"};

		System.out.println(PhoneVal.getExampleValidPhoneNumbers());
		System.out.println(PhoneVal.getExampleInvalidPhoneNumbers());


		System.out.println("\nTesting Valid numbers...\n");
		for (String s : validAUNums) {
			System.out.println("Current number: " + s);
			System.out.println("Stripped to digits: " + PhoneVal.stripStringToNumbers(s));
			System.out.println("First 3 digits: " + PhoneVal.getNumberPrefix(s,3));
			System.out.println("Valid number: " + PhoneVal.validateAUNumber(s));
			System.out.println("isMobile: " + PhoneVal.isMobile(s));
			System.out.println("isMobileWithCountryCode: " + PhoneVal.isMobileWithAUCountryCode(s));
			System.out.println("isLandLine: " + PhoneVal.isLandLine(s));
			System.out.println("isLandLineWithAreaCode: " + PhoneVal.isLandLineWithAreaCode(s));
			System.out.println("isLandLineWithCountryCode: " + PhoneVal.isLandLineWithCountryCode(s));
			System.out.println();
		}

		System.out.println("\nTesting Invalid numbers...\n");
		for (String s : invalidAUNums) {
			System.out.println("Current number: " + s);
			System.out.println("Stripped to digits: " + PhoneVal.stripStringToNumbers(s));
			System.out.println("First 3 digits: " + PhoneVal.getNumberPrefix(s,3));
			System.out.println("Valid number: " + PhoneVal.validateAUNumber(s));
			System.out.println("isMobile: " + PhoneVal.isMobile(s));
			System.out.println("isMobileWithCountryCode: " + PhoneVal.isMobileWithAUCountryCode(s));
			System.out.println("isLandLine: " + PhoneVal.isLandLine(s));
			System.out.println("isLandLineWithAreaCode: " + PhoneVal.isLandLineWithAreaCode(s));
			System.out.println("isLandLineWithCountryCode: " + PhoneVal.isLandLineWithCountryCode(s));
			System.out.println();
		}

		String stateAbbsCorrect[] = {"VIC","NSW","ACT","QLD","NT","WA","SA","TAS"};
		String stateCodes[] = {"03","02","02","07","08","08","08","03"};

		System.out.println("\nPrinting correct matches...\n");
		for (int x = 0; x < stateCodes.length; x ++) {
			System.out.println(stateCodes[x] + " " + stateAbbsCorrect[x] + ": " + PhoneVal.validateAUAreaCode(stateCodes[x], stateAbbsCorrect[x]));
		}

		String stateAbbsIncorrect[] = {"SA","VIC","WA","TAS","QLD","ACT","NSW","NT"};

		System.out.println("\nPrinting incorrect matches...\n");
		for (int x = 0; x < stateCodes.length; x ++) {
			System.out.println(stateCodes[x] + " " + stateAbbsIncorrect[x] + ": " + PhoneVal.validateAUAreaCode(stateCodes[x], stateAbbsIncorrect[x]));
		}

	}

}
