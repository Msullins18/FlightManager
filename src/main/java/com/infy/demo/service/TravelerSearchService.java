package com.infy.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

public interface TravelerSearchService {
	public List<Flight> getFlights(LocalDate date, Integer airportId, String destination, Integer numberOfTickets);
	public List<Airport> getAllOrigins();
	public List<String> getAllDestinations();

}
