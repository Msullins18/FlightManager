package com.infy.demo.validator;

import java.time.LocalDate;

import com.infy.demo.exceptions.InvalidArrivalDateException;
import com.infy.demo.model.Flight;

public class AirportValidator {
	public static void validateFlight(Flight flight){
		if(!isValidDate(flight.getDateOfDeparture(), flight.getDateOfArrival()))
			throw new InvalidArrivalDateException();
		
	}
	
	public static boolean isValidDate(LocalDate departureDate, LocalDate arrivalDate){
		LocalDate today = LocalDate.now();
		if(departureDate.isBefore(today)) return false;
		if(arrivalDate.isBefore(departureDate)) return false;
		return true;
	}
	
}


