package com.infy.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.demo.dao.TravellerSearchDAO;
import com.infy.demo.model.Flight;
import com.infy.demo.validator.TravelValidator;

@Service( value = "TravellerSearchService" )
@Transactional
public class TravellerSearchServiceImpl implements TravellerSearchService {
	@Autowired
	private TravellerSearchDAO travellerSearchDAO;

	@Override
	public List<Flight> getFlights(LocalDate date, String origin, String destination) throws Exception {
		TravelValidator.isValidDate(date);
		List<Flight> flightList = travellerSearchDAO.getFlights(origin, destination);
		if(flightList.isEmpty())
			throw new Exception("TravellerSearchService.NO_Flights_AVAILABLE");
		return flightList;
	}

	@Override
	public List<String> getAllOrigins() throws Exception {
		return travellerSearchDAO.getAllOrigins();
	}

	@Override
	public List<String> getAllDestinations() throws Exception {
		return travellerSearchDAO.getAllDestinations();
	}

}
