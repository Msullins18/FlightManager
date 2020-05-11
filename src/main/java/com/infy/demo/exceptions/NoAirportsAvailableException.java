package com.infy.demo.exceptions;

public class NoAirportsAvailableException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NoAirportsAvailableException()
	{
		super("No Airports Available");
	}
}
