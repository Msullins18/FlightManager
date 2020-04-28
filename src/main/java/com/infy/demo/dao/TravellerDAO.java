package com.infy.demo.dao;

import com.infy.demo.model.Traveller;

public interface TravellerDAO {
	public abstract String getPasswordOfTraveller(String emailId);
	public abstract Traveller getTravellerByEmailId(String emailId);
	public abstract String registerTraveller(Traveller traveller);
	public abstract Boolean checkAvailabilityOfEmailId(String emailId);
}