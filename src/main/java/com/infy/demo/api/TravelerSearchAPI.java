package com.infy.demo.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.model.SearchFlights;
import com.infy.demo.service.TravelerSearchService;
@CrossOrigin
@RestController
@RequestMapping(value ="Search")
public class TravelerSearchAPI {
	@Autowired
	public Environment environment;
	
	@Autowired
	private TravelerSearchService travelerSearchService;
	
	static Logger logger = LogManager.getLogger(TravelerSearchAPI.class.getName());

	@PostMapping(value="/getFlights")
	public ResponseEntity<List<Flight>> getFlights(@RequestBody SearchFlights searchFlights) throws Exception{
		try{
			logger.info("Search flights from "+searchFlights.getAirportId() +" to " +searchFlights.getDestination());
			List<Flight> flightList = travelerSearchService.getFlights(searchFlights.getDate(), searchFlights.getAirportId(),searchFlights.getDestination(), searchFlights.getNumberOfTickets());
			ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
			logger.info("The number of flights avaiable is " + flightList.size()); 
			return response;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	environment.getProperty(e.getMessage()), e);
		}
	}

	@GetMapping(value="/getAirports")
	public ResponseEntity<List<Airport>> getAllOrigins() throws Exception{
		List<Airport> origins = null;
		try{
			origins = travelerSearchService.getAllOrigins();
			ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(origins, HttpStatus.OK);
			logger.info("The number of airports is " + origins.size());
			return response;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	environment.getProperty(e.getMessage()), e);
		}
	}

	@GetMapping(value="/getDestinations")
	public ResponseEntity<List<String>> getAllDestinations() throws Exception{
		List<String> destinations = null;
		try{
			destinations = travelerSearchService.getAllDestinations();
			ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(destinations, HttpStatus.OK);
			logger.info("The number of airports is " + destinations.size());
			return response;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	environment.getProperty(e.getMessage()), e);
		}
	}
	

}