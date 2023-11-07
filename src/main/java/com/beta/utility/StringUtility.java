package com.beta.utility;

import org.apache.commons.lang3.StringUtils;

import com.beta.constant.MessageConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StringUtility {
	private StringUtility() {
		throw new IllegalStateException("StringUtility class");
	}

	public static String reverseString(String message) {
		log.debug("Processing: Reverse {}", message);
		if (StringUtils.isAllBlank(message)) {
			return MessageConstant.INVALID_INPUT;
		}
		return StringUtils.reverse(message);
	}

	public static String hashString(String message) {
		log.debug("Processing: Hashing {}", message);
		return message;
	}

	public static String formatStringAsHexValues(String message) {
		log.debug("Processing: Hexing {}", message);
		return message;
	}
}
