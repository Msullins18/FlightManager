package com.infy.demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.infy.demo.model.Traveller;
import com.infy.demo.service.TravellerService;

@RestController
@RequestMapping("Traveller")
public class TravellerAPI {
	
	static Logger logger = LogManager.getLogger(AdminAPI.class.getName());
	
	@Autowired
	TravellerService travellerService;
	
//	@Autowired
//	Environment environment;
	
	@PostMapping(value = "Login")
	public ResponseEntity<Traveller> loginTraveller(@RequestBody Traveller traveller)
	{
		try {
			logger.info("TRAVELLER TRYING TO LOGIN WITH EMAIL: " + traveller.getEmailId());
			Traveller authenticated = travellerService.loginTraveller(traveller);
			ResponseEntity<Traveller> re = new ResponseEntity<Traveller>(authenticated,HttpStatus.OK);
			logger.info("TRAVELLER LOGGED IN SUCCESSFULLY WITH EMAIL: "+ authenticated.getEmailId());
			return re;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.toString());
		}
	}
	
	@PostMapping(value = "Register")
	public ResponseEntity<String> registerTraveller(@RequestBody Traveller traveller)
	{
		try {
			logger.info("TRAVELLER TRYING TO REGISTER WITH EMAIL: " + traveller.getEmailId());
			String registered = travellerService.registerTraveller(traveller);
			ResponseEntity<String> re = new ResponseEntity<String>(registered,HttpStatus.OK);
			logger.info("TRAVELLER REGISTERED SUCCESSFULLY WITH EMAIL: "+ registered);
			return re;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.toString());
		}
	}
}
