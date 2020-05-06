package com.infy.demo.exceptions;

public class NoFlightsAvailableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoFlightsAvailableException()
	{
		super("No Flights Available");
	}
}
