package com.infy.demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.demo.exceptions.EmailUnavailableException;
import com.infy.demo.exceptions.InvalidCredentialsException;
import com.infy.demo.exceptions.InvalidEmailException;
import com.infy.demo.exceptions.UserNotFoundException;
import com.infy.demo.model.Admin;
import com.infy.demo.model.Airport;
import com.infy.demo.service.AdminService;
@CrossOrigin
@RestController
@RequestMapping("Admin")
public class AdminAPI {
	
	static Logger logger = LogManager.getLogger(AdminAPI.class.getName());
	
	@Autowired
	AdminService adminService;

	@Autowired
	private Environment environment;
	
	@PostMapping(value = "Login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) throws Exception
	{
		logger.info("ADMIN TRYING TO LOGIN WITH EMAIL: " + admin.getEmailId());
		Admin authenticated = adminService.loginAdmin(admin);
		ResponseEntity<Admin> re = new ResponseEntity<Admin>(authenticated,HttpStatus.OK);
		logger.info("ADMIN LOGGED IN SUCCESSFULLY WITH EMAIL: "+ authenticated.getEmailId());
		return re;
	}
	
	@PostMapping(value = "Register")
	public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) throws Exception
	{
		logger.info("ADMIN TRYING TO REGISTER WITH EMAIL: "+ admin.getEmailId());
		String registered = adminService.registerAdmin(admin);
		ResponseEntity<String> re = new ResponseEntity<String>(registered,HttpStatus.OK);
		logger.info("ADMIN SUCCESSFULLY REGISTERED WITH EMAIL: "+ registered);
		return re;
	}
	
	@PostMapping(value = "addAirport/")
	public ResponseEntity<String> addAirport(@RequestBody Airport airport) throws Exception {
		try
		{
			adminService.addAirport(airport);
			
			String message = environment.getProperty("AdminAPI.AIRPORT_ADDED_TO_AIRPORT");
			logger.info(message + adminService.addAirport(airport));
			return new ResponseEntity<String>(message, HttpStatus.OK);
			
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "deleteAirport/{airportId}")
	public ResponseEntity<String> deleteAirport(@PathVariable("airportId") Integer airportId) throws Exception{
		
		try
		{
			adminService.deleteAirport(airportId);
			
			String message = environment.getProperty("Airport Successfully deleted");
			
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch (Exception e) {
			
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
    
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Object> handleException(InvalidEmailException  e) {
    	//throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
