package com.infy.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.infy.demo.dao.UserDAO;
import com.infy.demo.dao.UserDAOImpl;
import com.infy.demo.entity.UserEntity;
import com.infy.demo.model.User;
import com.infy.demo.model.UserType;

public class UserDAOTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@Mock
	private EntityManager entityManager; 

    @InjectMocks
    private UserDAO userDAO = new UserDAOImpl();

    private UserEntity dummyEntity;
    
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
        dummyEntity = new UserEntity();
        dummyEntity.setEmailId("email");
        dummyEntity.setFirstName("name");
        dummyEntity.setLastName("name");
        dummyEntity.setPassword("password");
        dummyEntity.setPhoneNumber("phone");
        dummyEntity.setUserType(UserType.ADMIN);
    }

	
	@Test
	public void getUserByEmailIdTest()
	{
		UserEntity user = new UserEntity();
		Mockito.when(entityManager.find(UserEntity.class, "Jack@infosys.com")).thenReturn(dummyEntity);
		user = userDAO.getUserByEmailId("Jack@infosys.com"); 
		assertNotNull(user);
	}
	@Test
	public void getUserByEmailIdFailTest()
	{
		expectedException.expect(UsernameNotFoundException.class);
		UserEntity user = new UserEntity();
		Mockito.when(entityManager.find(UserEntity.class, "Jack@infosys.com")).thenReturn(null);
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
		Mockito.doNothing().when(entityManager).persist(Mockito.any(UserEntity.class));
		userDAO.registerUser(user);
	}
	@Test
	public void checkAvailabilityOfEmailIdTest()
	{
		Mockito.when(entityManager.find(UserEntity.class, "Jack@infosys.com")).thenReturn(null);
		boolean available = userDAO.checkAvailabilityOfEmailId("Jack@infosys.com");
		assertTrue(available);
	}
	
	@Test
	public void checkAvailabilityOfEmailIdFailTest()
	{
		Mockito.when(entityManager.find(UserEntity.class, "Jack@infosys.com")).thenReturn(dummyEntity);
		boolean available = userDAO.checkAvailabilityOfEmailId("Jack@infosys.com");
		assertFalse(available);
	}
}
