package com.infy.demo.exceptions;

public class AirportExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AirportExistsException(String airportName)
	{
		super("Airport "+ airportName + " already exists!");
	}
}
