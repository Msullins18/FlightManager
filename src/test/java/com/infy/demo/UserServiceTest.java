package com.infy.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import org.springframework.transaction.TransactionSystemException;
import com.infy.demo.dao.UserDAO;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.model.User;
import com.infy.demo.model.UserType;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;


public class UserServiceTest {
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	private UserDAO userDAO;
   	
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	private User user;
	
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setPassword("Me@123");
		user.setFirstName("marcus");
		user.setLastName("marcus");
		user.setPhoneNumber("1112225545");
		user.setUserType(UserType.ADMIN);
    }
    
	@Test
	public void registerAdminValidInputs() throws Exception 
	{
		when(userDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(true);
		when(userDAO.registerUser(user)).thenReturn("marcus@marcus.com");
		String registered = userService.registerUser(user);
		assertEquals("marcus@marcus.com", registered);
	}
	
	@Test
	public void registerAdminInvalidInputs() throws Exception 
	{
		expectedException.expect(TransactionSystemException.class);
		user.setEmailId("marcusmarcus.com");
		when(userDAO.checkAvailabilityOfEmailId("marcusmarcus.com")).thenReturn(true);
		when(userDAO.registerUser(user)).thenThrow(TransactionSystemException.class);
		userService.registerUser(user);
	}
	
	@Test
	public void registerAdminTakenEmail() throws Exception 
	{
		expectedException.expect(EmailUnavailableException.class);
		
		when(userDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(false);
		userService.registerUser(user);
	}
	

}
