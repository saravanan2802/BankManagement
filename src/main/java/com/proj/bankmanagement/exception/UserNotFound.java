package com.proj.bankmanagement.exception;

public class UserNotFound extends RuntimeException {

	private String message;

	public UserNotFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
