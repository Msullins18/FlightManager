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
import com.infy.demo.api.FlightAPIImpl;
import com.infy.demo.model.Flight;
import com.infy.demo.service.FlightService;

public class FlightAPITest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@InjectMocks
	private FlightAPIImpl flightAPI;

	@Mock
	FlightService flightService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(flightAPI).build();
	}
	
	@Test
	public void addFlightTest() throws Exception {
		Flight flight = new Flight();
		flight.setAirportId(1000);
		flight.setDestination("New York");
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		flight.setFlightType("Domestic");

		String json = new ObjectMapper().writeValueAsString(flight);
		Mockito.when(flightService.addFlight(flight)).thenReturn(1001);
		mockMvc.perform(MockMvcRequestBuilders.post("/flightAPI/flights").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void addFlightAirportDoesNotExistTest() throws Exception {
		Flight flight = new Flight();
		flight.setAirportId(1000);
		flight.setDestination("New York");
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		flight.setFlightType("Domestic");

		String json = new ObjectMapper().writeValueAsString(flight);
		Mockito.when(flightService.addFlight(flight)).thenThrow(Exception.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/FlightAPI/flights").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void deleteFlightTest() throws Exception {
		Mockito.when(flightService.deleteFlight(1000)).thenReturn(1000);
		mockMvc.perform(MockMvcRequestBuilders.delete("/FlightAPI/flights/{1001}",1001))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getFlightsValidTest() throws Exception {
		Flight flight = new Flight();
		flight.setAirportId(1000);
		flight.setDestination("New York");
		flight.setFlightFare(250);
		flight.setFlightId(2000);
		flight.setFlightSize(999);
		flight.setFlightTax(20);
		flight.setSeatsAvailable(100);
		flight.setFlightType("Domestic");
		List<Flight> lis = new ArrayList<>();
		lis.add(flight);
		Mockito.when(flightService.getFlights()).thenReturn(lis);
		mockMvc.perform(MockMvcRequestBuilders.get("/FlightAPI/flights"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
