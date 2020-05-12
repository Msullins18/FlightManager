package com.infy.demo.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.demo.dao.AirportDAO;
import com.infy.demo.model.Airport;
import com.infy.demo.exceptions.AirportNotFoundException;
import com.infy.demo.exceptions.FlightNotFoundException;
import com.infy.demo.exceptions.NoFlightsAvailableException;
import com.infy.demo.model.Flight;
import com.infy.demo.validator.AirportValidator;

@Service(value = "AirportService")
@Transactional
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportDAO airportDAO;

	@Override
	public Integer addFlight(Flight flight) throws Exception {
		// TODO Auto-generated method stub
		AirportValidator.validateFlight(flight);
		boolean doesExist = airportDAO.airportExists(flight.getAirportId());
		if (doesExist) {
			Integer flightId = airportDAO.addFlight(flight);
			return flightId;
		} else {
			throw new AirportNotFoundException(flight.getAirportId());
		}

	}

	@Override
	public Integer deleteFlight(Integer flightId) throws Exception {
		// TODO Auto-generated method stub
		Integer id = airportDAO.deleteFlight(flightId);
		if (id == null) {
			throw new FlightNotFoundException(flightId);
		}
		return id;
	}

	@Override
	public List<Flight> getFlights() throws Exception {
		// TODO Auto-generated method stub
		List<Flight> flightList = airportDAO.getFlights();
		if (flightList == null) {
			throw new NoFlightsAvailableException();
		}
		return flightList;
	}

	@Override
	public Integer addAirport(Airport airport) throws Exception {
		// TODO Auto-generated method stub
		Integer id = airportDAO.addAirport(airport);
		return id;
	}

	@Override
	public Integer deleteAirport(Integer airportId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Integer> id = Optional.of(airportDAO.deleteAirport(airportId));
		if (!id.isPresent()) {
			throw new Exception("userService.AIRPORT_NOT_EXISTS");
		}
		return id.get();
	}

	@Override
	public List<Airport> getAirports() throws Exception {
		// TODO Auto-generated method stub
		Optional<List<Airport>> airports = Optional.of(airportDAO.getAirports());
		if (!airports.isPresent()) {
			throw new Exception("Sorry no Airports are available at the moment");
		}
		return airports.get();
	}

}
