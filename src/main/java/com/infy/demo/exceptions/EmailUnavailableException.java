package com.infy.demo.exceptions;

public class EmailUnavailableException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmailUnavailableException(String emailId)
	{
		super("Email "+ emailId + " is already in use!");
	}
}
