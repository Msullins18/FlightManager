package com.infy.demo.dao;

import java.time.LocalDate;
import java.util.List;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;


public interface TravelerSearchDAO {
	public List<Flight> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets) throws Exception;
	public List<Airport> getAllOrigins();
	public List<String> getAllDestinations();
	


}
