package study;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StringTest {
	@Nested
	public class Split {
		@Test
		void testSplit() {
			// given
			String target = "1,2";

			// when
			String[] result = target.split(",");

			// then
			assertThat(result).contains("1");
			assertThat(result).contains("2");
			assertThat(result).containsExactly("1", "2");
		}

		@Test
		void testOneElement() {
			// given
			String target = "1";

			// when
			String[] result = target.split(",");

			// then
			assertThat(result).containsExactly("1");
		}

		@Test
		void testFail() {
			// given
			String target = "1,2";

			// when
			String[] result = target.split(",");

			// then

			assertThatThrownBy(() -> {
				assertThat(result).containsExactly("1");
			});
		}
	}

	@Nested
	public class Substring {
		@Test
		void testSubstring() {
			// given
			String target = "(1,2)";

			// when
			String result = target.substring(1, target.length() - 1);

			// then
			assertThat(result).isEqualTo("1,2");
		}

		@Test
		void testSubstringFail() {
			// given
			String target = "(1,2) ";

			// when
			String result = target.substring(1, target.length() - 1);

			// then
			assertThatThrownBy(() -> {
				assertThat(result).isEqualTo("1,2");
			});
		}
	}

	@Nested
	public class CharAt {
		@Test
		@DisplayName("String 의 n 번째 문자를 가져올 수 있다.")
		void testCharAt() {
			// given
			String target = "abc";

			// when
			char result = target.charAt(1);

			// then
			assertThat(result).isEqualTo('b');
		}

		@Test
		@DisplayName("String 의 길이보다 긴 인덱스를 가져오려고 하면 예외가 발생한다.")
		void testCharAtFail() {
			// given
			String target = "abc";

			// when
			// then

			// assertThatThrownBy 사용
			assertThatThrownBy(() -> {
				assertThat(target.charAt(target.length())).isEqualTo('b');
			}).isInstanceOf(StringIndexOutOfBoundsException.class)
				.hasMessageContaining(String.format("String index out of range: %d", target.length()));

			// Message matching, assertThatExceptionOfType 사용
			assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
				.isThrownBy(() -> {
					// then
					assertThat(target.charAt(target.length())).isEqualTo('b');
				}).withMessageMatching("String index out of range: \\d");
		}
	}

	@Nested
	@DisplayName("AssertJ Exception 학습")
	public class ExceptionAssertion {
		@Test
		void testIllegalArgumentException() {
			// given
			String target = "abc";
			// when
			// then
			assertThatIllegalArgumentException().isThrownBy(() -> {
				Integer.parseInt(target);
			}).withMessageMatching("For input string: \".+\"");
		}

		@Test
		void testIllegalStateException() {
			// given
			// when
			// then
			assertThatIllegalStateException().isThrownBy(() -> {
				throw new IllegalStateException("Illegal State");
			}).withMessage("Illegal State");
		}

		@Test
		void testNullPointerException() {
			// given
			String target = null;
			// when
			// then
			assertThatNullPointerException().isThrownBy(() -> {
				target.length();
			});
		}

		@Test
		void testIOException() {
			// given
			// when
			// then
			assertThatIOException().isThrownBy(() -> {
				throw new IOException("IO Exception");
			}).withMessage("IO Exception");
		}
	}
}

