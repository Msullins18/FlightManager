package com.infy.demo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

@Repository(value = "travelerSearchDAO")
public class TravelerSearchDAOImpl implements TravelerSearchDAO {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Flight> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets) throws Exception {
		String queryString = "select f from FlightEntity f where f.airportId =: airportId and f.destination =:destination";
		Query query=entityManager.createQuery(queryString);
		query.setParameter("airportId", airportId);
		query.setParameter("destination", destination);
		List<FlightEntity> flightEntityList = query.getResultList();

		List<Flight> flightList = null;
		LocalDate today = LocalDate.now();
		if(flightEntityList != null){
			flightList = new ArrayList<>();
			for(FlightEntity flightEntity: flightEntityList){
				if(flightEntity.getSeatsAvailable()<1 && flightEntity.getDateOfDeparture().isBefore(today))
					return flightList;
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
				if(numberOfTickets>flight.getSeatsAvailable()){
					throw new Exception("TravelerSearchDAO.Not_Engough_Tickets_Available");
				}
				flightList.add(flight);
			}
		}
		return flightList;
	}

	@Override
	public List<Airport> getAllOrigins() {
		String queryString = "select f from FlightEntity f";
		Query query=entityManager.createQuery(queryString);
		List<FlightEntity> flightEntityList = query.getResultList();
		List<Airport> originList = new ArrayList<>();
		List<Airport> airportList = new ArrayList<>();
		if(flightEntityList != null){
			for(FlightEntity flightEntity: flightEntityList){
				AirportEntity airportEntity = entityManager.find(AirportEntity.class, flightEntity.getAirportId());
				Airport airport = new Airport();
				airport.setAirportId(airportEntity.getAirportId());
				airport.setCity(airportEntity.getCity());
				airport.setName(airportEntity.getName());
				originList.add(airport);
			}
			airportList = originList.stream()
                    .filter( distinctByKey(p -> p.getAirportId()) )
                    .collect( Collectors.toList() );
		}
		return airportList;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
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
