package com.infy.demo;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.demo.api.TravelerSearchAPI;
import com.infy.demo.exceptions.FlightNotFoundException;
import com.infy.demo.exceptions.InvalidDateException;
import com.infy.demo.exceptions.NoFlightsAvailableException;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.model.SearchFlights;
import com.infy.demo.service.TravelerSearchService;

@RunWith(SpringRunner.class)
public class TravelerSearchAPITest {
	

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private MockMvc mockMvc;
	
	@InjectMocks
	private TravelerSearchAPI travelerSearchAPI;
	
	@Mock
	private TravelerSearchService travelerSearchService;
	
	private static List<Flight> flightList = new ArrayList<>();
	private static List<Airport> origins = new ArrayList<>();
	private static LocalDate date = LocalDate.now().plusDays(14);
	private static Integer airportId = 1000;
	private static String destination = "New York";
	private static Integer numberOfTickets = 2;
	private static List<String> destinations = new ArrayList<>();
	private static SearchFlights searchFlights = new SearchFlights();

	@BeforeClass
	public static void initializeAirport(){
		Flight flight = new Flight();
		flight.setAirportId(airportId);;
		flight.setDateOfArrival(date);;
		flight.setDateOfDeparture(date);
		flight.setDestination(destination);
		flightList.add(flight);
		Airport airport = new Airport();
		airport.setAirportId(1000);
		airport.setAirportName("DFW");
		airport.setCity("Dallas");
		origins.add(airport);
		destinations.add("New York");
		searchFlights.setAirportId(airportId);
		searchFlights.setDate(date);
		searchFlights.setDestination(destination);
		searchFlights.setNumberOfTickets(numberOfTickets);
	}
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(travelerSearchAPI).build();
	}

	@Test
	public void travelerSearchGetAllOrigins() throws Exception {
		Mockito.when(travelerSearchService.getAllOrigins()).thenReturn(origins);
		mockMvc.perform(MockMvcRequestBuilders.get("/Search/getAirports")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void travelerSearchGetAllDestinations() throws Exception {
		Mockito.when(travelerSearchService.getAllDestinations()).thenReturn(destinations);
		mockMvc.perform(MockMvcRequestBuilders.get("/Search/getDestinations")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
//	@Test
//	public void travelerSearchGetFlights() throws Exception {
//		String json = new ObjectMapper().writeValueAsString(searchFlights);		
//		Mockito.when(travelerSearchService.getFlights(searchFlights.getDate(), searchFlights.getAirportId(), searchFlights.getDestination(), searchFlights.getNumberOfTickets()))
//		.thenReturn(flightList);
//		mockMvc.perform(MockMvcRequestBuilders.post("/Search/getFlights").contentType(APPLICATION_JSON_UTF8).content(json))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}
	
//	@Test
//	public void travelerSearchGetFlightsFlightNotFound() throws Exception {
//		String json = new ObjectMapper().writeValueAsString(searchFlights);
//		Mockito.when(travelerSearchService.getFlights(date, airportId, destination, numberOfTickets))
//		.thenThrow(NoFlightsAvailableException.class);
//		mockMvc.perform(MockMvcRequestBuilders.post("/Search/getFlights").contentType(APPLICATION_JSON_UTF8).content(json))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//	}

	
	
	

}
