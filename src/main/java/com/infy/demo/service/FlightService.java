package com.infy.demo.service;

import java.util.List;

import com.infy.demo.model.Flight;

public interface FlightService {
	public abstract Integer addFlight(Flight flight) throws Exception;
	public abstract Integer deleteFlight(Integer flightId) throws Exception;
	public abstract List<Flight> getFlights() throws Exception;
}
