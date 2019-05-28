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
		alphabeticalOrder.add("á");
		alphabeticalOrder.add("â");
		alphabeticalOrder.add("ã");
		alphabeticalOrder.add("ä");
		alphabeticalOrder.add("å");
		alphabeticalOrder.add("æ");
		alphabeticalOrder.add("ç");
		alphabeticalOrder.add("è");
		alphabeticalOrder.add("é");
		alphabeticalOrder.add("ê");
		alphabeticalOrder.add("ë");
		alphabeticalOrder.add("ì");
		alphabeticalOrder.add("í");
		alphabeticalOrder.add("î");
		alphabeticalOrder.add("ï");
		alphabeticalOrder.add("ð");
		alphabeticalOrder.add("ñ");
		alphabeticalOrder.add("ó");
		alphabeticalOrder.add("ô");
		alphabeticalOrder.add("õ");
		alphabeticalOrder.add("ö");
		alphabeticalOrder.add("÷");
		alphabeticalOrder.add("ø");
		alphabeticalOrder.add("ù");

	}

	private static String replaceTones(String string) {

		string = string.replaceAll("Ü", "á");
		string = string.replaceAll("Ý", "å");
		string = string.replaceAll("Þ", "ç");
		string = string.replaceAll("ß", "é");
		string = string.replaceAll("ü", "ï");
		string = string.replaceAll("ý", "õ");
		string = string.replaceAll("þ", "ù");

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
