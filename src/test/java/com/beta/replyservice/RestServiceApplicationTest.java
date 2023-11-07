package com.beta.replyservice;

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

import com.beta.constant.TestConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestServiceApplicationTest {

	@LocalServerPort
	int port;

	@Autowired
	MockMvc mock;

	@Autowired
	ObjectMapper mapper;

	@DisplayName("v1 - without message")
	@Test
	void testv1_withoutmessage() throws Exception {
		String input = TestConstant.V1_EMPTY_INPUT;
		String response = TestConstant.V1_EMPTY_RESPONSE;
		String endpointPath = String.format("%s", TestConstant.V1_ENDPOINT);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk()).andExpect(jsonPath(TestConstant.V1_RESPONSE_MESSAGE_JSONPATH, is(response)))
				.andReturn();
	}

	@DisplayName("v1 - with message")
	@Test
	void testv1_withmessage() throws Exception {
		String input = TestConstant.V1_VALID_INPUT;
		String response = TestConstant.V1_VALID_INPUT;
		String endpointPath = String.format("%s/%s", TestConstant.V1_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk()).andExpect(jsonPath(TestConstant.V1_RESPONSE_MESSAGE_JSONPATH, is(response)))
				.andReturn();
	}

	@DisplayName("v1 - helloworld")
	@Test
	void testv1_helloworld() throws Exception {
		String input = TestConstant.V1_HELLO_WORLD;
		String response = TestConstant.V1_HELLO_WORLD;
		String endpointPath = String.format("%s/%s", TestConstant.V1_ENDPOINT, input);
		mock.perform(get(endpointPath).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(status().isOk()).andExpect(jsonPath(TestConstant.V1_RESPONSE_MESSAGE_JSONPATH, is(response)))
				.andReturn();
	}
}
