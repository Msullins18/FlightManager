package com.infy.demo.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		try {
			Integer id = airportService.addFlight(flight);

			String message = "The following flight has been successfully added with Id:" + id;

			return new ResponseEntity<String>(message, HttpStatus.OK);

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(value = "deleteFlight/{flightId}")
	public ResponseEntity<Integer> deleteFlight(@PathVariable("flightId") Integer flightId) throws Exception {

		try {
			Integer result = airportService.deleteFlight(flightId);
			log.info("Flight successfully deleted with id: " + result);
			ResponseEntity<Integer> re = new ResponseEntity<Integer>(result, HttpStatus.OK);
			return re;
		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(value = "addAirport")
	public ResponseEntity<String> addAirport(@RequestBody Airport airport) throws Exception {
		try {
			Integer id = airportService.addAirport(airport);
			String message = "The following Airport has been successfully added with Airport Id: " + id;
			log.info(message);
			ResponseEntity<Integer> re = new ResponseEntity<Integer>(id, HttpStatus.OK);
			return new ResponseEntity<String>(message, HttpStatus.OK);

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(value = "deleteAirport/{airportId}")
	public ResponseEntity<Integer> deleteAirport(@PathVariable("airportId") Integer airportId) throws Exception {

		try {
			Integer result = airportService.deleteAirport(airportId);
			log.info("The following Airport has been successfully deleted with Airport Id:" + result);
			ResponseEntity<Integer> re = new ResponseEntity<Integer>(result, HttpStatus.OK);
			return re;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping(value = "getAirports")
	public ResponseEntity<List<Airport>> getAirports() throws Exception {
		List<Airport> list = null;
		try {
			list = airportService.getAirports();
			ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(list, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(value = "getFlights")
	public ResponseEntity<List<Flight>> getFlights() throws Exception {
		try {
			List<Flight> list = null;
			list = airportService.getFlights();
			ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(list, HttpStatus.OK);
			return response;
		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleException(FlightNotFoundException e) {
		// throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoFlightsAvailableException.class)
	public ResponseEntity<Object> handleException(NoFlightsAvailableException e) {
		// throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
