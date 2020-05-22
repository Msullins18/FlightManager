package com.infy.demo.service;

import java.util.List;

import com.infy.demo.model.Airport;

public interface AirportService {
	public abstract Integer addAirport(Airport airport);
	public abstract Integer deleteAirport(Integer airportId);
	public abstract List<Airport> getAirports();
}
