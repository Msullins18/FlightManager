package com.infy.demo;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.demo.api.AirportAPI;
import com.infy.demo.exceptions.FlightNotFoundException;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.service.AirportService;

public class AirportAPITest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@InjectMocks
	private AirportAPI airportAPI;

	@Mock
	AirportService airportService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(airportAPI).build();
	}
	
	
	@Test
	public void addAirportValidTest() throws Exception {
		Airport airport = new Airport();
		airport.setAirportName("DFW");
		airport.setCity("Dallas");

		String json = new ObjectMapper().writeValueAsString(airport);

		Mockito.when(airportService.addAirport(airport)).thenReturn(1001);
		mockMvc.perform(MockMvcRequestBuilders.post("/AirportAPI/addAirport").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteAirportValidTest() throws Exception {
		Mockito.when(airportService.deleteAirport(1001)).thenReturn(1001);
		mockMvc.perform(MockMvcRequestBuilders.post("/AirportAPI/deleteAirport/{1001}",1001))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getAirportsValidTest() throws Exception {
		Airport airport = new Airport();
		airport.setAirportName("DFW");
		airport.setCity("Dallas");
		List<Airport> lis = new ArrayList<>();
		lis.add(airport);
		Mockito.when(airportService.getAirports()).thenReturn(lis);
		mockMvc.perform(MockMvcRequestBuilders.get("/AirportAPI/getAirports"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
