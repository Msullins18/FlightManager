package com.infy.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.demo.dao.AirportDAO;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.model.Airport;
import com.infy.demo.exceptions.AirportNotFoundException;
import com.infy.demo.exceptions.NoAirportsAvailableException;
import com.infy.demo.model.Flight;
@Service(value = "AirportService")
@Transactional
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportDAO airportDAO;

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
		List<Airport> airports = null;
		List<AirportEntity> airportList = airportDAO.getAirports();
		Optional<List<AirportEntity>> checkNull = Optional.ofNullable(airportList);
		if(checkNull.isPresent()){
			airports = new ArrayList<Airport>();
			for(AirportEntity a : airportList){
				Airport airport = new Airport();
				airport.setAirportId(a.getAirportId());
				airport.setAirportName(a.getAirportName());
				airport.setCity(a.getCity());
				airport.setFlights(new ArrayList<Flight>());
				airports.add(airport);
			}
		}
		else
		{
			throw new NoAirportsAvailableException();
		}
		return airports;
	}

}
