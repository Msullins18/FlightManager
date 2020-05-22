package com.infy.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlightManagerExceptionHandler {
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException  e) {
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
	
    @ExceptionHandler(EmailUnavailableException.class)
    public ResponseEntity<Object> handleException(EmailUnavailableException  e) {
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleException(TransactionSystemException  e) {
    	return new ResponseEntity<>("Invalid inputs! Please try again.", HttpStatus.BAD_REQUEST);
    }
    
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleException(FlightNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoFlightsAvailableException.class)
	public ResponseEntity<Object> handleException(NoFlightsAvailableException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<Object> handleException(AirportNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoAirportsAvailableException.class)
	public ResponseEntity<Object> handleException(NoAirportsAvailableException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	 
	@ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<Object> handleException(InvalidDateException  e) {
    	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception  e) {
    	return new ResponseEntity<>("Some unknown error occurred!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
