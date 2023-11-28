package com.proj.bankmanagement.exception;

public class UserInSameBranch extends RuntimeException {

	private String message;

	public UserInSameBranch(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
