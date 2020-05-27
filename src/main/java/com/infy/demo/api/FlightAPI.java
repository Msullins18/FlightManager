package com.infy.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.infy.demo.model.Flight;
import com.infy.demo.service.AirportService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface FlightAPI {
	@ApiOperation(value = "Add a new flight to the flight database", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Flight Added successfully"),
			@ApiResponse(code = 404, message = ""), @ApiResponse(code = 400, message = "")})
	public ResponseEntity<String> addFlight(@Valid @RequestBody Flight flight);
	@ApiOperation(value = "Delete flight from data base based on flight Id provided", response = String.class)
	    @ApiResponses(value = { 
	        @ApiResponse(code = 200, message = "Flight deleted successfully", response = String.class),
	        @ApiResponse(code = 403, message = ""),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Some unknown error occurred!") })
	public ResponseEntity<Integer> deleteFlight(@PathVariable("flightId") Integer flightId);
	
	 @ApiOperation(value = "Fetch all the flights from database", response = AirportService.class)
	 	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetched all flights successfully"),
	 			@ApiResponse(code = 404, message = "flights not found"), @ApiResponse(code = 400, message = "")})
	public ResponseEntity<List<Flight>> getFlights();
}
