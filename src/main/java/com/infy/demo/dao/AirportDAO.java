package com.infy.demo.dao;

import java.util.List;

import com.infy.demo.entity.AirportEntity;
import com.infy.demo.model.Airport;

public interface AirportDAO {
	public abstract boolean airportExists(Integer airportId);
	public abstract Integer addAirport(Airport airport);
	public abstract Integer deleteAirport(Integer airportId);
	public abstract List<AirportEntity> getAirports();
}
