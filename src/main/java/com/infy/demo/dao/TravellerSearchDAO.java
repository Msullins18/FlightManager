package com.infy.demo.dao;

import java.util.List;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;


public interface TravellerSearchDAO {
	public List<Flight> getFlights(String origin, String destination);
	public List<String> getAllOrigins();
	public List<String> getAllDestinations();
	


}
