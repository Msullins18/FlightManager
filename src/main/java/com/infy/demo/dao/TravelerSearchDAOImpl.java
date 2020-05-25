package com.infy.demo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

@Repository(value = "travelerSearchDAO")
public class TravelerSearchDAOImpl implements TravelerSearchDAO {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<FlightEntity> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets){
		String queryString = "select f from FlightEntity f where f.airportId =: airportId and f.destination =:destination";
		Query query=entityManager.createQuery(queryString);
		query.setParameter("airportId", airportId);
		query.setParameter("destination", destination);
		List<FlightEntity> flightEntityList = query.getResultList();

		List<FlightEntity> flightList = new ArrayList<>();
		LocalDate today = LocalDate.now();
		Optional<List<FlightEntity>> checkNull = Optional.ofNullable(flightEntityList);
		if(checkNull.isPresent()){
			for(FlightEntity flightEntity: flightEntityList){
				if(flightEntity.getSeatsAvailable()<1 && flightEntity.getDateOfDeparture().isBefore(today))
					return flightList;
				if(flightEntity.getDateOfDeparture().isAfter(date) || flightEntity.getDateOfDeparture().isEqual(date))
					flightList.add(flightEntity);
			}
		}
		return flightList;
	}

	@Override
	public List<AirportEntity> getAllOrigins() {
		String queryString = "select f from FlightEntity f";
		Query query=entityManager.createQuery(queryString);
		List<FlightEntity> flightEntityList = query.getResultList();
		List<AirportEntity> originList = new ArrayList<>();
		List<AirportEntity> airportList = new ArrayList<>();
		Optional<List<FlightEntity>> checkNull = Optional.ofNullable(flightEntityList);
		if(checkNull.isPresent()){
			for(FlightEntity flightEntity: flightEntityList){
				AirportEntity airportEntity = entityManager.find(AirportEntity.class, flightEntity.getAirportId());

				originList.add(airportEntity);
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
		List<String> finalDestinationList = new ArrayList<>();
		Optional<List<FlightEntity>> checkNull = Optional.ofNullable(flightEntityList);
		if(checkNull.isPresent()){
			for(FlightEntity flight: flightEntityList){
				String destination = flight.getDestination();
				destinationList.add(destination);
			}	
		}
		finalDestinationList =destinationList.stream()
                .filter( distinctByKey(p -> p) )
                .collect( Collectors.toList() );
		return finalDestinationList;
	}

	

}
