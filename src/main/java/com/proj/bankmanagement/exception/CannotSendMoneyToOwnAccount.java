package com.proj.bankmanagement.exception;

public class CannotSendMoneyToOwnAccount extends RuntimeException {

	private String message;

	public CannotSendMoneyToOwnAccount(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
