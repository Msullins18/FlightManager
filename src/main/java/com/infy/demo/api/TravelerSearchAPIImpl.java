package com.infy.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.model.SearchFlights;
import com.infy.demo.service.TravelerSearchService;
import io.swagger.annotations.Api;




@CrossOrigin
@RestController
@RequestMapping(value ="Search")
@Api(value = "TravelerSearchAPI, REST APIs that deal with searching flights as a Traveler")
public class TravelerSearchAPIImpl implements TravelerSearchAPI {
	
	@Autowired
	private TravelerSearchService travelerSearchService;
	
	/* 
	 * @requestBody SearchFlights This is the searchFlihgt object that contains search data from a Traveler
	 * @return a list of flights that matches search data
	 * @throw Exception
	 */
	@PostMapping(consumes="application/json")
	public ResponseEntity<List<Flight>> getFlights(@RequestBody SearchFlights searchFlights) {
		List<Flight> flightList = travelerSearchService.getFlights(searchFlights.getDate(), searchFlights.getAirportId(),searchFlights.getDestination(), searchFlights.getNumberOfTickets());
		ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
		return response;
	}

	/*
	 * @return all airports from database
	 */
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Airport>> getAllOrigins() {
		List<Airport> origins = null;
		origins = travelerSearchService.getAllOrigins();
		ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(origins, HttpStatus.OK);
		return response;
	}

	/*
	 * @return all destination cities that flights have
	 */
	@GetMapping(value="Destinations", produces="application/json")
	public ResponseEntity<List<String>> getAllDestinations() {
		List<String> destinations = null;
		destinations = travelerSearchService.getAllDestinations();
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(destinations, HttpStatus.OK);
		return response;
	}
}
