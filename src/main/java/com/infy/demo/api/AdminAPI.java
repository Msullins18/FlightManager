package com.infy.demo.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;
import com.infy.demo.service.AdminService;
<<<<<<< HEAD
@Controller
=======

import lombok.extern.slf4j.Slf4j;

>>>>>>> 49d26f658d96bf4867a9cca40964036672efcc45
@CrossOrigin
@RestController
@RequestMapping("Admin")
@Slf4j
public class AdminAPI {
	
	@Autowired
	AdminService adminService;

	@Autowired
	private Environment environment;
	
	@PostMapping(value = "Login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) throws Exception
	{
		log.info("ADMIN TRYING TO LOGIN WITH EMAIL: " + admin.getEmailId());
		Admin authenticated = adminService.loginAdmin(admin);
		ResponseEntity<Admin> re = new ResponseEntity<Admin>(authenticated,HttpStatus.OK);
		log.info("ADMIN LOGGED IN SUCCESSFULLY WITH EMAIL: "+ authenticated.getEmailId());
		return re;
	}
	
	@PostMapping(value = "Register")
	public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) throws Exception
	{
		log.info("ADMIN TRYING TO REGISTER WITH EMAIL: "+ admin.getEmailId());
		String registered = adminService.registerAdmin(admin);
		System.out.println(registered);
		ResponseEntity<String> re = new ResponseEntity<String>(registered,HttpStatus.OK);
		log.info("ADMIN SUCCESSFULLY REGISTERED WITH EMAIL: "+ registered);
		return re;
	}
	
	@PostMapping(value = "addAirport")
	public ResponseEntity<String> addAirport(@RequestBody Airport airport) throws Exception {
		try
		{
			Integer id = adminService.addAirport(airport);
			String message = "The following Airport has been successfully added with Airport Id: " + id;
			log.info(message);
			return new ResponseEntity<String>(message, HttpStatus.OK);
			
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "deleteAirport/{airportId}")
	public ResponseEntity<Integer> deleteAirport(@PathVariable("airportId") Integer airportId) throws Exception{
		
		try
		{
			Integer result = adminService.deleteAirport(airportId);
			log.info("The following Airport has been successfully deleted with Airport Id:" + result);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value = "getAirports")
	public ResponseEntity<List<Airport>> getAirports() throws Exception {
		List<Airport> list = null;
		try {
			list = adminService.getAirports();
			ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(list, HttpStatus.OK);
			return response;
		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}

	}
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleException(UserNotFoundException  e) {
    	//throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleException(InvalidCredentialsException  e) {
    	//throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(EmailUnavailableException.class)
    public ResponseEntity<Object> handleException(EmailUnavailableException  e) {
    	//throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleException(TransactionSystemException  e) {
    	//throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
    	return new ResponseEntity<>("Invalid inputs! Please try again.", HttpStatus.BAD_REQUEST);
    }

}
