package com.proj.bankmanagement.exception;

public class ManagerNotFound extends RuntimeException {

	private String message;

	public ManagerNotFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
