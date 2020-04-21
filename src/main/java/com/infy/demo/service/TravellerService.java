package com.infy.demo.service;

import com.infy.demo.model.Traveller;

public interface TravellerService {
	public abstract Traveller loginTraveller(Traveller traveller);
	public abstract Boolean registerTraveller(Traveller traveller);
}
