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

import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping(value ="Search")
@Slf4j
public class TravelerSearchAPI {
	
	@Autowired
	private TravelerSearchService travelerSearchService;

	@PostMapping(value="/getFlights")
	public ResponseEntity<List<Flight>> getFlights(@RequestBody SearchFlights searchFlights) {
//		log.info("Search flights from "+searchFlights.getAirportId() +" to " +searchFlights.getDestination());
		List<Flight> flightList = travelerSearchService.getFlights(searchFlights.getDate(), searchFlights.getAirportId(),searchFlights.getDestination(), searchFlights.getNumberOfTickets());
		ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
//		log.info("The number of flights avaiable is " + flightList.size()); 
		return response;
	}

	@GetMapping(value="/getAirports")
	public ResponseEntity<List<Airport>> getAllOrigins() {
		List<Airport> origins = null;
		origins = travelerSearchService.getAllOrigins();
		ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(origins, HttpStatus.OK);
//		log.info("The number of airports is " + origins.size());
		return response;
	}

	@GetMapping(value="/getDestinations")
	public ResponseEntity<List<String>> getAllDestinations() {
		List<String> destinations = null;
		destinations = travelerSearchService.getAllDestinations();
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(destinations, HttpStatus.OK);
//		log.info("The number of airports is " + destinations.size());
		return response;
	}
}
