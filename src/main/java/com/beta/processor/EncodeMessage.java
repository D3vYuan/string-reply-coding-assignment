package com.beta.processor;

import com.beta.utility.StringUtility;

public class EncodeMessage implements MessageProcessor {

	@Override
	public String process(String message) {
		return StringUtility.hashString(message);
	}
}
