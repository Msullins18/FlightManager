package com.infy.demo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.infy.demo.dao.TravellerDAO;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Traveller;
import com.infy.demo.service.TravellerService;
import com.infy.demo.service.TravellerServiceImpl;
import com.infy.demo.utility.HashingUtility;

@RunWith(SpringRunner.class)
public class TravelerLoginTests {
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	TravellerDAO travellerDAO;
	@InjectMocks
	TravellerService travellerService = new TravellerServiceImpl();
	
	@Test
	public void authenticateTravellerLoginValidCredentials() throws Exception 
	{
		Traveller traveller = new Traveller();
		Traveller returned = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setPassword("Me@123");
		
		returned.setEmailId("marcus@marcus.com");
		returned.setPassword(HashingUtility.getHash("Me@123"));
		
		when(travellerDAO.getPasswordOfTraveller("marcus@marcus.com")).thenReturn(HashingUtility.getHash("Me@123"));
		when(travellerDAO.getTravellerByEmailId("marcus@marcus.com")).thenReturn(returned);
		
		Traveller travellerFromDAO = travellerService.loginTraveller(traveller);
		assertEquals("marcus@marcus.com", travellerFromDAO.getEmailId());
	}
	@Test
	public void authenticateTravellerLoginInvalidCredentials() throws Exception 
	{
		expectedException.expect(InvalidCredentialsException.class);
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setPassword("Me123");
		
		when(travellerDAO.getPasswordOfTraveller("marcus@marcus.com")).thenReturn(HashingUtility.getHash("Me@123"));
		
		Traveller travellerFromDAO = travellerService.loginTraveller(traveller);
	}
	@Test
	public void authenticateTravellerLoginUserNotFound() throws Exception 
	{
		expectedException.expect(UserNotFoundException.class);
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setPassword("Me123");
		
		when(travellerDAO.getPasswordOfTraveller("marcus@marcus.com")).thenReturn(null);
		
		Traveller travellerFromDAO = travellerService.loginTraveller(traveller);
	}
}