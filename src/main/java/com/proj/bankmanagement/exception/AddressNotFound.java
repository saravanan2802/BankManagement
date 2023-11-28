package com.proj.bankmanagement.exception;

public class AddressNotFound extends RuntimeException {

	private String message;

	public AddressNotFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
