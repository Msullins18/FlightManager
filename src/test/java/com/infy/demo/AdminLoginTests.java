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

import com.infy.demo.dao.AdminDAO;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Admin;
import com.infy.demo.service.AdminService;
import com.infy.demo.service.AdminServiceImpl;
import com.infy.demo.utility.HashingUtility;

@RunWith(SpringRunner.class)
public class AdminLoginTests {

	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	AdminDAO adminDAO;
	@InjectMocks
	AdminService adminService = new AdminServiceImpl();
	
	@Test
	public void authenticateAdminLoginValidCredentials() throws Exception 
	{
		Admin admin = new Admin();
		Admin returned = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@123");
		
		returned.setEmailId("marcus@marcus.com");
		returned.setPassword(HashingUtility.getHash("Me@123"));
		
		when(adminDAO.getPasswordOfAdmin("marcus@marcus.com")).thenReturn(HashingUtility.getHash("Me@123"));
		when(adminDAO.getAdminByEmailId("marcus@marcus.com")).thenReturn(returned);
		
		Admin adminFromDAO = adminService.loginAdmin(admin);
		assertEquals("marcus@marcus.com", adminFromDAO.getEmailId());
	}
	
	@Test
	public void authenticateAdminLoginInvalidCredentials() throws Exception 
	{
		expectedException.expect(InvalidCredentialsException.class);
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@12");
		
		when(adminDAO.getPasswordOfAdmin("marcus@marcus.com")).thenReturn(HashingUtility.getHash("Me@123"));
		
		Admin adminFromDAO = adminService.loginAdmin(admin);
		assertEquals(null, adminFromDAO);
	}
	
	@Test
	public void authenticateAdminLoginUserNotFound() throws Exception 
	{
		expectedException.expect(UserNotFoundException.class);
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@123");
		
		when(adminDAO.getPasswordOfAdmin("marcus@marcus.com")).thenReturn(null);
		
		Admin adminFromDAO = adminService.loginAdmin(admin);
		assertEquals(null, adminFromDAO);
	}
}
