package com.infy.demo.api;

import javax.validation.Valid;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


public interface UserAPI {
	

    @ApiOperation(value = "Register user for application", nickname = "registerUser", notes = "Returns email of user upon successful registration", response = String.class, tags={ "User"})
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = String.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Some unknown error occurred!") })
	public abstract ResponseEntity<String> registerUser(@ApiParam(value = "Info of User Registering",required=true) @Valid @RequestBody User user);

    @ApiOperation(value = "Submit user credentials for authorization", nickname = "loginUser", notes = "Returns a JSON Web Token for use in other api calls", response = String.class, tags={ "User", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = String.class),
        @ApiResponse(code = 403, message = ""),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Some unknown error occurred!") })
	public abstract String loginUser(@ApiParam(value = "Credentials of User to authenticate",required=true) @Valid @RequestBody User user);
}
