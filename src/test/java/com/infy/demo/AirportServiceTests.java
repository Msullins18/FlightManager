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
import com.infy.demo.model.Flight;
import com.infy.demo.service.AirportServiceImpl;
@RunWith(SpringRunner.class)
public class AirportServiceTests {
	
	@Rule
	public BenchmarkRule benchmarkRun = new BenchmarkRule();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Mock
	private AirportDAO airportDAO;
	
	@InjectMocks
	private AirportServiceImpl airportServiceImpl = new AirportServiceImpl();
	
	
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
		Mockito.when(airportDAO.addFlight(flight)).thenReturn(1000);
		Integer id = airportServiceImpl.addFlight(flight);
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
		Mockito.when(airportDAO.addFlight(flight)).thenReturn(1000);
		airportServiceImpl.addFlight(flight);
	}
	
	@Test
	public void deleteFlight() throws Exception{
		Mockito.when(airportDAO.deleteFlight(1000)).thenReturn(1000);
		Integer id = airportServiceImpl.deleteFlight(1000);
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
		Mockito.when(airportDAO.getFlights()).thenReturn(list);
		boolean isEmpty = airportServiceImpl.getFlights().isEmpty();
		assertEquals(false, isEmpty);
	}
	
	@Test
	public void getFlightsNoFlightExist() throws Exception{
		expectedException.expect(Exception.class);
		List<Flight> list = null;
		Mockito.when(airportDAO.getFlights()).thenReturn(list);
		airportServiceImpl.getFlights();
	}
}
