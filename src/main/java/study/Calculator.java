package study;

import java.util.stream.Stream;

public class Calculator {
	public int add(String text) {
		if (text.isEmpty()) {
			return 0;
		}
		final String regex = getDelimiterRegex(text);
		final String numbers = text.startsWith("//") ? text.substring(4) : text;
		return Stream.of(numbers.split(regex))
			.mapToInt(this::parseNumber)
			.sum();
	}

	private String getDelimiterRegex(String text) {
		if (text.startsWith("//") && text.indexOf("\n") == 3) {
			return String.format("[,:%c]", text.charAt(2));
		}
		return "[,:]";
	}

	// private method 인데 어떻게 테스트를 해야하는걸까?
	// 문자열 시작에서 \\과 \n의 사이의 문자에 대해서 custom delimiter 를 지정함.
	private int parseNumber(String number) {
		if (number.isEmpty()) {
			return 0;
		}
		try {
			final int ret = Integer.parseInt(number);
			if (ret < 0) {
				throw new RuntimeException("number is not positive");
			}
			return ret;
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
	}
}
