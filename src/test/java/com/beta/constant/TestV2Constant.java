package com.beta.constant;

public final class TestV2Constant {
	public static String V2_ENDPOINT = "/v2/reply";

	public static String V2_EMPTY_INPUT = "";
	public static String V2_TEST_INPUT = "kbzw9ru";
	public static String V2_EMPTY_RESPONSE = "Message is empty";
	public static String V2_INVALID_RESPONSE = "Invalid input";
	public static String V2_RESPONSE_MESSAGE_JSONPATH = "$.message";

	public static String V2_RULE_11_INPUT = String.format("%s-%s", "11", V2_TEST_INPUT);
	public static String V2_RULE_12_INPUT = String.format("%s-%s", "12", V2_TEST_INPUT);
	public static String V2_RULE_21_INPUT = String.format("%s-%s", "21", V2_TEST_INPUT);
	public static String V2_RULE_22_INPUT = String.format("%s-%s", "22", V2_TEST_INPUT);
	public static String V2_RULE_13_INPUT = String.format("%s-%s", "13", V2_TEST_INPUT);
	public static String V2_RULE_31_INPUT = String.format("%s-%s", "31", V2_TEST_INPUT);
	public static String V2_RULE_33_INPUT = String.format("%s-%s", "33", V2_TEST_INPUT);
}
