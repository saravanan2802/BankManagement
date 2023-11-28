package com.proj.bankmanagement.exception;

public class BankNotFound extends RuntimeException {

	private String message;

	public BankNotFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
