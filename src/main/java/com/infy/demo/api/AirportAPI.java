package com.infy.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.demo.model.Airport;
import com.infy.demo.exceptions.AirportNotFoundException;
import com.infy.demo.exceptions.FlightNotFoundException;
import com.infy.demo.exceptions.NoAirportsAvailableException;
import com.infy.demo.exceptions.NoFlightsAvailableException;
import com.infy.demo.model.Flight;
import com.infy.demo.service.AirportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("AirportAPI")
@Slf4j
@Api(value="Airport Management System", description="Managing admin operations such as adding and deleting airports")
public class AirportAPI {

	@Autowired
	private AirportService airportService;

	@Autowired
	private Environment environment;


    @ApiOperation(value = "Add an Airport")
	@PostMapping(value = "Airport")
	public ResponseEntity<String> addAirport(@Valid @RequestBody Airport airport) throws Exception {
			Integer id = airportService.addAirport(airport);
			String message = "The following Airport has been successfully added with Airport Id: " + id;
			log.info(message);
			ResponseEntity<Integer> re = new ResponseEntity<Integer>(id, HttpStatus.OK);
			return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	

    @ApiOperation(value = "Delete an airport")
	@DeleteMapping(value = "Airport/{airportId}")
	public ResponseEntity<Integer> deleteAirport(@PathVariable("airportId") Integer airportId) throws Exception {
			Integer result = airportService.deleteAirport(airportId);
			log.info("The following Airport has been successfully deleted with Airport Id:" + result);
			ResponseEntity<Integer> re = new ResponseEntity<Integer>(result, HttpStatus.OK);
			return re;

	}
	
	@ApiOperation(value = "Get list of all available airports", response = List.class)
	@GetMapping(value = "Airport")
	public ResponseEntity<List<Airport>> getAirports() throws Exception {
			List<Airport> list = null;
			list = airportService.getAirports();
			log.info("Fetching list of all the airports available in the data base");
			ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(list, HttpStatus.OK);
			return response;
	}
	
	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<Object> handleException(AirportNotFoundException e) {
		// throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoAirportsAvailableException.class)
	public ResponseEntity<Object> handleException(NoAirportsAvailableException e) {
		// throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
