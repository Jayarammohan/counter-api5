package main.java.com.wordcount.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;
 
/**Error Handler class for our controllers
 * if for the search command, the json input has syntax errors, this handles it and returns the proper error status code and error message back.
 * @author Jaya
 *
 */
@ControllerAdvice
public class RestErrorHandler {
  
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processValidationError(Exception ex) {
		return "Status Code= " + HttpURLConnection.HTTP_BAD_REQUEST + " http error=Could not parse JSON input from request  ";    	
    }
 
 }