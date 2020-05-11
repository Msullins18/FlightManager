package com.infy.demo.dao;

import java.util.List;
import java.util.Optional;

import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;

public interface AdminDAO {
	public abstract Optional<String> getPasswordOfAdmin(String emailId);
	public abstract Admin getAdminByEmailId(String emailId);
	public abstract String registerAdmin(Admin admin);
	public Boolean checkAvailabilityOfEmailId(String emailId);
	public abstract Integer addAirport(Airport airport);
	public abstract Integer deleteAirport(Integer airportId);
	public abstract List<Airport> getAirports();
}
