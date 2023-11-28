package com.proj.bankmanagement.exception;

public class AccountNotFound extends RuntimeException {

	private String message;

	public AccountNotFound(String message) {

		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
