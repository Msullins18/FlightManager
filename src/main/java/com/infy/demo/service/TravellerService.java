package com.infy.demo.service;

import com.infy.demo.model.Traveller;

public interface TravellerService {
	public abstract Traveller loginTraveller(Traveller traveller) throws Exception;
	public abstract String registerTraveller(Traveller traveller) throws Exception;
	
}
