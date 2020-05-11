package com.infy.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.infy.demo.model.User;
import com.infy.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping("User")
@Slf4j
public class UserAPI {
	
	@Autowired
	UserService userService;

	@Autowired
	private Environment environment;
	
//	@PostMapping(value = "Login")
//	public ResponseEntity<user> loginuser(@RequestBody user user) throws Exception
//	{
//		log.info("user TRYING TO LOGIN WITH EMAIL: " + user.getEmailId());
//		user authenticated = userService.loginuser(user);
//		ResponseEntity<user> re = new ResponseEntity<user>(authenticated,HttpStatus.OK);
//		log.info("user LOGGED IN SUCCESSFULLY WITH EMAIL: "+ authenticated.getEmailId());
//		return re;
//	}
	
	@PostMapping(value = "Register")
	public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception
	{
		log.info("USER TRYING TO REGISTER WITH EMAIL: "+ user.getEmailId());
		String registered = userService.registerUser(user);
		ResponseEntity<String> re = new ResponseEntity<String>(registered,HttpStatus.OK);
		log.info("USER SUCCESSFULLY REGISTERED WITH EMAIL: "+ registered);
		return re;
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
    	e.printStackTrace();
    	return new ResponseEntity<>("Invalid inputs! Please try again.", HttpStatus.BAD_REQUEST);
    }

}
