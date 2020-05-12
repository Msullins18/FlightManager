package com.infy.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.infy.demo.dao.UserDAO;
import com.infy.demo.model.User;
import com.infy.demo.model.UserType;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional()
@Rollback
public class UserDAOTest {
	
	@Autowired
	UserDAO userDAO;
	
	@Test
	public void getUserByEmailIdTest()
	{
		User user = new User();
		user = userDAO.getUserByEmailId("Jack@infosys.com"); 
		assertNotNull(user);
	}
	@Test
	public void registerUserTest()
	{
		User user = new User();
		user.setEmailId("Jack1@infosys.com");
		user.setFirstName("Jack");
		user.setLastName("Jack");
		user.setPassword("Me@123");
		user.setPhoneNumber("1112224545");
		user.setUserType(UserType.ADMIN);
		
		String emailId = userDAO.registerUser(user);
		assertEquals("Jack1@infosys.com", emailId);
	}
	@Test
	public void checkAvailabilityOfEmailIdTest()
	{
		boolean available = userDAO.checkAvailabilityOfEmailId("Jack@infosys.com");
		assertFalse(available);
	}
}
