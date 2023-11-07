package com.beta.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringUtilityTest {
	@BeforeEach
	void setup() {

	}

	@AfterEach
	void destroy() {

	}

	@ParameterizedTest
	@DisplayName("Reverse String")
	@CsvSource(value = { "abc, cba", "abc123, 321cba", "'', Invalid input", "NIL, Invalid input" }, nullValues = "NIL")
	void testReverseString(String input, String expected) {
		String actual = StringUtility.reverseString(input);
		assertThat(actual).isEqualTo(expected);
	}
}
