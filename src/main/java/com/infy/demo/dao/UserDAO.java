package com.infy.demo.dao;

import java.util.List;
import java.util.Optional;

import com.infy.demo.model.User;
import com.infy.demo.model.Airport;

public interface UserDAO {
	public abstract Optional<String> getPasswordOfUser(String emailId);
	public abstract User getUserByEmailId(String emailId);
	public abstract String registerUser(User user);
	public Boolean checkAvailabilityOfEmailId(String emailId);
}
