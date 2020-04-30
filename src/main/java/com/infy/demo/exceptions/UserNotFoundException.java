package com.infy.demo.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String emailId)
	{
		super("Could not find a user with email id: " + emailId);
	}
}
