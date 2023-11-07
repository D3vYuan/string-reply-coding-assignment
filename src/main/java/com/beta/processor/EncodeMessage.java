package com.beta.processor;

import com.beta.utility.StringUtility;

public class EncodeMessage implements MessageProcessor {

	@Override
	public String process(String message) {
		String hashMessage = StringUtility.hashString(message);
		return StringUtility.formatStringAsHexValues(hashMessage);
	}
}
