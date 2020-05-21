package com.infy.demo.exceptions;

public class InvalidArrivalDateException  extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidArrivalDateException()
	{
		super("The departure date can not be before today and arrival date can not be before departure date.");
	}
}
