package com.infy.demo.exceptions;

public class AirportDoesNotExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AirportDoesNotExistException(String airportName)
	{
		super("Airport "+ airportName + " does not exist!");
	}
}
