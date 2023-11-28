package com.proj.bankmanagement.exception;

public class BranchNotFound extends RuntimeException{

	private String message;
	
	public BranchNotFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
