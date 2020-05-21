package com.infy.demo.dao;

import java.time.LocalDate;
import java.util.List;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;



public interface TravelerSearchDAO {
	public List<FlightEntity> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets);
	public List<AirportEntity> getAllOrigins();
	public List<String> getAllDestinations();
	


}
