package com.infy.demo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.demo.dao.AdminDAO;
import com.infy.demo.model.Airport;
import com.infy.demo.service.AdminService;
import com.infy.demo.service.AdminServiceImpl;

@RunWith(SpringRunner.class)
public class AdminAirportTests {
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	AdminDAO adminDAO;
   	
	@InjectMocks
	AdminService adminService = new AdminServiceImpl();
	
	@Test
	public void addAirportTest() throws Exception 
	{
		Airport airport = new Airport();
		
		airport.setAirportName("DFW");
		airport.setCity("Dallas - Fort Worth");
		
		int id = adminService.addAirport(airport);
		
		assertNotNull(id);
	}
	@Test
	public void deleteAirportTest() throws Exception 
	{
		Airport airport = new Airport();
		airport.setAirportId(1000);
		airport.setAirportName("DFW");
		airport.setCity("Dallas - Fort Worth");
		
		when(adminDAO.deleteAirport(1000)).thenReturn(1000);
		int id = adminService.deleteAirport(1000);
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
		
		when(adminDAO.deleteAirport(1000)).thenReturn(null);
		int id = adminService.deleteAirport(1000);
		assertNotNull(id);
	}
	@Test
	public void getAirportsTest() throws Exception 
	{
		List<Airport> airports = adminService.getAirports();
		assertNotNull(airports);
	}
	@Test
	public void getAirportsDoesNotExistTest() throws Exception 
	{
		expectedException.expect(Exception.class);
		when(adminDAO.getAirports()).thenReturn(null);
		List<Airport> airports = adminService.getAirports();
		assertNotNull(airports);
	}
}
