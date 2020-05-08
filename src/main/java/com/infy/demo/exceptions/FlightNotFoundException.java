package com.infy.demo.exceptions;

public class FlightNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FlightNotFoundException(Integer flightId)
	{
		super("Could not find a flight with flight id: " + flightId);
		}
	
	
}
