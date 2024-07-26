package study;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {
	private final Calculator calculator = new Calculator();

	@Nested
	public class DefaultDelimiterTest {
		@Test
		@DisplayName(",로 구분된 숫자 두 개를 더한다.")
		void testCommaDelimiterString() {
			// given
			String text = "1,2";
			// when
			int result = calculator.add(text);
			// then
			assertEquals(3, result);
		}

		@Test
		@DisplayName("숫자 하나를 입력한 경우 그대로 리턴한다.")
		void testOneNumberString() {
			// given
			String text = "1";
			// when
			int result = calculator.add(text);
			// then
			assertEquals(1, result);
		}

		@Test
		@DisplayName("빈 문자열을 입력한 경우 0을 리턴한다.")
		void testEmptyString() {
			// given
			String text = "";
			// when
			int result = calculator.add(text);
			// then
			assertEquals(0, result);
		}

		@ParameterizedTest
		@CsvSource(value = {"1,2,3:6", "1,2,3,4:10", "1,2,3,4,5:15"}, delimiter = ':')
		@DisplayName("콤마로 구분된 숫자를 더한다.")
		void testCommaDelimiterStringWithMultipleNumbers(String text, int expected) {
			// when
			int result = calculator.add(text);
			// then
			assertEquals(expected, result);
		}

		@ParameterizedTest
		@CsvSource(value = {"-1,-2,3", "1,2,-3", "-1", "2,3,4:-1"}, delimiter = ';')
		@DisplayName("음수를 입력한 경우 RuntimeException을 던진다.")
		void testNegativeNumber(String text) {
			// given
			// when
			// then
			assertThrows(RuntimeException.class, () -> calculator.add(text));
		}

		@ParameterizedTest
		@CsvSource(value = {"1:2:3;6", "1:2:3:4;10", "1:2:3:4:5;15"}, delimiter = ';')
		@DisplayName("콜론으로 구분된 숫자를 더한다.")
		void testColonDelimiterStringWithMultipleNumbers(String text, int expected) {
			// when
			int result = calculator.add(text);
			// then
			assertEquals(expected, result);
		}

		@ParameterizedTest
		@CsvSource(value = {"1,2:3;6", "1:2,3,4;10", "1:2:3:4,5:;15", "::;0"}, delimiter = ';')
		@DisplayName("콤마와 콜론으로 구분된 숫자를 더한다.")
		void testCommaAndColonDelimiterStringWithMultipleNumbers(String text, int expected) {
			// when
			int result = calculator.add(text);
			// then
			assertEquals(expected, result);
		}
	}

	@Nested
	public class CustomDelimiterTest {
		@Test
		@DisplayName("커스텀 구분자로 '-'를 입력한 경우")
		void testCustomDelimiterWithMinus() {
			// given
			String text = "//-\n1-2-3-----4--5";
			// when
			int result = calculator.add(text);
			// then
			assertEquals(15, result);
		}

		@Test
		@DisplayName("커스텀 구분자로 ';'를 입력한 경우")
		void testCustomDelimiterWithSemicolon() {
			// given
			String text = "//;\n1;2;3;;;;4;;5";
			// when
			int result = calculator.add(text);
			// then
			assertEquals(15, result);
		}

		@Test
		@DisplayName("커스텀 구분자로 '\\n'를 입력한 경우")
		void testCustomDelimiterWithNewLine() {
			// given
			String text = "//\n\n1\n2\n3\n\n\n\n4\n\n5";
			// when
			// then
			assertThrows(RuntimeException.class, () -> calculator.add(text));
		}

		@Test
		@DisplayName("커스텀 구분자로 '/'를 입력한 경우")
		void testCustomDelimiterWithSlash() {
			// given
			String text = "///\n1/2/3/////4//5";
			// when
			int result = calculator.add(text);
			// then
			assertEquals(15, result);
		}

		// '문자' 니까 문자열에 대해서는 거부?
		@Test
		@DisplayName("커스텀 구분자로 문자열 입력한 경우")
		void testCustomDelimiterWithString() {
			// given
			String text = "//abc\n1abc2";
			// when
			// then
			assertThrows(RuntimeException.class, () -> calculator.add(text));
		}
	}
}