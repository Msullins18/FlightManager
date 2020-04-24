package com.infy.demo.api;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.demo.model.Flight;
import com.infy.demo.service.TravelerSearchService;

@RestController
@RequestMapping(value ="searchAPI")
public class TravelerSearchAPI {
	@Autowired
	public Environment environment;
	
	@Autowired
	private TravelerSearchService travelerSearchService;
	
	@GetMapping(value="/getFlights")
	public ResponseEntity<List<Flight>> getFlights(@PathVariable LocalDate date, Integer airportId, String destination, Integer numberOfTickets) throws Exception{
		try{
			List<Flight> flightList = travelerSearchService.getFlights(date, airportId, destination, numberOfTickets);
			ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
			return response;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	environment.getProperty(e.getMessage()), e);
		}
	}
	
	
	@GetMapping(value="/getAirports")
	public ResponseEntity<List<String>> getAllOrigins() throws Exception{
		List<String> origins = null;
		try{
			origins = travelerSearchService.getAllOrigins();
			ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(origins, HttpStatus.OK);
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
			return response;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	environment.getProperty(e.getMessage()), e);
		}
	}
	

}
