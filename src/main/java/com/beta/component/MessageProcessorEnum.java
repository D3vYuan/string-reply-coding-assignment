package com.beta.component;

import java.util.Arrays;
import java.util.Optional;

import com.beta.processor.EncodeMessage;
import com.beta.processor.MessageProcessor;
import com.beta.processor.ReverseMessage;

import lombok.Getter;

@Getter
public enum MessageProcessorEnum {
	REVERSE("1", "Reverse the string", new ReverseMessage()),
	ENCODE("2", "Encode the string with MD5 hash alogrithm", new EncodeMessage());

	private final String processorID;
	private final String processorDescription;
	private final MessageProcessor processor;

	private MessageProcessorEnum(String processorID, String processorDescription, MessageProcessor processor) {
		this.processorID = processorID;
		this.processorDescription = processorDescription;
		this.processor = processor;
	}

	public static Optional<MessageProcessorEnum> getProcessorByID(String processorId) {
		return Arrays.stream(MessageProcessorEnum.values())
				.filter(processor -> processor.getProcessorID().equals(processorId)).findAny();
	}

}
