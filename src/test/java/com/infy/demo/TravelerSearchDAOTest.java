package com.infy.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.demo.dao.TravelerSearchDAO;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.exceptions.NotEnoughTicketsException;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class TravelerSearchDAOTest {
	@Autowired
	private TravelerSearchDAO travelerSearchDAO;
	
	private static Integer airportId;
	private static String destination;
	private static Integer numberOfTickets;
	private static LocalDate date;
	
	@BeforeClass
	public static void setup(){
		date = LocalDate.now().plusDays(14);
		airportId = 1000;
		destination = "New York";
		numberOfTickets = 2;
	}
	
	@Test
	public void getFlights() throws Exception{
		List<FlightEntity> flightEntityList = new ArrayList<>();
		List<Flight> flightList = new ArrayList<>();
		for(FlightEntity flightEntity: flightEntityList){
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
		travelerSearchDAO.getFlights(date, airportId, destination, numberOfTickets);
		Assert.assertTrue(true);
	}
	
	
	@Test
	public void getAllOrigins(){
		List<FlightEntity> flightEntityList = new ArrayList<>();
		List<Airport> originList = new ArrayList<>();
		for(FlightEntity flightEntity: flightEntityList){
			AirportEntity airportEntity =  null;
			Airport airport = new Airport();
			airport.setAirportId(airportEntity.getAirportId());
			airport.setCity(airportEntity.getCity());
			airport.setAirportName(airportEntity.getAirportName());
			originList.add(airport);
		}
		travelerSearchDAO.getAllOrigins();
		Assert.assertTrue(true);
	}
	
	@Test
	public void getAllDestination(){
		List<FlightEntity> flightEntityList = new ArrayList<>();
		List<String> destinationList = new ArrayList<>();
		for(FlightEntity flight: flightEntityList){
			String destination = flight.getDestination();
			destinationList.add(destination);
		}
		travelerSearchDAO.getAllDestinations();
		Assert.assertTrue(true);
	}

}
