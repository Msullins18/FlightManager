package com.infy.demo.exceptions;

public class NoFlightsAvailableException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NoFlightsAvailableException()
	{
		super("No flight schedule available. Please select a different date.");
	}
}
