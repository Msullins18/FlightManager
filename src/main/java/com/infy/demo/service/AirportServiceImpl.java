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
import com.infy.demo.exceptions.NoAirportsAvailableException;
import com.infy.demo.exceptions.NoFlightsAvailableException;
import com.infy.demo.model.Flight;
import com.infy.demo.validator.AirportValidator;

@Service(value = "AirportService")
@Transactional
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportDAO airportDAO;

	@Override
	public Integer addFlight(Flight flight) {
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
	public Integer deleteFlight(Integer flightId) {
		// TODO Auto-generated method stub
		Optional<Integer> idCheck = Optional.ofNullable(flightId);
		if(!idCheck.isPresent()){
			throw new FlightNotFoundException(flightId);
		}
		return idCheck.get();
	}

	@Override
	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		List<Flight> flightList = airportDAO.getFlights();
		if (flightList == null) {
			throw new NoFlightsAvailableException();
		}
		return flightList;
	}

	@Override
	public Integer addAirport(Airport airport) {
		// TODO Auto-generated method stub
		Integer id = airportDAO.addAirport(airport);
		return id;
	}

	@Override
	public Integer deleteAirport(Integer airportId) {
		// TODO Auto-generated method stub
		Optional<Integer> id = Optional.of(airportDAO.deleteAirport(airportId));
		if (!id.isPresent()) {
			throw new AirportNotFoundException(airportId);
		}
		return id.get();
	}

	@Override
	public List<Airport> getAirports() {
		// TODO Auto-generated method stub
		Optional<List<Airport>> airports = Optional.of(airportDAO.getAirports());
		if (!airports.isPresent()) {
			throw new NoAirportsAvailableException();
		}
		return airports.get();
	}

}
