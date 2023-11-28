package com.proj.bankmanagement.exception;

public class PasswordIncorrect extends RuntimeException {

	private String message;

	public PasswordIncorrect(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
