package utils;

import java.util.ArrayList;

public class AlphabeticalOrderGreek {

	private static ArrayList<String> alphabeticalOrder = new ArrayList<>();

	private AlphabeticalOrderGreek() {

	}

	public static String firstInAlphabeticalOrder(String first, String second) {

		if (alphabeticalOrder.isEmpty())
			createList();

		String firstString = first.toLowerCase();
		String secondString = second.toLowerCase();

		firstString = replaceTones(firstString);
		secondString = replaceTones(secondString);

		return getFirstInAlphabeticalOrder(first, second);

	}

	private static void createList() {

		alphabeticalOrder.add(" ");
		alphabeticalOrder.add(".");
		alphabeticalOrder.add("&");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");
		alphabeticalOrder.add("�");

	}

	private static String replaceTones(String string) {

		string = string.replaceAll("�", "�");
		string = string.replaceAll("�", "�");
		string = string.replaceAll("�", "�");
		string = string.replaceAll("�", "�");
		string = string.replaceAll("�", "�");
		string = string.replaceAll("�", "�");
		string = string.replaceAll("�", "�");

		return string;
	}

	private static String getFirstInAlphabeticalOrder(String first,
			String second) {

		String stringToReturn = null;

		for (int counter = 0; counter < Math.min(first.length(),
				second.length()); counter++) {

			if (alphabeticalOrder.indexOf(getStringAtIndex(first, counter)) < alphabeticalOrder
					.indexOf(getStringAtIndex(second, counter))) {
				stringToReturn = first;
				break;

			} else if (alphabeticalOrder.indexOf(getStringAtIndex(second,
					counter)) < alphabeticalOrder.indexOf(getStringAtIndex(
					first, counter))) {
				stringToReturn = second;
				break;
			}

		}

		if (stringToReturn != null)
			return stringToReturn;

		if (first.length() < second.length())
			return first;

		return second;

	}

	private static String getStringAtIndex(String string, int index) {
		return string.substring(index, index + 1);
	}

}
