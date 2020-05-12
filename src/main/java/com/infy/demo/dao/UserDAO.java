package com.infy.demo.dao;

import com.infy.demo.model.User;

public interface UserDAO {
	public abstract User getUserByEmailId(String emailId);

	public abstract String registerUser(User user);

	public Boolean checkAvailabilityOfEmailId(String emailId);
}
