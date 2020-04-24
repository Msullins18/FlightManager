package com.infy.demo.dao;

import java.time.LocalDate;
import java.util.List;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;


public interface TravelerSearchDAO {
	public List<Flight> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets);
	public List<String> getAllOrigins();
	public List<String> getAllDestinations();
	


}
