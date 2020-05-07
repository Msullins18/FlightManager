package com.infy.demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Traveller;
import com.infy.demo.service.TravellerService;

<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> 49d26f658d96bf4867a9cca40964036672efcc45
@CrossOrigin
@RestController
@RequestMapping("Traveller")
@Slf4j
public class TravellerAPI {
<<<<<<< HEAD

	static Logger logger = LogManager.getLogger(AdminAPI.class.getName());
=======
	
>>>>>>> 49d26f658d96bf4867a9cca40964036672efcc45

	@Autowired
	TravellerService travellerService;

	@PostMapping(value = "Login")
<<<<<<< HEAD
	public ResponseEntity<Traveller> loginTraveller(@RequestBody Traveller traveller) throws Exception {
		logger.info("TRAVELLER TRYING TO LOGIN WITH EMAIL: " + traveller.getEmailId());
		Traveller authenticated = travellerService.loginTraveller(traveller);
		ResponseEntity<Traveller> re = new ResponseEntity<Traveller>(authenticated, HttpStatus.OK);
		logger.info("TRAVELLER LOGGED IN SUCCESSFULLY WITH EMAIL: " + authenticated.getEmailId());
=======
	public ResponseEntity<Traveller> loginTraveller(@RequestBody Traveller traveller) throws Exception
	{
		log.info("TRAVELLER TRYING TO LOGIN WITH EMAIL: " + traveller.getEmailId());
		Traveller authenticated = travellerService.loginTraveller(traveller);
		ResponseEntity<Traveller> re = new ResponseEntity<Traveller>(authenticated,HttpStatus.OK);
		log.info("TRAVELLER LOGGED IN SUCCESSFULLY WITH EMAIL: "+ authenticated.getEmailId());
>>>>>>> 49d26f658d96bf4867a9cca40964036672efcc45
		return re;
	}

	@PostMapping(value = "Register")
<<<<<<< HEAD
	public ResponseEntity<String> registerTraveller(@RequestBody Traveller traveller) throws Exception {
		logger.info("TRAVELLER TRYING TO REGISTER WITH EMAIL: " + traveller.getEmailId());
		String registered = travellerService.registerTraveller(traveller);
		ResponseEntity<String> re = new ResponseEntity<String>(registered, HttpStatus.OK);
		logger.info("TRAVELLER REGISTERED SUCCESSFULLY WITH EMAIL: " + registered);
=======
	public ResponseEntity<String> registerTraveller(@RequestBody Traveller traveller) throws Exception
	{
		log.info("TRAVELLER TRYING TO REGISTER WITH EMAIL: " + traveller.getEmailId());
		String registered = travellerService.registerTraveller(traveller);
		ResponseEntity<String> re = new ResponseEntity<String>(registered,HttpStatus.OK);
		log.info("TRAVELLER REGISTERED SUCCESSFULLY WITH EMAIL: "+ registered);
>>>>>>> 49d26f658d96bf4867a9cca40964036672efcc45
		return re;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleException(UserNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<Object> handleException(InvalidCredentialsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(EmailUnavailableException.class)
	public ResponseEntity<Object> handleException(EmailUnavailableException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<Object> handleException(TransactionSystemException e) {
		return new ResponseEntity<>("Invalid inputs! Please try again.", HttpStatus.BAD_REQUEST);
	}

}
