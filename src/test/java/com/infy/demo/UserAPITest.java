package com.infy.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import com.infy.demo.api.UserAPIImpl;
import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.model.User;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;


public class UserAPITest {

	@InjectMocks
	private UserAPIImpl userAPI;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
   	@Mock
	UserService userService = new UserServiceImpl();
	
   	private User user;
   	
   	@Before
	public void setup() {
   		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setFirstName("Marcus");
		user.setLastName("Sullins");
		user.setPassword("Me@123");
		user.setPhoneNumber("5552225555");
	}

	@Test
	public void registeruserValidTest() throws Exception {
		Mockito.when(userService.registerUser(user)).thenReturn("marcus@marcus.com");
		String id = userAPI.registerUser(user).getBody();
		HttpStatus status = userAPI.registerUser(user).getStatusCode();
		assertEquals("marcus@marcus.com", id);
		assertEquals(HttpStatus.OK, status);
	}

	
	@Test
	public void registeruserInvalidEmailTest() throws Exception {
		expectedException.expect(Exception.class);
		user.setEmailId("marcusmarcus.com");
		Mockito.when(userService.registerUser(user)).thenThrow(Exception.class);
		HttpStatus status = userAPI.registerUser(user).getStatusCode();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, status);
	}
	
	@Test
	public void registerUserInvalidTest() throws Exception {
		expectedException.expect(EmailUnavailableException.class);
		user.setEmailId("marcus@marcus.com");
		Mockito.when(userService.registerUser(user)).thenThrow(EmailUnavailableException.class);
		HttpStatus status = userAPI.registerUser(user).getStatusCode();
		assertEquals(HttpStatus.BAD_REQUEST, status);
		
	}
}
