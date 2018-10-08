package com.softserve.edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that contains two methods: extract string and extract first int from
 * this string
 * 
 * @author Mykhailo Levchun
 *
 */

public final class Parser {

	/** Integer number that is extracted */
	private final static String PATTERN_UNSIGNED_NUMBER = "\\d+";

	/** Error message appears when extraction failed */
	private final static String EXTRACT_NUMBER_MESSAGE = "NumberFormatException for pattern =  %s text =  %s";

	/**
	 * Method extracts string from @param text by rule @param pattern
	 * 
	 */
	public static String extractFirstString(String pattern, String text) {
		String result = new String();
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		if (matcher.find()) {
			result = text.substring(matcher.start(), matcher.end());
		}
		return result;
	}

	/**
	 * Method extracts first number from @param text
	 */
	public static int extractFirstNumber(String text) {
		int result = -1;
		String extractText = extractFirstString(PATTERN_UNSIGNED_NUMBER, text);
		if (!extractText.isEmpty()) {
			try {
				result = Integer.parseUnsignedInt(extractText);
			} catch (NumberFormatException e) {
				throw new RuntimeException(String.format(EXTRACT_NUMBER_MESSAGE, PATTERN_UNSIGNED_NUMBER, text));
			}
		}
		return result;
	}

}
