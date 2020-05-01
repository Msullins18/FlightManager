package com.infy.demo.dao;

import java.util.List;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

public interface AirportDAO {
	public abstract Integer addFlight(Flight flight);
	public abstract Integer deleteFlight(Integer flightId);
	public abstract List<Flight> getFlights();
}
