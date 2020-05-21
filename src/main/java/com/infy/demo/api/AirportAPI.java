package com.infy.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.demo.model.Airport;
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

	/**
	 * @param flight 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "addFlight")
	public ResponseEntity<String> addFlight(@RequestBody Flight flight) {
			Integer id = airportService.addFlight(flight);
			String message = "The following flight has been successfully added with Id:" + id;
			return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping(value = "deleteFlight/{flightId}")
	public ResponseEntity<Integer> deleteFlight(@PathVariable("flightId") Integer flightId) {
		Integer result = airportService.deleteFlight(flightId);
		log.info("Flight successfully deleted with id: " + result);
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(result, HttpStatus.OK);
		return re;
	}

	@PostMapping(value = "addAirport")
	public ResponseEntity<String> addAirport(@RequestBody Airport airport) {
		Integer id = airportService.addAirport(airport);
		String message = "The following Airport has been successfully added with Airport Id: " + id;
		log.info(message);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping(value = "deleteAirport/{airportId}")
	public ResponseEntity<Integer> deleteAirport(@PathVariable("airportId") Integer airportId) {
		Integer result = airportService.deleteAirport(airportId);
		log.info("The following Airport has been successfully deleted with Airport Id:" + result);
		ResponseEntity<Integer> re = new ResponseEntity<Integer>(result, HttpStatus.OK);
		return re;
	}

	@GetMapping(value = "getAirports")
	public ResponseEntity<List<Airport>> getAirports() {
		List<Airport> list = null;
		list = airportService.getAirports();
		ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(list, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "getFlights")
	public ResponseEntity<List<Flight>> getFlights() {
		List<Flight> list = null;
		list = airportService.getFlights();
		ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(list, HttpStatus.OK);
		return response;
	}
}
