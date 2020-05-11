package com.infy.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	public List<Flight> getFlights() {
		// TODO Auto-generated method stub
		List<Flight> flights = null;
		String dft = "SELECT f FROM FlightEntity f";
		Query query = entityManager.createQuery(dft);
		List<FlightEntity> flightEntity = query.getResultList();
		Optional<List<FlightEntity>> checkNull = Optional.ofNullable(flightEntity);
		if(checkNull.isPresent()){
			flights = new ArrayList<Flight>();
			for(FlightEntity f : flightEntity){
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

		return flights;
	}

	@Override
	public boolean airportExists(Integer airportId) {
		// TODO Auto-generated method stub
		AirportEntity airport = entityManager.find(AirportEntity.class, airportId);
		Optional<AirportEntity> checkNull = Optional.ofNullable(airport);
		if(checkNull.isPresent())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@Override
	public Integer addAirport(Airport airport) {
		// TODO Auto-generated method stub
		AirportEntity newAirport = new AirportEntity();
		
		newAirport.setCity(airport.getCity());
		newAirport.setAirportName(airport.getAirportName());
		
		List<FlightEntity> listOfFlights = new ArrayList<>();
		
		newAirport.setFlightEntities(listOfFlights);
		
		entityManager.persist(newAirport);
		
		return newAirport.getAirportId();
	}

	@Override
	public Integer deleteAirport(Integer airportId) {
		// TODO Auto-generated method stub
		Integer id = null;
		Optional<AirportEntity> airport = Optional.of(entityManager.find(AirportEntity.class, airportId));
		if(airport.isPresent()){
			AirportEntity ae = airport.get();
			ae.setFlightEntities(null);
			id = ae.getAirportId();
			entityManager.remove(ae);
		}
		return id;
	}

	@Override
	public List<Airport> getAirports() {
		// TODO Auto-generated method stub\
		List<Airport> airports = null;

		String dft = "SELECT a FROM AirportEntity a";
		Query query = entityManager.createQuery(dft);
		List<AirportEntity> airportList = query.getResultList();
		airports = new ArrayList<Airport>();
		if(!airportList.isEmpty()){
			for(AirportEntity a : airportList){
				Airport airport = new Airport();
				airport.setAirportId(a.getAirportId());
				airport.setAirportName(a.getAirportName());
				airport.setCity(a.getCity());
				airport.setFlights(new ArrayList<Flight>());
				airports.add(airport);
			}
		}
		return airports;

	}

}
