package com.infy.demo;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.infy.demo.exceptions.InvalidDateException;
import com.infy.demo.validator.TravelValidator;

public class TravelValidatorTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public  void validateDatevalid() throws Exception{
		LocalDate today = LocalDate.now();
		LocalDate testDate = today.plusMonths(2);
		TravelValidator.validateTravel(testDate);
		Assert.assertTrue(true);
		
	}
	
	@Test
	public  void validateDateInvalid() throws Exception{
		expectedException.expect(InvalidDateException.class);
		LocalDate today = LocalDate.now();
		LocalDate testDate = today.plusMonths(13);
		TravelValidator.validateTravel(testDate);;
		
	}
	
	

}
