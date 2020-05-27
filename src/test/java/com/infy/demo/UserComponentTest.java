package com.infy.demo;

import java.nio.charset.Charset;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.demo.api.UserAPI;
import com.infy.demo.api.UserAPIImpl;
import com.infy.demo.dao.UserDAO;
import com.infy.demo.dao.UserDAOImpl;
import com.infy.demo.entity.UserEntity;
import com.infy.demo.model.User;
import com.infy.demo.model.UserType;
import com.infy.demo.service.UserService;
import com.infy.demo.service.UserServiceImpl;

@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserAPIImpl.class,UserDAOImpl.class,UserServiceImpl.class,EntityManager.class})
public class UserComponentTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
	MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private UserAPI userAPI;
	
	@Autowired
    private UserDAO userDAO;
    
	@Autowired
	private UserService userService;
	
	@Mock
	private EntityManager entityManager; 
	
	private UserEntity dummyEntity;
	
	private User user;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(UserAPI.class).build();
		
        dummyEntity = new UserEntity();
        dummyEntity.setEmailId("email");
        dummyEntity.setFirstName("name");
        dummyEntity.setLastName("name");
        dummyEntity.setPassword("password");
        dummyEntity.setPhoneNumber("phone");
        dummyEntity.setUserType(UserType.ADMIN);
        
    	user = new User();
		user.setEmailId("marcus@marcus.com");
		user.setPassword("Me@123");
		user.setFirstName("marcus");
		user.setLastName("marcus");
		user.setPhoneNumber("1112225545");
		user.setUserType(UserType.ADMIN);
	}
	
	@Test
	public void registeruserValidTest() throws Exception {
	
	String json = new ObjectMapper().writeValueAsString(user);
	Mockito.when(entityManager.find(UserEntity.class, "marcus@marcus.com")).thenReturn(dummyEntity);
	mockMvc.perform(MockMvcRequestBuilders.post("/User/Register").contentType(APPLICATION_JSON_UTF8).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
//	@Test
//	public void registeruserInvalidEmailTest() throws Exception {
//	User user = new User();
//	user.setEmailId("marcusmarcus.com");
//	user.setFirstName("Marcus");
//	user.setLastName("Sullins");
//	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	user.setPassword(passwordEncoder.encode("Me@123"));
//	user.setPhoneNumber("5552225555");
//	
//	String json = new ObjectMapper().writeValueAsString(user);
//	
//	Mockito.when(userService.registerUser(user)).thenThrow(TransactionSystemException.class);
//	mockMvc.perform(MockMvcRequestBuilders.post("/User/Register").contentType(APPLICATION_JSON_UTF8).content(json))
//			.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
//	}
//	
//	@Test
//	public void registerUserInvalidTest() throws Exception {
//	User user = new User();
//	user.setEmailId("marcus@marcus.com");
//	user.setFirstName("Marcus");
//	user.setLastName("Sullins");
//	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	user.setPassword(passwordEncoder.encode("Me@123"));
//	user.setPhoneNumber("5552225555");
//	
//	String json = new ObjectMapper().writeValueAsString(user);
//	
//	Mockito.when(userService.registerUser(user)).thenThrow(EmailUnavailableException.class);
//	mockMvc.perform(MockMvcRequestBuilders.post("/User/Register").contentType(APPLICATION_JSON_UTF8).content(json))
//			.andExpect(MockMvcResultMatchers.status().isBadRequest());
//	}
}
