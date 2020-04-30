package com.infy.demo.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.demo.model.Flight;
import com.infy.demo.service.AirportService;

@CrossOrigin
@RestController
@RequestMapping("AirportAPI")
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
	
	@PostMapping(value = "deleteFlight/{airportId}")
	public ResponseEntity<String> deleteFlight(@PathVariable("airportId") Integer airId, @RequestBody Integer flightId) throws Exception{
		
		try
		{
			airportService.deleteFlight(airId, flightId);
			
			String message = environment.getProperty("AirportAPI.FLIGHT_DELETED_FROM_AIRPORT" + airId);
			
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
}
