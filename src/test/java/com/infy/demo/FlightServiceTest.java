package com.infy.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.infy.demo.dao.AirportDAO;
import com.infy.demo.dao.FlightDAO;
import com.infy.demo.model.Flight;
import com.infy.demo.service.FlightService;
import com.infy.demo.service.FlightServiceImpl;

@RunWith(SpringRunner.class)

public class FlightServiceTest {
	@Rule
	public BenchmarkRule benchmarkRun = new BenchmarkRule();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Mock
	private FlightDAO flightDAO;
	
	@Mock
	private AirportDAO airportDAO;
	
	@Mock 
	private FlightService flightService;
	@InjectMocks
	private FlightServiceImpl flightServiceImpl = new FlightServiceImpl();
	
	@Test
	@BenchmarkOptions(concurrency = 0, warmupRounds = 0, benchmarkRounds = 1)
	public void addFlight() throws Exception{
		Flight flight = new Flight();
		flight.setAirportId(1000);
		flight.setDateOfArrival(LocalDate.now().plusDays(14));
		flight.setDateOfDeparture(LocalDate.now().plusDays(14));
		flight.setDestination("New York");
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		Mockito.when(airportDAO.airportExists(1000)).thenReturn(true);
		Mockito.when(flightDAO.addFlight(flight)).thenReturn(1000);
		Integer id = flightService.addFlight(flight);
		assertEquals(1000, id);
	}
	
	@Test
	public void addFlightAirportDoesNotExist() throws Exception{
		expectedException.expect(Exception.class);
		Flight flight = new Flight();
		flight.setAirportId(1000);
		flight.setDateOfArrival(LocalDate.now().plusDays(14));
		flight.setDateOfDeparture(LocalDate.now().plusDays(14));
		flight.setDestination("New York");
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		Mockito.when(airportDAO.airportExists(1000)).thenReturn(false);
		Mockito.when(flightDAO.addFlight(flight)).thenReturn(1000);
		flightServiceImpl.addFlight(flight);
	}
	
	@Test
	public void deleteFlight() throws Exception{
		Mockito.when(flightDAO.deleteFlight(1000)).thenReturn(1000);
		Integer id = flightServiceImpl.deleteFlight(1000);
		assertEquals(1000, id);
	}
	
	@Test
	public void getFlights() throws Exception{
		Flight flight = new Flight();
		flight.setAirportId(1000);
		flight.setDateOfArrival(LocalDate.now().plusDays(14));
		flight.setDateOfDeparture(LocalDate.now().plusDays(14));
		flight.setDestination("New York");
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		
		List<Flight> list = new ArrayList<>();
		list.add(flight);
		Mockito.when(flightService.getFlights()).thenReturn(list);
		boolean isEmpty = flightServiceImpl.getFlights().isEmpty();
		assertEquals(false, isEmpty);
	}
	
	@Test
	public void getFlightsNoFlightExist() throws Exception{
		expectedException.expect(Exception.class);
		List<Flight> list = null;
		Mockito.when(flightService.getFlights()).thenReturn(list);
		flightServiceImpl.getFlights();
	}
}
