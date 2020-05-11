package com.infy.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.infy.demo.dao.UserDAO;

import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.User;
import com.infy.demo.model.Airport;
import com.infy.demo.utility.HashingUtility;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	EntityManager entityManager;

	@Override
	public User loginUser(User user) throws Exception {
		// TODO Auto-generated method stub
		User userFromDAO = null;
		String emailId = user.getEmailId().toLowerCase();
		Optional<String> passwordFromDAO = userDAO.getPasswordOfUser(emailId);
		if (passwordFromDAO.isPresent()) {
			String hashedPassword = HashingUtility.getHash(user.getPassword());
			if (hashedPassword.equals(passwordFromDAO.get())) {
				userFromDAO = userDAO.getUserByEmailId(emailId);
			} else {
				throw new InvalidCredentialsException();
			}

		} else {
			throw new UserNotFoundException(emailId);
		}
		return userFromDAO;
	}

	@Override
	public String registerUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String emailId = user.getEmailId().toLowerCase();
		Optional<String> registered = Optional.empty();
		Boolean available = userDAO.checkAvailabilityOfEmailId(emailId);
		if (available) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String passwordToDB = passwordEncoder.encode(user.getPassword());
			System.out.println(passwordToDB);
			System.out.println(user.getPassword());
			user.setEmailId(emailId);
			user.setPassword(passwordToDB);
			registered = Optional.of(userDAO.registerUser(user));
		}
		if (!registered.isPresent()) {
			throw new EmailUnavailableException(emailId);
		}
		return registered.get();
	}

}
