package com.infy.demo.dao;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

public interface AirportDAO {
	public abstract Integer addFlight(Flight flight);
	public abstract Integer deleteFlight(Integer airportId, Integer flightId);
}
