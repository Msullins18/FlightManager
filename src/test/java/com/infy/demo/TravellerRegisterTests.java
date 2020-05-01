package com.infy.demo;
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
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.model.Traveller;
import com.infy.demo.service.TravellerService;
import com.infy.demo.service.TravellerServiceImpl;

@RunWith(SpringRunner.class)
public class TravellerRegisterTests {

	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	TravellerDAO travellerDAO;
	@InjectMocks
	TravellerService travellerService = new TravellerServiceImpl();
	
	@Test
	public void registerTravellerValidInputs() throws Exception 
	{
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setPassword("Me@123");
		traveller.setFirstName("marcus");
		traveller.setLastName("marcus");
		traveller.setPhoneNumber("1112225545");
		
		when(travellerDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(true);
		when(travellerDAO.registerTraveller(traveller)).thenReturn("marcus@marcus.com");
		String registered = travellerService.registerTraveller(traveller);
		assertEquals("marcus@marcus.com", registered);
	}
	
	@Test
	public void registerTravellerInvalidInputs() throws Exception 
	{
		expectedException.expect(Exception.class);
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcusmarcus.com");
		traveller.setPassword("Me@123");
		traveller.setFirstName("marcus");
		traveller.setLastName("marcus");
		traveller.setPhoneNumber("1112225545");
		
//		when(travellerDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(true);
//		when(travellerDAO.registertraveller(traveller)).thenReturn("marcus@marcus.com");
		String registered = travellerService.registerTraveller(traveller);
		assertEquals("marcus@marcus.com", registered);
	}
	
	@Test
	public void registerTravellerTakenEmail() throws Exception 
	{
		expectedException.expect(EmailUnavailableException.class);
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setPassword("Me@123");
		traveller.setFirstName("marcus");
		traveller.setLastName("marcus");
		traveller.setPhoneNumber("1112225545");
		
		when(travellerDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(false);
		String registered = travellerService.registerTraveller(traveller);
	}
}
