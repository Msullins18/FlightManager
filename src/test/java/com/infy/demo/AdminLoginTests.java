package com.infy.demo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.infy.demo.dao.UserDAO;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.User;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;
import com.infy.demo.utility.HashingUtility;

@RunWith(SpringRunner.class)
public class AdminLoginTests {

	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	UserDAO adminDAO;
	@InjectMocks
	UserService adminService = new UserServiceImpl();
	
	@Test
	public void authenticateAdminLoginValidCredentials() throws Exception 
	{
		User admin = new User();
		User returned = new User();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@123");
		
		returned.setEmailId("marcus@marcus.com");
		returned.setPassword(HashingUtility.getHash("Me@123"));
		
		when(adminDAO.getPasswordOfAdmin("marcus@marcus.com")).thenReturn(Optional.of(HashingUtility.getHash("Me@123")));
		when(adminDAO.getAdminByEmailId("marcus@marcus.com")).thenReturn(returned);
		
		User adminFromDAO = adminService.loginAdmin(admin);
		assertEquals("marcus@marcus.com", adminFromDAO.getEmailId());
	}
	
	@Test
	public void authenticateAdminLoginInvalidCredentials() throws Exception 
	{
		expectedException.expect(InvalidCredentialsException.class);
		User admin = new User();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@12");
		
		when(adminDAO.getPasswordOfAdmin("marcus@marcus.com")).thenReturn(Optional.of(HashingUtility.getHash("Me@123")));
		
		User adminFromDAO = adminService.loginAdmin(admin);
		assertEquals(null, adminFromDAO);
	}
	
	@Test
	public void authenticateAdminLoginUserNotFound() throws Exception 
	{
		expectedException.expect(UserNotFoundException.class);
		User admin = new User();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@123");
		Optional <String> empty = Optional.empty(); 
		when(adminDAO.getPasswordOfAdmin("marcus@marcus.com")).thenReturn(empty);
		
		User adminFromDAO = adminService.loginAdmin(admin);
		assertEquals(null, adminFromDAO);
	}
}
