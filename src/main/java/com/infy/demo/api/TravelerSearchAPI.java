package com.infy.demo.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.model.SearchFlights;
import com.infy.demo.service.TravelerSearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface TravelerSearchAPI {
	@ApiOperation(value = "Fetch flights that match search data", response = TravelerSearchService.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetched all flights matched successfully"),
			@ApiResponse(code = 404, message = "Flights not found"), @ApiResponse(code = 400, message = "Invalid date")})
	public abstract ResponseEntity<List<Flight>> getFlights(@RequestBody SearchFlights searchFlights);
	
	@ApiOperation(value = "Fetch all airports", response = TravelerSearchService.class)
	@ApiResponse(code = 200, message = "Fetched all airports successfully")
	public abstract ResponseEntity<List<Airport>> getAllOrigins();
	
	@ApiOperation(value = "Fetch all destinations", response = TravelerSearchService.class)
	@ApiResponse(code = 200, message = "Fetched all destinations successfully")
	public abstract ResponseEntity<List<String>> getAllDestinations();

}
