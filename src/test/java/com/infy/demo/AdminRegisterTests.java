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

import com.infy.demo.dao.AdminDAO;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.model.Admin;
import com.infy.demo.service.AdminService;
import com.infy.demo.service.AdminServiceImpl;

@RunWith(SpringRunner.class)
public class AdminRegisterTests {

	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
   	@Mock
	AdminDAO adminDAO;
	@InjectMocks
	AdminService adminService = new AdminServiceImpl();
	
	@Test
	public void registerAdminValidInputs() throws Exception 
	{
		Admin admin = new Admin();
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
		Admin admin = new Admin();
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
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setPassword("Me@123");
		admin.setFirstName("marcus");
		admin.setLastName("marcus");
		admin.setPhoneNumber("1112225545");
		
		when(adminDAO.checkAvailabilityOfEmailId("marcus@marcus.com")).thenReturn(false);
		String registered = adminService.registerAdmin(admin);
	}
}
