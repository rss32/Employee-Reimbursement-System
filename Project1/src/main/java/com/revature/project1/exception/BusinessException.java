package com.revature.project1.exception;

@SuppressWarnings("serial")
public class BusinessException extends Exception {
	public BusinessException() {
	}

	public BusinessException(final String message) {
		super(message);
	}
}