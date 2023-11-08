package com.beta.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.beta.constant.TestV2Constant;
import com.beta.utility.StringUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReplyControllerv2Test {
	@LocalServerPort
	int port;

	@Autowired
	MockMvc mock;

	@Autowired
	ObjectMapper mapper;

	@DisplayName("v2 - without message")
	@Test
	void testv1_withoutmessage() throws Exception {
		String input = TestV2Constant.V2_EMPTY_INPUT;
		String response = TestV2Constant.V2_EMPTY_RESPONSE;

		log.debug("Test: Empty Input - {}", input);
		String endpointPath = String.format("%s", TestV2Constant.V2_ENDPOINT);

		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v2 - with rule 11")
	@Test
	void testv1_withrule11() throws Exception {
		String input = TestV2Constant.V2_RULE_11_INPUT;

		String message = input.split("-")[1];
		String response = StringUtility.reverseString(StringUtility.reverseString(message));

		log.debug("Test: Rule 11 - {}", input);
		String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v2 - with rule 12")
	@Test
	void testv1_withrule12() throws Exception {
		String input = TestV2Constant.V2_RULE_12_INPUT;

		String message = input.split("-")[1];
		String response = StringUtility.hashString(StringUtility.reverseString(message));

		log.debug("Test: Rule 12 - {}", input);
		String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v2 - with rule 21")
	@Test
	void testv1_withrule21() throws Exception {
		String input = TestV2Constant.V2_RULE_21_INPUT;

		String message = input.split("-")[1];
		String response = StringUtility.reverseString(StringUtility.hashString(message));

		log.debug("Test: Rule 21 - {}", input);
		String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v2 - with rule 22")
	@Test
	void testv1_withrule22() throws Exception {
		String input = TestV2Constant.V2_RULE_22_INPUT;

		String message = input.split("-")[1];
		String response = StringUtility.hashString(StringUtility.hashString(message));

		log.debug("Test: Rule 21 - {}", input);
		String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v2 - with rule 13")
	@Test
	void testv1_withrule13() throws Exception {
		String input = TestV2Constant.V2_RULE_13_INPUT;
		String response = TestV2Constant.V2_INVALID_RESPONSE;

		log.debug("Test: Rule 13 - {}", input);
		String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v2 - with rule 31")
	@Test
	void testv1_withrule31() throws Exception {
		String input = TestV2Constant.V2_RULE_31_INPUT;
		String response = TestV2Constant.V2_INVALID_RESPONSE;

		log.debug("Test: Rule 31 - {}", input);
		String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v2 - with rule 33")
	@Test
	void testv1_withrule33() throws Exception {
		String input = TestV2Constant.V2_RULE_33_INPUT;
		String response = TestV2Constant.V2_INVALID_RESPONSE;

		log.debug("Test: Rule 33 - {}", input);
		String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}
}
