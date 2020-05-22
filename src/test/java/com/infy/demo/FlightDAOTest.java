package com.infy.demo;

import java.time.LocalDate;

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
import com.infy.demo.dao.FlightDAO;
import com.infy.demo.model.Flight;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class FlightDAOTest {
	@Rule
	public BenchmarkRule benchmarkRun = new BenchmarkRule();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Autowired
	FlightDAO flightDAO;
	
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
		flightDAO.addFlight(flight);
		
	}
	@Test
	public void deleteFlightNoExistingAirportTest() throws Exception {
		flightDAO.deleteFlight(2000);
		Assert.assertTrue(true);
	}
}
