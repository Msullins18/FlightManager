package com.infy.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionSystemException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.demo.api.AdminAPI;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;
import com.infy.demo.service.AdminService;
import com.infy.demo.utility.HashingUtility;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class AdminAPITest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;

	@InjectMocks
	private AdminAPI adminAPI;

	@Mock
	AdminService adminService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(adminAPI).build();
	}

	@Test
	public void registerAdminValidTest() throws Exception {
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setFirstName("Marcus");
		admin.setLastName("Sullins");
		admin.setPassword(HashingUtility.getHash("Me@123"));
		admin.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(admin);

		Mockito.when(adminService.registerAdmin(admin)).thenReturn("marcus@marcus.com");
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void registerAdminInvalidTest() throws Exception {
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setFirstName("Marcus");
		admin.setLastName("Sullins");
		admin.setPassword(HashingUtility.getHash("Me@123"));
		admin.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(admin);

		Mockito.when(adminService.registerAdmin(admin)).thenThrow(EmailUnavailableException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void loginAdminValidTest() throws Exception {
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setFirstName("Marcus");
		admin.setLastName("Sullins");
		admin.setPassword(HashingUtility.getHash("Me@123"));
		admin.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(admin);

		Mockito.when(adminService.loginAdmin(admin)).thenReturn(admin);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/Login").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void loginAdminInvalidTest() throws Exception {
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setFirstName("Marcus");
		admin.setLastName("Sullins");
		admin.setPassword(HashingUtility.getHash("Me@123"));
		admin.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(admin);

		Mockito.when(adminService.loginAdmin(admin)).thenThrow(InvalidCredentialsException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/Login").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void loginAdminDoesNotExistTest() throws Exception {
		Admin admin = new Admin();
		admin.setEmailId("marcus@marcus.com");
		admin.setFirstName("Marcus");
		admin.setLastName("Sullins");
		admin.setPassword(HashingUtility.getHash("Me@123"));
		admin.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(admin);

		Mockito.when(adminService.loginAdmin(Mockito.any(Admin.class))).thenThrow(UserNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/Login").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void registerAdminInvalidEmailTest() throws Exception {
		Admin admin = new Admin();
		admin.setEmailId("marcusmarcus.com");
		admin.setFirstName("Marcus");
		admin.setLastName("Sullins");
		admin.setPassword(HashingUtility.getHash("Me@123"));
		admin.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(admin);

		Mockito.when(adminService.registerAdmin(admin)).thenThrow(TransactionSystemException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void addAirportValidTest() throws Exception {
		Airport airport = new Airport();
		airport.setAirportName("DFW");
		airport.setCity("Dallas");

		String json = new ObjectMapper().writeValueAsString(airport);

		Mockito.when(adminService.addAirport(airport)).thenReturn(1001);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/addAirport").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteAirportValidTest() throws Exception {
		Mockito.when(adminService.deleteAirport(1001)).thenReturn(1001);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin/deleteAirport/{1001}",1001))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getAirportsValidTest() throws Exception {
		Airport airport = new Airport();
		airport.setAirportName("DFW");
		airport.setCity("Dallas");
		List<Airport> lis = new ArrayList<>();
		lis.add(airport);
		Mockito.when(adminService.getAirports()).thenReturn(lis);
		mockMvc.perform(MockMvcRequestBuilders.get("/Admin/getAirports"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
