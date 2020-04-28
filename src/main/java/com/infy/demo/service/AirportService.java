package com.infy.demo.service;

import com.infy.demo.model.Flight;

public interface AirportService {
	public abstract Integer addFlight(Flight flight);
	public abstract Integer deleteFlight(Integer airportId, Integer flightId);
}
