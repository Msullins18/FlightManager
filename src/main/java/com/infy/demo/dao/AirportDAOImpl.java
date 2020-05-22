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


@Repository(value = "airportDAO")
public class AirportDAOImpl implements AirportDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public boolean airportExists(Integer airportId) {
		// TODO Auto-generated method stub
		Optional<AirportEntity> checkNull = Optional.ofNullable(entityManager.find(AirportEntity.class, airportId));
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
		Optional<AirportEntity> airport = Optional.ofNullable(entityManager.find(AirportEntity.class, airportId));
		if(airport.isPresent()){
			AirportEntity ae = airport.get();
			ae.setFlightEntities(null);
			id = ae.getAirportId();
			entityManager.remove(ae);
		}
		return id;
	}

	@Override
	public List<AirportEntity> getAirports() {
		// TODO Auto-generated method stub\
		String dft = "SELECT a FROM AirportEntity a";
		Query query = entityManager.createQuery(dft);
		List<AirportEntity> airportList = query.getResultList();		
		return airportList;

	}

}
