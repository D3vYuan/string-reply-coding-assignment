package com.beta.exception;

public class InvalidInputException extends Exception {
	private static final long serialVersionUID = -6409605987495788886L;

	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}
}
