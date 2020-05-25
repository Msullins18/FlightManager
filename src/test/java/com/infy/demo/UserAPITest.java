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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionSystemException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.demo.api.UserAPIImpl;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.model.User;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;


public class UserAPITest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;

	@InjectMocks
	private UserAPIImpl userAPI;

   	@Mock
	UserService userService = new UserServiceImpl();
	
   	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userAPI).build();
	}

	@Test
	public void registeruserValidTest() throws Exception {
		User user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setFirstName("Marcus");
		user.setLastName("Sullins");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("Me@123"));
		user.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(user);

		Mockito.when(userService.registerUser(user)).thenReturn("marcus@marcus.com");
		mockMvc.perform(MockMvcRequestBuilders.post("/User/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	@Test
	public void registeruserInvalidEmailTest() throws Exception {
		User user = new User();
		user.setEmailId("marcusmarcus.com");
		user.setFirstName("Marcus");
		user.setLastName("Sullins");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("Me@123"));
		user.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(user);

		Mockito.when(userService.registerUser(user)).thenThrow(TransactionSystemException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/User/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
	}
	
	@Test
	public void registerUserInvalidTest() throws Exception {
		User user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setFirstName("Marcus");
		user.setLastName("Sullins");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("Me@123"));
		user.setPhoneNumber("5552225555");

		String json = new ObjectMapper().writeValueAsString(user);

		Mockito.when(userService.registerUser(user)).thenThrow(EmailUnavailableException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/User/Register").contentType(APPLICATION_JSON_UTF8).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	

}
