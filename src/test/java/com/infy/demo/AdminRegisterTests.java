package com.infy.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.infy.demo.dao.UserDAO;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.model.User;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;

@RunWith(SpringRunner.class)
public class AdminRegisterTests {

	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	UserDAO adminDAO;
	@InjectMocks
	UserService adminService = new UserServiceImpl();
	
	@Test
	public void registerAdminValidInputs() throws Exception 
	{
		User admin = new User();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@123");
		admin.setFirstName("marcus");
		admin.setLastName("marcus");
		admin.setPhoneNumber("1112225545");
		
		when(adminDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(true);
		when(adminDAO.registerAdmin(admin)).thenReturn("marcus@marcus.com");
		String registered = adminService.registerAdmin(admin);
		assertEquals("marcus@marcus.com", registered);
	}
	
	@Test
	public void registerAdminInvalidInputs() throws Exception 
	{
		expectedException.expect(TransactionSystemException.class);
		User admin = new User();
		admin.setEmailId("marcusmarcus.com");
		admin.setPassword("Me@123");
		admin.setFirstName("marcus");
		admin.setLastName("marcus");
		admin.setPhoneNumber("1112225545");
		
		when(adminDAO.checkAvailabilityOfEmailId("marcusmarcus.com")).thenReturn(true);
		when(adminDAO.registerAdmin(admin)).thenThrow(TransactionSystemException.class);
		String registered = adminService.registerAdmin(admin);
	}
	
	@Test
	public void registerAdminTakenEmail() throws Exception 
	{
		expectedException.expect(EmailUnavailableException.class);
		User admin = new User();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@123");
		admin.setFirstName("marcus");
		admin.setLastName("marcus");
		admin.setPhoneNumber("1112225545");
		
		when(adminDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(false);
		String registered = adminService.registerAdmin(admin);
	}
}
