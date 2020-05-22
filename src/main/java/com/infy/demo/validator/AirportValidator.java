package com.infy.demo.validator;

import java.time.LocalDate;

import com.infy.demo.model.Flight;

public class AirportValidator {
	public static void validateFlight(Flight flight) throws Exception{
		if(!isValidDate(flight.getDateOfDeparture(), flight.getDateOfArrival()))
			throw new Exception("AirportValidator.INVALID_ARRIVAL_DATE");
		
	}
	
	public static boolean isValidDate(LocalDate departureDate, LocalDate arrivalDate){
		LocalDate today = LocalDate.now();
		if(departureDate.isBefore(today)) return false;
		if(arrivalDate.isBefore(departureDate)) return false;
		return true;
	}
	
}


