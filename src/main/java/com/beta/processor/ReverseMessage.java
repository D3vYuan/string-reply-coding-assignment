package com.beta.processor;

import com.beta.utility.StringUtility;

public class ReverseMessage implements MessageProcessor {

	@Override
	public String process(String message) {
		return StringUtility.reverseString(message);
	}

}
