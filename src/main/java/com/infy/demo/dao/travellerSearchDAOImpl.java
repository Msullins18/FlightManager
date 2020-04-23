package com.infy.demo.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.model.Flight;

@Repository(value = "travellerSearchDAO")
public class travellerSearchDAOImpl implements TravellerSearchDAO {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Flight> getFlights(String origin, String destination) {
		String queryString = "select f from FlightEntity f where f.origin =: origin and f.destination =:destination";
		Query query=entityManager.createQuery(queryString);
		query.setParameter("origin", origin);
		query.setParameter("destination", destination);
		List<FlightEntity> flightEntityList = query.getResultList();
		List<Flight> flightList = new ArrayList<>();
		if(flightEntityList != null){
			for(FlightEntity flightEntity: flightEntityList){
//				if(flightEntity.getDestination().equals(destination)){
					Flight flight = new Flight();
					flight.setFlightId(flightEntity.getFlightId());
					flight.setFlightNo(flightEntity.getFlightNo());
					flight.setOrigin(flightEntity.getOrigin());
					flight.setDestination(flightEntity.getDestination());
					flight.setBaseFare(flightEntity.getBaseFare());
					flight.setTax(flightEntity.getTax());
					flightList.add(flight);
//				}
			}
		}
		return flightList;
	}

	@Override
	public List<String> getAllOrigins() {
		String queryString = "select f from FlightEntity f";
		Query query=entityManager.createQuery(queryString);
		List<FlightEntity> flightEntityList = query.getResultList();
		List<String> originList = new ArrayList<>();
		if(flightEntityList != null){
			for(FlightEntity flight: flightEntityList){
				String origin = flight.getOrigin();
				originList.add(origin);
			}
			
		}
		return originList;
	}

	@Override
	public List<String> getAllDestinations() {
		String queryString = "select f from FlightEntity f";
		Query query=entityManager.createQuery(queryString);
		List<FlightEntity> flightEntityList = query.getResultList();
		List<String> destinationList = new ArrayList<>();
		if(flightEntityList != null){
			for(FlightEntity flight: flightEntityList){
				String destination = flight.getDestination();
				destinationList.add(destination);
			}	
		}
		return destinationList;
	}

	

}
