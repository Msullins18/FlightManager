package com.infy.demo;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.infy.demo.dao.TravelerSearchDAO;
import com.infy.demo.dao.TravelerSearchDAOImpl;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;


public class TravelerSearchDAOTest {
	
	@Mock
	private EntityManager entityManager;    
	@Mock
	private Query query;
	
	@InjectMocks
	private TravelerSearchDAO travelerSearchDAO = new TravelerSearchDAOImpl();
	
	private List<FlightEntity> flightEntityList;
	private List<AirportEntity> airportEntityList;
	private Integer airportId = 1000;
	private String destination = "New York";
	private Integer numberOfTickets = 2;
	private LocalDate date = LocalDate.now().plusDays(1);
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getFlights() throws Exception{
		flightEntityList = new ArrayList<>();
		FlightEntity flightEntity = new FlightEntity();
		flightEntity.setAirportId(airportId);
		flightEntity.setDateOfArrival(date);
		flightEntity.setDateOfDeparture(date);
		flightEntity.setDestination(destination);
		flightEntity.setSeatsAvailable(999);
		flightEntityList.add(flightEntity);
		
		Mockito.when(entityManager.createQuery("select f from FlightEntity f where f.airportId =: airportId and f.destination =:destination")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(flightEntityList);

		List<FlightEntity> result = travelerSearchDAO.getFlights(date, airportId, destination, numberOfTickets);
		Assert.assertNotNull(result);	
	}
	
	@Test
	public void getAllOrigins(){
		airportEntityList = new ArrayList<>();
		AirportEntity airportEntity = new AirportEntity();
		airportEntity.setAirportId(airportId);
		airportEntity.setAirportName("DFW");
		airportEntity.setCity("Dallas-ForthWorth");
		airportEntity.setFlightEntities(flightEntityList);
		airportEntityList.add(airportEntity);
		
		Mockito.when(entityManager.createQuery("select f from FlightEntity f")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(flightEntityList);
	    Mockito.when(entityManager.find(AirportEntity.class, airportId)).thenReturn(airportEntity);
	    
		List<AirportEntity> result = travelerSearchDAO.getAllOrigins();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void getAllDestination(){
		Mockito.when(entityManager.createQuery("select f from FlightEntity f")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(flightEntityList);
		List<String> result = travelerSearchDAO.getAllDestinations();
		Assert.assertNotNull(result);
	}

}
