package com.beta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.component.ReplyMessage;
import com.beta.exception.InvalidInputException;
import com.beta.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v2")
public class ReplyControllerv2 {

	private ReplyService replyService;

	public ReplyControllerv2(ReplyService replyService) {
		this.replyService = replyService;
	}

	@GetMapping("/reply")
	public ReplyMessage replying() throws InvalidInputException {
		return replyService.process(null);
	}

	@GetMapping("/reply/{message:[0-9]{2}-.*}")
	public ReplyMessage replying(@PathVariable String message) throws InvalidInputException {
		return replyService.process(message);
	}
}