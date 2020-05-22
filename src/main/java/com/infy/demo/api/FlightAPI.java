package com.infy.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infy.demo.model.Flight;
import com.infy.demo.service.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("FlightAPI")
@Api(value="Flight Management System", description="Managing admin operations such as adding and deleting flights")
public class FlightAPI {

	@Autowired
	private FlightService flightService;

	@ApiOperation(value = "Add a flight")
	@PostMapping(value = "flights")
	public ResponseEntity<String> addFlight(@Valid @RequestBody Flight flight) {
			Integer id = flightService.addFlight(flight);
			String message = "The following flight has been successfully added with Id:" + id;
			return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a flight")
	@DeleteMapping(value = "flights/{flightId}")
	public ResponseEntity<Integer> deleteFlight(@PathVariable("flightId") Integer flightId) {
			Integer result = flightService.deleteFlight(flightId);
			ResponseEntity<Integer> re = new ResponseEntity<Integer>(result, HttpStatus.OK);
			return re;
	}
	
	@ApiOperation(value = "Get list of all available flights", response = List.class)
	@GetMapping(value = "flights")
	public ResponseEntity<List<Flight>> getFlights() {
			List<Flight> list = null;
			list = flightService.getFlights();
			ResponseEntity<List<Flight>> response = new ResponseEntity<List<Flight>>(list, HttpStatus.OK);
			return response;
	}
}
