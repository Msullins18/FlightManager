package com.infy.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.demo.dao.TravellerSearchDAO;
import com.infy.demo.model.Flight;
import com.infy.demo.service.TravellerSearchService;
import com.infy.demo.service.TravellerSearchServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravellerSearchServiceTest {
	@Mock
	private TravellerSearchDAO travellerSearchDAO;
	
	@InjectMocks
	private TravellerSearchService travellerSearchService = new TravellerSearchServiceImpl();
	
	@Test
	public void testGetFlights() throws Exception{
		LocalDate date = LocalDate.now().plusDays(14);
		Integer airport = 1000;
		String destination = "New York";
		Integer numberOfTickets = 2;
		Flight flight = new Flight();
		flight.setAirportId(airport);;
		flight.setDateOfArrival(date);;
		flight.setDateOfDeparture(date);
		flight.setDestination(destination);
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight);
		Mockito.when(travellerSearchDAO.getFlights(date, airport, destination, numberOfTickets)).thenReturn(flightList);
		List<Flight> result = travellerSearchService.getFlights(date, airport, destination, numberOfTickets);
		Assert.assertNotNull(result);
		
	}
	
	@Test
	public void testGetAllOrigins() throws Exception{
		List<String> origins = new ArrayList<>();
		origins.add("Dallas");
		origins.add("Houston");
		origins.add("New York");

		Mockito.when(travellerSearchDAO.getAllOrigins()).thenReturn(origins);
		List<String> originList = travellerSearchService.getAllOrigins();
		Assert.assertNotNull(originList);
	}
	
	@Test
	public void testGetAllDestinations() throws Exception{
		List<String> destination = new ArrayList<>();
		destination.add("Dallas");
		destination.add("Houston");
		destination.add("New York");

		Mockito.when(travellerSearchDAO.getAllDestinations()).thenReturn(destination);
		List<String> destinationList = travellerSearchService.getAllDestinations();
		Assert.assertNotNull(destinationList);
	}
	

}
