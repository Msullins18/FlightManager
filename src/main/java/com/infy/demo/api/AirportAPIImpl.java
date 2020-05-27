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
import com.infy.demo.model.Airport;
import com.infy.demo.service.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("AirportAPI")
@Api(value="Airport Management System", description="Managing admin operations such as adding and deleting airports")
public class AirportAPIImpl implements AirportAPI {

	@Autowired
	private AirportService airportService;

	@Override
	@PostMapping(value = "Airport",consumes ="application/json")
	public ResponseEntity<String> addAirport(@Valid @RequestBody Airport airport) {
			Integer id = airportService.addAirport(airport);
			String message = "The following Airport has been successfully added with Airport Id: " + id;
			return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	

	@Override
	@PostMapping(value = "Airport/{airportId}")
	public ResponseEntity<Integer> deleteAirport(@PathVariable("airportId") Integer airportId) {
			Integer result = airportService.deleteAirport(airportId);
			ResponseEntity<Integer> re = new ResponseEntity<Integer>(result, HttpStatus.OK);
			return re;

	}
	
	@Override
	@GetMapping(value ="Airport", produces="application/json")
	public ResponseEntity<List<Airport>> getAirports() {
			List<Airport> list = null;
			list = airportService.getAirports();
			ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(list, HttpStatus.OK);
			return response;
	}
}
