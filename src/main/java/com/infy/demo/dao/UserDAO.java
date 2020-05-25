package com.infy.demo.dao;

import com.infy.demo.entity.UserEntity;
import com.infy.demo.model.User;

public interface UserDAO {
	public abstract UserEntity getUserByEmailId(String emailId);

	public abstract String registerUser(User user);

	public abstract Boolean checkAvailabilityOfEmailId(String emailId);
}
