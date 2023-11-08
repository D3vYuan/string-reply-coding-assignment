package com.beta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.component.ReplyMessage;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReplyController {

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(message);
	}
}