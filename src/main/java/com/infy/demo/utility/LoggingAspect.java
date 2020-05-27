package com.infy.demo.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
	
    @Around("execution(* com.infy.demo.api.UserAPI.registerUser(..))")
    public Object logAroundRegisterUserMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("USER TRYING TO REGISTER...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	log.info("USER SUCCESSFULLY REGISTERED...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.UserAPI.loginUser(..))")
    public Object logAroundLoginUserMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("USER TRYING TO LOGIN...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	log.info("USER SUCCESSFULLY LOGGED IN...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.TravelerSearchAPI.getFlights(..))")
    public Object logAroundGetFlightsMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("SEARCHING FOR FLIGHTS...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	log.info("FLIGHTS FOUND...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.TravelerSearchAPI.getAllOrigins(..))")
    public Object logAroundGetAllOriginsMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("SEARCHING FOR ORIGINS...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	log.info("ORIGINS ACQUIRED...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.TravelerSearchAPI.getAllDestinations(..))")
    public Object logAroundGetAllDestinationsMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {	
    	Object obj = null;
    	log.info("SEARCHING FOR DESTINATIONS...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	log.info("DESTINATIONS ACQUIRED...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.FlightAPI.addFlight(..))")
    public Object logAroundAddFlightMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("ADDING FLIGHT...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	
    	log.info("SUCCESSFULLY ADDED FLIGHT...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.FlightAPI.deleteFlight(..))")
    public Object logAroundDeleteFlightMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("DELETING FLIGHT...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	log.info("SUCCESSFULLY DELETED FLIGHT...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.FlightAPI.getFlights(..))")
    public Object logAroundGetFlightsFlightMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("SEARCHING FOR FLIGHTS...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        }
    	log.info("FLIGHTS FOUND...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.AirportAPI.addAirport(..))")
    public Object logAroundAddAirportMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("ADDING AIRPORT...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        };
    	log.info("SUCCESSFULLY ADDED AIRPORT...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.AirportAPI.deleteAirport(..))")
    public Object logAroundDeleteAirportMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("DELETING AIRPORT...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        };
    	log.info("SUCCESSFULLY DELETED AIRPORT...");
    	return obj;
    }
    
    @Around("execution(* com.infy.demo.api.AirportAPI.getAirports(..))")
    public Object logAroundGetAirportsFlightMethod(ProceedingJoinPoint joinPoint) throws Throwable 
    {
    	Object obj = null;
    	log.info("SEARCHING FOR AIRPORTS...");
    	try{
    		obj =  aroundLogHelper(joinPoint);
            
        } catch(Throwable e){
            e.printStackTrace();
        };
    	log.info("AIRPORTS FOUND...");
    	return obj;
    }
    
    private Object aroundLogHelper(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().getName()+ " " + joinPoint.getSignature().toString());
        return joinPoint.proceed();
    }
    

	@AfterThrowing(pointcut = "execution(* com.infy.demo.dao.*Impl.*(..))", throwing = "exception")	
	public void logExceptionFromDAO(Exception exception) throws Exception {
		log(exception);
	}
	
	@AfterThrowing(pointcut = "execution(* com.infy.demo.service.*Impl.*(..))", throwing = "exception")	
	public void logExceptionFromService(Exception exception) throws Exception {
			log(exception);
	}
	
	private void log(Exception exception) {
		Logger logger = LogManager.getLogger(this.getClass());
		if(exception.getMessage() != null)
		{
			logger.error(exception.getClass());
			logger.error(exception.getMessage());
			logger.error(exception);
		}
		else
		{
			logger.error(exception.getClass());
			logger.error(exception);
		}
	}
	

}
