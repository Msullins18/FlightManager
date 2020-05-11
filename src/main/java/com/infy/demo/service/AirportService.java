package com.infy.demo.service;

import java.util.List;

import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

public interface AirportService {
	public abstract Integer addFlight(Flight flight) throws Exception;
	public abstract Integer deleteFlight(Integer flightId) throws Exception;
	public abstract List<Flight> getFlights() throws Exception;
	public abstract Integer addAirport(Airport airport) throws Exception;
	public abstract Integer deleteAirport(Integer airportId) throws Exception;
	public abstract List<Airport> getAirports() throws Exception;
}
