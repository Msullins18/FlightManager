package com.infy.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.infy.demo.dao.TravelerSearchDAO;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.exceptions.NoFlightsAvailableException;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.service.TravelerSearchService;
import com.infy.demo.service.TravelerSearchServiceImpl;


public class TravelerSearchServiceTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private TravelerSearchDAO travelerSearchDAO;
	
	@InjectMocks
	private TravelerSearchService travelerSearchService = new TravelerSearchServiceImpl();
	
	 @Before
	    public void setup() {
	    	MockitoAnnotations.initMocks(this);
	 }
	
	@Test
	public void testGetFlights() throws Exception{
		LocalDate date = LocalDate.now().plusDays(14);
		Integer airport = 1000;
		String destination = "New York";
		Integer numberOfTickets = 2;
		FlightEntity flight = new FlightEntity();
		flight.setAirportId(airport);
		flight.setDateOfArrival(date);
		flight.setDateOfDeparture(date);
		flight.setDestination(destination);
		flight.setFlightFare(250d);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20d);
		flight.setSeatsAvailable(100);
		List<FlightEntity> flightList = new ArrayList<>();
		flightList.add(flight);
		Mockito.when(travelerSearchDAO.getFlights(date, airport, destination, numberOfTickets)).thenReturn(flightList);
		List<Flight> result = travelerSearchService.getFlights(date, airport, destination, numberOfTickets);
		Assert.assertNotNull(result);	
	}
	
	@Test
	public void testGetFlightsNoFlights() throws Exception{
		expectedException.expect(NoFlightsAvailableException.class);
		LocalDate date = LocalDate.now().plusDays(14);
		Integer airport = 1001;
		String destination = "New York";
		Integer numberOfTickets = 2;

		List<FlightEntity> flightList = new ArrayList<>();
		Mockito.when(travelerSearchDAO.getFlights(date, airport, destination, numberOfTickets)).thenReturn(flightList);
		travelerSearchService.getFlights(date, airport, destination, numberOfTickets);
		
	}
	
	@Test
	public void testGetAllOrigins() throws Exception{
		List<AirportEntity> origins = new ArrayList<>();
		AirportEntity airport = new AirportEntity();
		airport.setAirportId(1000);
		airport.setAirportName("DFW");
		airport.setCity("Dallas");
		origins.add(airport);

		Mockito.when(travelerSearchDAO.getAllOrigins()).thenReturn(origins);
		List<Airport> originList = travelerSearchService.getAllOrigins();
		Assert.assertNotNull(originList);
	}
	
	@Test
	public void testGetAllDestinations() throws Exception{
		List<String> destination = new ArrayList<>();
		destination.add("Dallas");
		destination.add("Houston");
		destination.add("New York");

		Mockito.when(travelerSearchDAO.getAllDestinations()).thenReturn(destination);
		List<String> destinationList = travelerSearchService.getAllDestinations();
		Assert.assertNotNull(destinationList);
	}
	
	@Test(expected=NoFlightsAvailableException.class)
	public void testGetFlightsNoavailable() throws Exception{
		LocalDate date = LocalDate.now().plusDays(14);
		Integer airport = 1000;
		String destination = "New York";
		Integer numberOfTickets = 2;
		List<FlightEntity> flightList = new ArrayList<>();
		Mockito.when(travelerSearchDAO.getFlights(date, airport, destination, numberOfTickets)).thenReturn(flightList);
		travelerSearchService.getFlights(date, airport, destination, numberOfTickets);
		
	}

}