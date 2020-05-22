package com.infy.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.demo.dao.TravelerSearchDAO;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.exceptions.NoFlightsAvailableException;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;
import com.infy.demo.validator.TravelValidator;

@Service( value = "TravelerSearchService" )
@Transactional
public class TravelerSearchServiceImpl implements TravelerSearchService {
	@Autowired
	private TravelerSearchDAO travelerSearchDAO;

	@Override
	public List<Flight> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets) throws Exception {
		TravelValidator.validateTravel(date);;
		List<Flight> flightList = travelerSearchDAO.getFlights(date, airportId, destination, numberOfTickets);
		if(flightList.isEmpty())
		{
			throw new NoFlightsAvailableException();
		}
			
		return flightList;
	}

	@Override
	public List<Airport> getAllOrigins() throws Exception {
		return travelerSearchDAO.getAllOrigins();
	}

	@Override
	public List<String> getAllDestinations() throws Exception {
		return travelerSearchDAO.getAllDestinations();
	}

}
