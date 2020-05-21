package com.infy.demo.api;

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
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private JWTUtility jwtUtility;
    
    @Autowired
    private AuthenticationManager authenticationManager;
	
    @ApiOperation(value = "Register user for application", nickname = "registerUser", notes = "Returns email of user upon successful registration", response = String.class, tags={ "User"})
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = String.class),
        @ApiResponse(code = 400, message = ""),
        @ApiResponse(code = 500, message = "") })
	@PostMapping(value = "Register",produces = { "application/json" })
	public ResponseEntity<String> registerUser(@RequestBody User user)
	{
		String registered = userService.registerUser(user);
		ResponseEntity<String> re = new ResponseEntity<String>(registered,HttpStatus.OK);
		return re;
	}
    
    @ApiOperation(value = "Submit user credentials for authorization", nickname = "loginUser", notes = "Returns a JSON Web Token for use in other api calls", response = String.class, tags={ "login", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = String.class),
        @ApiResponse(code = 403, message = ""),
        @ApiResponse(code = 500, message = "") })
	@PostMapping(value = "Login")
	public String loginUser(@ApiParam(value = "Credentials of User to authenticate",required=true) @RequestBody User user)
	{
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmailId(),user.getPassword()));
		return jwtUtility.generateToken(user.getEmailId());
	}

}
