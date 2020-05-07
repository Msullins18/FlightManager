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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.demo.model.Flight;
import com.infy.demo.service.AirportService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("AirportAPI")
@Slf4j
public class AirportAPI {
	
	@Autowired
	private AirportService airportService;
	
	@Autowired
	private Environment environment;

	@PostMapping(value = "addFlight")
	public ResponseEntity<String> addFlight(@RequestBody Flight flight) throws Exception {
		try
		{
			Integer id = airportService.addFlight(flight);
			
			String message = "The following flight has been successfully added with Id:" + id;
			
			return new ResponseEntity<String>(message, HttpStatus.OK);
			
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "deleteFlight/{flightId}")
	public ResponseEntity<Integer> deleteFlight(@PathVariable("flightId") Integer flightId) throws Exception{
		
		try
		{
			Integer result = airportService.deleteFlight(flightId);
			log.info(environment.getProperty("DealsForTodayAPI.DELETE_SUCCESS") + result);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value = "getFlights")
	public ResponseEntity<List<Flight>> getDealsForToday() throws Exception {
		List<Flight> list = null;
		try {
			list = airportService.getFlights();
			ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(list, HttpStatus.OK);
			return response;
		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}

	}
}
