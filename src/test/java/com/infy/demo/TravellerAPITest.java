package com.infy.demo;

import java.nio.charset.Charset;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.demo.api.TravellerAPI;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Traveller;
import com.infy.demo.service.TravellerService;
import com.infy.demo.utility.HashingUtility;

@RunWith(SpringRunner.class)
public class TravellerAPITest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private MockMvc mockMvc;

	@InjectMocks
	private TravellerAPI travellerAPI;

	@Mock
	TravellerService travellerService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(travellerAPI).build();
	}

	@Test
	public void registerTravellerValidTest() throws Exception {
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setFirstName("Marcus");
		traveller.setLastName("Sullins");
		traveller.setPassword(HashingUtility.getHash("Me@123"));
		traveller.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(traveller);

		Mockito.when(travellerService.registerTraveller(traveller)).thenReturn("marcus@marcus.com");
		mockMvc.perform(
				MockMvcRequestBuilders.post("/Traveller/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void registerTravellerInvalidTest() throws Exception {
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setFirstName("Marcus");
		traveller.setLastName("Sullins");
		traveller.setPassword(HashingUtility.getHash("Me@123"));
		traveller.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(traveller);

		Mockito.when(travellerService.registerTraveller(traveller)).thenThrow(EmailUnavailableException.class);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/Traveller/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void loginTravellerValidTest() throws Exception {
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setFirstName("Marcus");
		traveller.setLastName("Sullins");
		traveller.setPassword(HashingUtility.getHash("Me@123"));
		traveller.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(traveller);

		Mockito.when(travellerService.loginTraveller(traveller)).thenReturn(traveller);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/Traveller/Login").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void loginTravellerInvalidTest() throws Exception {
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setFirstName("Marcus");
		traveller.setLastName("Sullins");
		traveller.setPassword(HashingUtility.getHash("Me@123"));
		traveller.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(traveller);

		Mockito.when(travellerService.loginTraveller(traveller)).thenThrow(InvalidCredentialsException.class);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/Traveller/Login").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void loginTravellerDoesNotExistTest() throws Exception {
		Traveller traveller = new Traveller();
		traveller.setEmailId("marcus@marcus.com");
		traveller.setFirstName("Marcus");
		traveller.setLastName("Sullins");
		traveller.setPassword(HashingUtility.getHash("Me@123"));
		traveller.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(traveller);

		Mockito.when(travellerService.loginTraveller(Mockito.any(Traveller.class)))
				.thenThrow(UserNotFoundException.class);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/Traveller/Login").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
