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
import com.infy.demo.api.LoginAPI;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.User;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;
import com.infy.demo.utility.HashingUtility;

@RunWith(SpringRunner.class)
public class LoginAPITest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;

	@InjectMocks
	private LoginAPI loginAPI;

   	@Mock
	UserService userService = new UserServiceImpl();
	
   	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginAPI).build();
	}


//	@Test
//	public void loginUserValidTest() throws Exception {
//		User user = new User();
//		user.setEmailId("marcus@marcus.com");
//		user.setFirstName("Marcus");
//		user.setLastName("Sullins");
//		user.setPassword(HashingUtility.getHash("Me@123"));
//		user.setPhoneNumber("5552225555");
//
//		String json = new ObjectMapper().writeValueAsString(user);
//
//		Mockito.when(userService.loginUser(user)).thenReturn(user);
//		mockMvc.perform(MockMvcRequestBuilders.post("/User/Login").contentType(APPLICATION_JSON_UTF8).content(json))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}
//	
//
//
//	@Test
//	public void loginUserInvalidTest() throws Exception {
//		User user = new User();
//		user.setEmailId("marcus@marcus.com");
//		user.setFirstName("Marcus");
//		user.setLastName("Sullins");
//		user.setPassword(HashingUtility.getHash("Me@123"));
//		user.setPhoneNumber("5552225555");
//
//		String json = new ObjectMapper().writeValueAsString(user);
//
//		Mockito.when(userService.loginUser(user)).thenThrow(InvalidCredentialsException.class);
//		mockMvc.perform(MockMvcRequestBuilders.post("/User/Login").contentType(APPLICATION_JSON_UTF8).content(json))
//				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
//	}
//	
//
//	@Test
//	public void loginUserDoesNotExistTest() throws Exception {
//		User user = new User();
//		user.setEmailId("marcus@marcus.com");
//		user.setFirstName("Marcus");
//		user.setLastName("Sullins");
//		user.setPassword(HashingUtility.getHash("Me@123"));
//		user.setPhoneNumber("5552225555");
//
//		String json = new ObjectMapper().writeValueAsString(user);
//
//		Mockito.when(userService.loginUser(Mockito.any(User.class))).thenThrow(UserNotFoundException.class);
//		mockMvc.perform(MockMvcRequestBuilders.post("/User/Login").contentType(APPLICATION_JSON_UTF8).content(json))
//				.andExpect(MockMvcResultMatchers.status().isNotFound());
//	}

}
