package com.infy.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infy.demo.model.User;
import com.infy.demo.utility.HashingUtility;
import com.infy.demo.utility.JWTUtility;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("Login")
@Slf4j
public class LoginAPI {
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
		
	@PostMapping(value = "/")
	public String loginUser(@RequestBody User user) throws Exception
	{
		log.info("USER TRYING TO LOGIN WITH EMAIL: " + user.getEmailId());
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmailId(),user.getPassword()));
		log.info("USER LOGGED IN SUCCESSFULLY WITH EMAIL: "+ user.getEmailId());
		return jwtUtility.generateToken(user.getEmailId());
	}
}
