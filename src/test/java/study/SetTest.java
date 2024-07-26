package study;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Set 클래스 테스트")
public class SetTest {
	private Set<Integer> numbers;

	@BeforeEach
	void setup() {
		numbers = new HashSet<>();
		for (int i = 1; i <= 5; i++) {
			numbers.add(i);
		}
	}

	@Nested
	@DisplayName("size 메서드 테스트")
	public class Size {
		@Test
		@DisplayName("Set에 저장된 요소의 개수를 확인한다.")
		void testSize() {
			// given
			// when
			int size = numbers.size();

			// then
			assertThat(size).isEqualTo(5);
		}
	}

	@Nested
	@DisplayName("contains 메서드 테스트")
	public class Contains {
		@Test
		@DisplayName("Set에 포함된 값인지 확인한다.")
		void testContains() {
			// given
			// when
			// then
			assertThat(numbers.contains(1)).isTrue();
			assertThat(numbers.contains(2)).isTrue();
			assertThat(numbers.contains(3)).isTrue();
			assertThat(numbers.contains(4)).isTrue();
			assertThat(numbers.contains(5)).isTrue();
		}

		@Test
		@DisplayName("Set에 없는 값을 확인할 때 false를 리턴한다.")
		void testNotContains() {
			// given
			// when
			// then
			assertThat(numbers.contains(100)).isFalse();
		}

		@ParameterizedTest
		@DisplayName("Set에 포함된 값인지 확인한다. (ParameterizedTest")
		@ValueSource(ints = {1, 2, 3, 4, 5})
		void testContainsParameterized(int number) {
			assertThat(numbers.contains(number)).isTrue();
		}

		@ParameterizedTest
		@CsvSource(value = {"1:true", "2:true", "3:true", "4:true", "5:true", "100:false"}, delimiter = ':')
		void testContainsParameterizedCsv(int input, boolean expected) {
			assertThat(numbers.contains(input)).isEqualTo(expected);
		}
	}

}
