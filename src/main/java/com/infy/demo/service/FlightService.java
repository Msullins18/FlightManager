package com.infy.demo.service;

import java.util.List;

import com.infy.demo.model.Flight;

public interface FlightService {
	public abstract Integer addFlight(Flight flight);
	public abstract Integer deleteFlight(Integer flightId);
	public abstract List<Flight> getFlights();
}
