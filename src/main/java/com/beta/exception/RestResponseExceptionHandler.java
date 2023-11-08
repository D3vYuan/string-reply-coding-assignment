package com.beta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.beta.component.ReplyMessage;
import com.beta.constant.MessageConstant;

import lombok.extern.slf4j.Slf4j;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@ResponseBody
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { InvalidInputException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ReplyMessage handleBadRequest(InvalidInputException ex) {
		log.error("Encountered: {}", ex.getMessage());
		return new ReplyMessage(MessageConstant.INVALID_INPUT);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ReplyMessage handleAllOtherException(Exception ex) {
		log.error("Encountered: {}", ex.getMessage());
		return new ReplyMessage(MessageConstant.UNKNOWN_ERROR);
	}
}
