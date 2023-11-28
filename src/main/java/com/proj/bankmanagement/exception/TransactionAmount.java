package com.proj.bankmanagement.exception;

public class TransactionAmount extends RuntimeException {

	private String message;

	public TransactionAmount(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
