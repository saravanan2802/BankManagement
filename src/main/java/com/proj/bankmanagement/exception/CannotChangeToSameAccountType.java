package com.proj.bankmanagement.exception;

public class CannotChangeToSameAccountType extends RuntimeException {

	private String message;

	public CannotChangeToSameAccountType(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
