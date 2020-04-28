package com.infy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;


@Repository(value = "airportDAO")
public class AirportDAOImpl implements AirportDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Integer addFlight(Flight flight) {
		// TODO Auto-generated method stub
		FlightEntity newFlight = new FlightEntity();
		newFlight.setDateOfArrival(flight.getDateOfArrival());
		newFlight.setDateOfDeparture(flight.getDateOfDeparture());
		newFlight.setDestination(flight.getDestination());
		newFlight.setFlightSize(flight.getFlightSize());
		newFlight.setFlightType(flight.getFlightType());
		newFlight.setDestination(flight.getDestination());
		newFlight.setSeatsAvailable(flight.getSeatsAvailable());
		newFlight.setFlightFare(flight.getFlightFare());
		newFlight.setFlightTax(flight.getFlightTax());
		AirportEntity airport = entityManager.find(AirportEntity.class, flight.getAirportId());
		airport.getFlightEntities().add(newFlight);
		entityManager.persist(airport);
		FlightEntity lastFlight = airport.getFlightEntities().get(airport.getFlightEntities().size()-1);
		return lastFlight.getFlightId();
	}

	@Override
	public Integer deleteFlight(Integer airportId, Integer flightId) {
		// TODO Auto-generated method stub
		AirportEntity airport = entityManager.find(AirportEntity.class, airportId);
		List<FlightEntity> flightList = airport.getFlightEntities();
		List<FlightEntity> updateFlightList = new ArrayList<>();
		for(FlightEntity flight : flightList){
			if(!(flightId.equals(flight.getFlightId()))){
				updateFlightList.add(flight);
		}
			}
		airport.setFlightEntities(updateFlightList);
		return flightId;
	}

}
