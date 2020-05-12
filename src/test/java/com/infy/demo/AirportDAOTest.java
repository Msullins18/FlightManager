package com.infy.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.infy.demo.dao.AirportDAO;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class AirportDAOTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Autowired
	AirportDAO	airportDAO;

	@Test
	public void addAirport(){
		Airport airport = new Airport();
		airport.setAirportName("DFW 23");
		airport.setCity("Dallas, Texas");
		List<Flight> flight = new ArrayList<>();
		airport.setFlights(flight);
		airportDAO.addAirport(airport);
		
	}
	@Test
	public void deleteAirportNoExistingAirportTest() throws Exception {
		airportDAO.deleteAirport(1000);
		Assert.assertTrue(true);
	}
	
	@Test
	public void addFlight(){
		Flight flight = new Flight();
		flight.setAirportId(1000);
		LocalDate arrival = LocalDate.of(2020, 05, 15);
		LocalDate departure = LocalDate.of(2020, 05, 13);
		flight.setDateOfArrival(arrival);
		flight.setDateOfDeparture(departure);
		flight.setDestination("Shangai China");
		flight.setFlightFare(2000);
		flight.setFlightSize(20);
		flight.setFlightTax(10);
		flight.setSeatsAvailable(20);
		airportDAO.addFlight(flight);
		
	}
	@Test
	public void airportExistsTest() throws Exception {
		boolean exists = airportDAO.airportExists(1000);
		
		assertEquals(true, exists);
	}
	
	@Test
	public void getAirportsTest() {
		List<Airport> list = new ArrayList<>();
		list = airportDAO.getAirports();
		assertNotNull(list);
	}
	
	@Test
	public void getFlightsTest() {
		List<Flight> list = new ArrayList<>();
		list = airportDAO.getFlights();
		assertNotNull(list);
	}

}
