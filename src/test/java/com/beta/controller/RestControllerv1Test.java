package com.beta.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
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

import com.beta.constant.TestV1Constant;
import com.beta.constant.TestV2Constant;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestControllerv1Test {

	@LocalServerPort
	int port;

	@Autowired
	MockMvc mock;

	@Autowired
	ObjectMapper mapper;

	@DisplayName("v1 - without message")
	@Test
	void testv1_withoutmessage() throws Exception {
		String input = TestV1Constant.V1_EMPTY_INPUT;
		String response = TestV1Constant.V1_EMPTY_RESPONSE;
		String endpointPath = String.format("%s", TestV1Constant.V1_ENDPOINT);
		mock.perform(get(endpointPath).with(httpBasic(TestV2Constant.V2_API_USER, TestV2Constant.V2_API_PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV1Constant.V1_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v1 - with message")
	@Test
	void testv1_withmessage() throws Exception {
		String input = TestV1Constant.V1_VALID_INPUT;
		String response = TestV1Constant.V1_VALID_INPUT;
		String endpointPath = String.format("%s/%s", TestV1Constant.V1_ENDPOINT, input);
		mock.perform(get(endpointPath).with(httpBasic(TestV2Constant.V2_API_USER, TestV2Constant.V2_API_PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV1Constant.V1_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}

	@DisplayName("v1 - helloworld")
	@Test
	void testv1_helloworld() throws Exception {
		String input = TestV1Constant.V1_HELLO_WORLD;
		String response = TestV1Constant.V1_HELLO_WORLD;
		String endpointPath = String.format("%s/%s", TestV1Constant.V1_ENDPOINT, input);
		mock.perform(get(endpointPath).with(httpBasic(TestV2Constant.V2_API_USER, TestV2Constant.V2_API_PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk())
				.andExpect(jsonPath(TestV1Constant.V1_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
	}
}
