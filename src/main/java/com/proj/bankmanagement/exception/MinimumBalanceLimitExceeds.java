package com.proj.bankmanagement.exception;

public class MinimumBalanceLimitExceeds extends RuntimeException {

	private String message;

	public MinimumBalanceLimitExceeds(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
