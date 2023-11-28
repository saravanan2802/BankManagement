package com.proj.bankmanagement.exception;

public class NoAccountTypeFound extends RuntimeException {

	private String message;

	public NoAccountTypeFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
