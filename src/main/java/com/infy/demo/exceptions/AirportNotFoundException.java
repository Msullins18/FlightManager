package com.infy.demo.exceptions;

public class AirportNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AirportNotFoundException(Integer airportId)
	{
		super("Could not find a airport with airport id: " + airportId);
	}
}
