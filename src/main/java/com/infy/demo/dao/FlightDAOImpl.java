package com.infy.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.model.Flight;

@Repository(value="flightDAO")
public class FlightDAOImpl implements FlightDAO {
	
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
		newFlight.setAirportId(flight.getAirportId());
		newFlight.setFlightTax(flight.getFlightTax());
		
		AirportEntity airport = entityManager.find(AirportEntity.class, flight.getAirportId());
		airport.getFlightEntities().add(newFlight);
		entityManager.persist(airport);
		FlightEntity lastFlight = airport.getFlightEntities().get(airport.getFlightEntities().size()-1);
		return lastFlight.getFlightId();
	}

	@Override
	public Integer deleteFlight(Integer flightId) {
		// TODO Auto-generated method stub
		FlightEntity f1 = entityManager.find(FlightEntity.class, flightId);
		entityManager.remove(f1);
		return flightId;
	}

	@Override
	public List<FlightEntity> getFlights() {
		// TODO Auto-generated method stub
		String dft = "SELECT f FROM FlightEntity f";
		Query query = entityManager.createQuery(dft);
		List<FlightEntity> flightEntity = query.getResultList();

		return flightEntity;
	}

}
