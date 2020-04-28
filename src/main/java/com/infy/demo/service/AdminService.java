package com.infy.demo.service;

import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;

public interface AdminService {
	public abstract Admin loginAdmin(Admin admin) throws Exception;
	public abstract String registerAdmin(Admin admin) throws Exception;
	public abstract Integer addAirport(Airport airport) throws Exception;
	public abstract Integer deleteAirport(Integer airportId) throws Exception;
}
