package com.infy.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infy.demo.dao.UserDAO;
import com.infy.demo.entity.UserEntity;
import com.infy.demo.model.User;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	UserDAO userDAO;
	//override user details service to check received credentials from frontend against database credentials
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity = userDAO.getUserByEmailId(emailId);
		User user = new User();
		user.setEmailId(userEntity.getEmailId());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setPassword(userEntity.getPassword());
		user.setPhoneNumber(userEntity.getPhoneNumber());
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), new ArrayList<>());
	}
}
