package com.infy.demo;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.infy.demo.validator.TravelValidator;
import junit.framework.Assert;

public class TravelValidatorTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public  void validateDatevalid() throws Exception{
		LocalDate today = LocalDate.now();
		LocalDate testDate = today.plusMonths(2);
		Assert.assertTrue(TravelValidator.isValidDate(testDate));
		
	}
	
	@Test
	public  void validateDateInvalid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("TravelValidator.INVALID_DATE");
		LocalDate today = LocalDate.now();
		LocalDate testDate = today.plusMonths(13);
		TravelValidator.validateTravel(testDate);;
		
	}
	
	

}
