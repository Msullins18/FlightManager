package com.infy.demo.dao;

import java.util.List;

import com.infy.demo.entity.FlightEntity;
import com.infy.demo.model.Flight;

public interface FlightDAO {
	public abstract Integer addFlight(Flight flight);
	public abstract Integer deleteFlight(Integer flightId);
	public abstract List<FlightEntity> getFlights();
}
