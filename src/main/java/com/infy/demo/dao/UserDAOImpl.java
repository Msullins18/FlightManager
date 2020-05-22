package com.infy.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.demo.entity.UserEntity;
import com.infy.demo.entity.AirportEntity;
import com.infy.demo.entity.FlightEntity;
import com.infy.demo.model.User;
import com.infy.demo.model.Airport;
import com.infy.demo.model.Flight;

@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public String registerUser(User user) {
		// TODO Auto-generated method stub
		UserEntity userEntity = new UserEntity();
		
		userEntity.setEmailId(user.getEmailId());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhoneNumber(user.getPhoneNumber());
		userEntity.setUserType(user.getUserType());
		entityManager.persist(userEntity);
		
		return userEntity.getEmailId();
	}

	@Override
	public Optional<String> getPasswordOfUser(String emailId) {
		Optional<String> password = Optional.empty();
		UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
		
		if (password.isPresent()){
			password = Optional.of(userEntity.getPassword());
		}
		
		return password;
	}

	@Override
	public User getUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
		User user = new User();
		System.out.println(userEntity);
		user.setEmailId(userEntity.getEmailId());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setPassword(userEntity.getPassword());
		user.setPhoneNumber(userEntity.getPhoneNumber());
		
		return user;
	}

	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		// TODO Auto-generated method stub
		Boolean available = false;
		Optional<UserEntity> userEntity = Optional.ofNullable(entityManager.find(UserEntity.class, emailId));

		if(!userEntity.isPresent())
		{
			available = true;
		}
		
		return available;
	}
}
