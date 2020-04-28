package com.infy.demo.validator;

import java.time.LocalDate;

public class TravelValidator {
	public static void validateTravel(LocalDate date) throws Exception{
		if(!isValidDate(date))
			throw new Exception("TravelValidator.INVALID_DATE");
		
	}
	
	public static boolean isValidDate(LocalDate date){
		LocalDate today = LocalDate.now();
		if(date.isBefore(today)) return false;
		if(date.isAfter(today.plusMonths(12))) return false;
		return true;
	}

}