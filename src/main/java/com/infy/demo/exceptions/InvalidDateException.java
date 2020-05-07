package com.infy.demo.exceptions;

public class InvalidDateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDateException() {
		super("Invalid date. Please select a vaild date within one year.");
	}
	
}
