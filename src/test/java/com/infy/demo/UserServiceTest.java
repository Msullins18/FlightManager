package com.infy.demo;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import com.infy.demo.dao.UserDAO;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.User;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;
import com.infy.demo.utility.HashingUtility;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	UserDAO userDAO;
   	
	@InjectMocks
	UserService userService = new UserServiceImpl();
	
	@Test
	public void authenticateUserLoginValidCredentials() throws Exception 
	{
		User user = new User();
		User returned = new User();
		user.setEmailId("marcus@marcus.com");
		user.setPassword("Me@123");
		
		returned.setEmailId("marcus@marcus.com");
		returned.setPassword(HashingUtility.getHash("Me@123"));
		
		when(userDAO.getPasswordOfUser("marcus@marcus.com")).thenReturn(Optional.of(HashingUtility.getHash("Me@123")));
		when(userDAO.getUserByEmailId("marcus@marcus.com")).thenReturn(returned);
		
		User userFromDAO = userService.loginUser(user);
		assertEquals("marcus@marcus.com", userFromDAO.getEmailId());
	}
	
	@Test
	public void authenticateAdminLoginInvalidCredentials() throws Exception 
	{
		expectedException.expect(InvalidCredentialsException.class);
		User user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setPassword("Me@12");
		
		when(userDAO.getPasswordOfUser("marcus@marcus.com")).thenReturn(Optional.of(HashingUtility.getHash("Me@123")));
		
		User userFromDAO = userService.loginUser(user);

	}
	

	@Test
	public void authenticateAdminLoginUserNotFound() throws Exception 
	{
		expectedException.expect(UserNotFoundException.class);
		User user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setPassword("Me@123");
		Optional <String> empty = Optional.empty(); 
		when(userDAO.getPasswordOfUser("marcus@marcus.com")).thenReturn(empty);
		User userFromDAO = userService.loginUser(user);

	}
	
	@Test
	public void registerAdminValidInputs() throws Exception 
	{
		User user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setPassword("Me@123");
		user.setFirstName("marcus");
		user.setLastName("marcus");
		user.setPhoneNumber("1112225545");
		
		when(userDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(true);
		when(userDAO.registerUser(user)).thenReturn("marcus@marcus.com");
		String registered = userService.registerUser(user);
		assertEquals("marcus@marcus.com", registered);
	}
	
	@Test
	public void registerAdminInvalidInputs() throws Exception 
	{
		expectedException.expect(TransactionSystemException.class);
		User user = new User();
		user.setEmailId("marcusmarcus.com");
		user.setPassword("Me@123");
		user.setFirstName("marcus");
		user.setLastName("marcus");
		user.setPhoneNumber("1112225545");
		
		when(userDAO.checkAvailabilityOfEmailId("marcusmarcus.com")).thenReturn(true);
		when(userDAO.registerUser(user)).thenThrow(TransactionSystemException.class);
		String registered = userService.registerUser(user);
	}
	
	@Test
	public void registerAdminTakenEmail() throws Exception 
	{
		expectedException.expect(EmailUnavailableException.class);
		User user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setPassword("Me@123");
		user.setFirstName("marcus");
		user.setLastName("marcus");
		user.setPhoneNumber("1112225545");
		
		when(userDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(false);
		String registered = userService.registerUser(user);
	}
	

}
