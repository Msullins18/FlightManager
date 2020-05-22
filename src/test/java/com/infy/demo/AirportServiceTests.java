package com.infy.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import com.infy.demo.model.Airport;
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
	public void addAirportTest() throws Exception 
	{
		Airport airport = new Airport();
		
		airport.setAirportName("DFW");
		airport.setCity("Dallas - Fort Worth");
		
		int id = airportServiceImpl.addAirport(airport);
		
		assertNotNull(id);
	}
	@Test
	public void deleteAirportTest() throws Exception 
	{
		Airport airport = new Airport();
		airport.setAirportId(1000);
		airport.setAirportName("DFW");
		airport.setCity("Dallas - Fort Worth");
		
		when(airportDAO.deleteAirport(1000)).thenReturn(1000);
		int id = airportServiceImpl.deleteAirport(1000);
		assertNotNull(id);
	}
	@Test
	public void deleteAirportDoesNotExistTest() throws Exception 
	{
		expectedException.expect(Exception.class);
		Airport airport = new Airport();
		airport.setAirportId(1000);
		airport.setAirportName("DFW");
		airport.setCity("Dallas - Fort Worth");
		
		when(airportDAO.deleteAirport(1000)).thenReturn(null);
		int id = airportServiceImpl.deleteAirport(1000);
		assertNotNull(id);
	}
	@Test
	public void getAirportsTest() throws Exception 
	{
		List<Airport> airports = airportServiceImpl.getAirports();
		assertNotNull(airports);
	}
	@Test
	public void getAirportsDoesNotExistTest() throws Exception 
	{
		expectedException.expect(Exception.class);
		when(airportDAO.getAirports()).thenReturn(null);
		List<Airport> airports = airportServiceImpl.getAirports();
		assertNotNull(airports);
	}
}
