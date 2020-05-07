package com.infy.demo.exceptions;

public class NotEnoughTicketsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotEnoughTicketsException() 
	{
		super("Not Engough Tickets Available");
	}
	

}
