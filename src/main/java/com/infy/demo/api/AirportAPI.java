package com.infy.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.infy.demo.model.Airport;
import com.infy.demo.service.AirportService;
import com.infy.demo.service.TravelerSearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface AirportAPI {
	 	@ApiOperation(value = "Add a new airport to the airport database", response = String.class)
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Airport Added successfully"),
				@ApiResponse(code = 404, message = ""), @ApiResponse(code = 400, message = "")})
		public abstract ResponseEntity<String> addAirport(@Valid @RequestBody Airport airport);
	 	
	 	  @ApiOperation(value = "Delete airport from data base based on airport Id provided", response = String.class)
	 	    @ApiResponses(value = { 
	 	        @ApiResponse(code = 200, message = "Airport deleted successfully", response = String.class),
	 	        @ApiResponse(code = 403, message = ""),
	 	        @ApiResponse(code = 400, message = "Bad Request"),
	 	        @ApiResponse(code = 500, message = "Some unknown error occurred!") })
	 	public ResponseEntity<Integer> deleteAirport(@PathVariable("airportId") Integer airportId);
	 	
	 	 @ApiOperation(value = "Fetch all the airports from database", response = AirportService.class)
	 	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetched all airports successfully"),
	 			@ApiResponse(code = 404, message = "airports not found"), @ApiResponse(code = 400, message = "")})
	 	public ResponseEntity<List<Airport>> getAirports();
}
