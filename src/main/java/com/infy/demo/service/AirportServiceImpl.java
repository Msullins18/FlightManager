package com.infy.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.demo.dao.AirportDAO;
import com.infy.demo.model.Flight;
import com.infy.demo.validator.AirportValidator;

@Service( value = "AirportService" )
@Transactional
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportDAO airportDAO;
	
	@Override
	public Integer addFlight(Flight flight) {
		// TODO Auto-generated method stub
		try {
			AirportValidator.validateFlight(flight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Integer flightId = airportDAO.addFlight(flight);
		return flightId;
	}

	@Override
	public Integer deleteFlight(Integer airportId, Integer flightId) {
		// TODO Auto-generated method stub
		return airportDAO.deleteFlight(airportId, flightId);
	}

}
