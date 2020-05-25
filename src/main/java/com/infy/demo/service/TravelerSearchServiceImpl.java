package com.infy.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
	public List<Flight> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets) {
		TravelValidator.validateTravel(date);;
		List<FlightEntity> flightEntityList = travelerSearchDAO.getFlights(date, airportId, destination, numberOfTickets);
		if(flightEntityList.isEmpty())
			throw new NoFlightsAvailableException();
		
		List<Flight> flightList = new ArrayList<>();
		for(FlightEntity flightEntity:flightEntityList){
			if(numberOfTickets<flightEntity.getSeatsAvailable()){
				Flight flight = new Flight();
				flight.setAirportId(flightEntity.getAirportId());
				flight.setDateOfArrival(flightEntity.getDateOfArrival());
				flight.setDateOfDeparture(flightEntity.getDateOfDeparture());
				flight.setDestination(flightEntity.getDestination());
				flight.setFlightFare(flightEntity.getFlightFare());
				flight.setFlightId(flightEntity.getFlightId());
				flight.setFlightSize(flightEntity.getFlightSize());
				flight.setFlightTax(flightEntity.getFlightTax());
				flight.setFlightType(flightEntity.getFlightType());
				flight.setSeatsAvailable(flightEntity.getSeatsAvailable());
				flightList.add(flight);
			}		
		}
		return flightList;
	}

	@Override
	public List<Airport> getAllOrigins() {
		List<AirportEntity> airportEntityList= travelerSearchDAO.getAllOrigins();
		List<Airport> airportList = new ArrayList<>();
		for(AirportEntity airportEntity:airportEntityList){
			Airport airport = new Airport();
			airport.setAirportId(airportEntity.getAirportId());
			airport.setCity(airportEntity.getCity());
			airport.setAirportName(airportEntity.getAirportName());
			airportList.add(airport);
		}
		return airportList;
	}

	@Override
	public List<String> getAllDestinations() {
		return travelerSearchDAO.getAllDestinations();
	}

}
