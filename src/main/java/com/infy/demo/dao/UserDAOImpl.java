package com.infy.demo.dao;

import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.infy.demo.entity.UserEntity;
import com.infy.demo.model.User;


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
	public UserEntity getUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		Optional<UserEntity> userEntity = Optional.ofNullable(entityManager.find(UserEntity.class, emailId));
		if(userEntity.isPresent())
		{	
			return userEntity.get();
		}
		else
		{
			throw new UsernameNotFoundException("Email ID not found!");
		}
	}

	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		// TODO Auto-generated method stub
		Boolean available = false;
		Optional<UserEntity> userEntity = Optional.empty();
		if(entityManager.find(UserEntity.class, emailId) != null)
		{
			userEntity = Optional.ofNullable(entityManager.find(UserEntity.class, emailId));
		}
		

		if(!userEntity.isPresent())
		{
			available = true;
		}
		
		return available;
	}
}
