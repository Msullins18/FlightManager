package com.infy.demo.dao;

import com.infy.demo.model.Traveller;

public interface TravellerDAO {
	public abstract String getPasswordOfTraveller(String emailId);
	public abstract Traveller getTravellerByEmailId(String emailId);
	public abstract String registerTraveller(Traveller traveller);
	public abstract Boolean checkAvailabilityOfEmailId(String emailId);
<<<<<<< HEAD
}
=======
}
>>>>>>> bff0372be609b14f4ea6c2b7f0cf049c773969bd
