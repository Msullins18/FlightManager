package com.infy.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.demo.model.Flight;

public interface TravellerSearchService {
	public List<Flight> getFlights(LocalDate date, String origin, String destination) throws Exception;;
	public List<String> getAllOrigins() throws Exception;;
	public List<String> getAllDestinations() throws Exception;;

}
