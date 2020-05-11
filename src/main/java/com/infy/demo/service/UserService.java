package com.infy.demo.service;

import com.infy.demo.model.User;

public interface UserService {
	public abstract User loginUser(User user) throws Exception;
	public abstract String registerUser(User user) throws Exception;
}
