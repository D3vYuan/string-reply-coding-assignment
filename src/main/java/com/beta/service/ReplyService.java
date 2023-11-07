package com.beta.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.beta.component.MessageProcessorEnum;
import com.beta.component.ReplyMessage;
import com.beta.constant.MessageConstant;
import com.beta.processor.MessageProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyService {

	public ReplyMessage process(String message) {
		if (StringUtils.isAllBlank(message)) {
			return new ReplyMessage("Message is empty");
		}

		log.info("Received: {}", message);
		String[] messageParts = message.split("-");

		String messageRules = messageParts[0];
		String originalMessage = messageParts[1];

		String[] messageRulesParts = messageRules.split("");
		String processedMessage = originalMessage;

		for (int i = 0; i < messageRulesParts.length; i++) {
			String currentRule = messageRulesParts[i];
			Optional<MessageProcessorEnum> processorOptional = MessageProcessorEnum.getProcessorByID(currentRule);
			if (!processorOptional.isPresent()) {
				processedMessage = MessageConstant.INVALID_INPUT;
				break;
			}

			MessageProcessorEnum processorEnum = processorOptional.get();
			MessageProcessor processor = processorEnum.getProcessor();
			processedMessage = processor.process(processedMessage);
		}

		return new ReplyMessage(processedMessage);
	}
}
