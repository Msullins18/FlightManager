package com.infy.demo;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.demo.api.TravelerSearchAPI;
import com.infy.demo.dao.TravelerSearchDAO;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.service.TravelerSearchService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TravelerSearchAPI.class)
public class TravelerSearchAPITest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TravelerSearchService travelerSearchService;
	
	private TravelerSearchDAO travelerSearchDAO = null;
	private List<Flight> flightList = null;
	private List<Airport> origins = null;
	private LocalDate date = LocalDate.now().plusDays(14);
	private Integer airportId = 1000;
	private String destination = "New York";
	private Integer numberOfTickets = 2;
	private List<String> destinations = null;
	
	@Before
	public void initializeAirport(){
		Flight flight = new Flight();
		flight.setAirportId(airportId);;
		flight.setDateOfArrival(date);;
		flight.setDateOfDeparture(date);
		flight.setDestination(destination);
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		flightList = new ArrayList<>();
		flightList.add(flight);
		
		origins = new ArrayList<>();
		Airport airport = new Airport();
		airport.setAirportId(1000);
		airport.setName("DFW");
		airport.setCity("Dallas");
		origins.add(airport);
		
		destinations = new ArrayList<>();
		destinations.add("New York");
	}
	
	
	@Test
	public void testGetFlights() throws Exception {
		Mockito.when(travelerSearchService.getFlights(date, airportId, destination, numberOfTickets)).thenReturn(flightList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getFlights").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, flightList);
		byte[] data = out.toByteArray();
		Assert.assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void testGetAllOrigins() throws Exception{
		Mockito.when(travelerSearchService.getAllOrigins()).thenReturn(origins);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAirports").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, origins);
		byte[] data = out.toByteArray();
		Assert.assertNotNull(response.getContentAsString());
	}
	
	@Test
	public void testgetAllDestinations() throws Exception{
		Mockito.when(travelerSearchService.getAllDestinations()).thenReturn(destinations);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getDestinations").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, destinations);
		byte[] data = out.toByteArray();
		Assert.assertNotNull(response.getContentAsString());
	}

}
