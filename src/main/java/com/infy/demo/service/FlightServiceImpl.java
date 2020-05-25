package com.infy.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.demo.dao.AirportDAO;
import com.infy.demo.dao.FlightDAO;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.exceptions.AirportNotFoundException;
import com.infy.demo.exceptions.FlightNotFoundException;
import com.infy.demo.model.Flight;
import com.infy.demo.validator.AirportValidator;

@Service(value = "FlightService")
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	AirportDAO airportDAO;
	
	@Autowired 
	FlightDAO flightDAO;

	@Override
	public Integer addFlight(Flight flight) {
		// TODO Auto-generated method stub
		AirportValidator.validateFlight(flight);
		boolean doesExist = airportDAO.airportExists(flight.getAirportId());
		if (doesExist) {
			Integer flightId = flightDAO.addFlight(flight);
			return flightId;
		} else {
			throw new AirportNotFoundException(flight.getAirportId());
		}

	}

	@Override
	public Integer deleteFlight(Integer flightId) {
		// TODO Auto-generated method stub
		Integer id = flightDAO.deleteFlight(flightId);
		if (id == null) {
			throw new FlightNotFoundException(flightId);
		}
		return flightDAO.deleteFlight(flightId);
	}

	@Override
	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		List<FlightEntity> flightList = flightDAO.getFlights();
		List<Flight> flights = null;
		Optional<List<FlightEntity>> checkNull = Optional.ofNullable(flightList);
		if(checkNull.isPresent()){
			flights = new ArrayList<Flight>();
			for(FlightEntity f : flightList){
				Flight flight = new Flight();
				flight.setAirportId(f.getAirportId());
				flight.setDateOfArrival(f.getDateOfArrival());
				flight.setDateOfDeparture(f.getDateOfDeparture());
				flight.setDestination(f.getDestination());
				flight.setFlightId(f.getFlightId());
				flight.setFlightSize(f.getFlightSize());
				flight.setFlightTax(f.getFlightTax());
				flight.setFlightFare(f.getFlightFare());
				flight.setFlightType(f.getFlightType());
				flight.setSeatsAvailable(f.getSeatsAvailable());
				flights.add(flight);
			}
		}
		else
		{
			throw new NoFlightsAvailableException();
		}
		return flights;
	}
}
