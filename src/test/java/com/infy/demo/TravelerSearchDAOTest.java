package com.infy.demo;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import com.infy.demo.dao.TravelerSearchDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class TravelerSearchDAOTest {
	@Autowired
	private TravelerSearchDAO travelerSearchDAO;
	
	@Test
	public void getDestinationValid(){
		List<String> destinationList = new ArrayList<>();
		destinationList.add("New York");
		Assert.assertNotNull(travelerSearchDAO.getAllDestinations());
	}
	

}
