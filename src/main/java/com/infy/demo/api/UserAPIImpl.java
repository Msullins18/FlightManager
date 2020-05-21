package com.infy.demo.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.infy.demo.model.User;
import com.infy.demo.service.UserService;
import com.infy.demo.utility.JWTUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping("User")
@Api(value = "UserAPI",tags={ "User"},description = "User calls")
public class UserAPIImpl implements UserAPI {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private JWTUtility jwtUtility;
    
    @Autowired
    private AuthenticationManager authenticationManager;
	
    @Override
	@PostMapping(value = "Register",produces = { "application/json" })
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user)
	{
		String registered = userService.registerUser(user);
		ResponseEntity<String> re = new ResponseEntity<String>(registered,HttpStatus.OK);
		return re;
	}
    
    @Override
	@PostMapping(value = "Login")
	public String loginUser(@Valid @RequestBody User user)
	{
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmailId(),user.getPassword()));
		return jwtUtility.generateToken(user.getEmailId());
	}

}
