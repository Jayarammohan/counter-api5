package main.java.com.wordcount.controller;

import main.java.com.wordcount.model.SearchResults;
import main.java.com.wordcount.service.WordCountService; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired; 
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping( "/top")
/**
 * 
 * @author Jaya
 * Controller class for /top curl command to obtain the topmost frequency words
 */
public class HighestCountsController{
	@Autowired  
	WordCountService wordCountService;  
	@RequestMapping(value = "{howMany}", method = RequestMethod.GET, produces = "text/csv"  )
	public @ResponseBody String getTop(@PathVariable String howMany) {

		int count = 0;
		try {
			count = Integer.parseInt(howMany);
		}
		catch(NumberFormatException nfe) {
			return "Status Code= " + HttpURLConnection.HTTP_BAD_REQUEST + " And error="+howMany + 
					" is an Invalid number (either contains alphanumeric or value > 2 **31 -1) ";
 		}
 
		return wordCountService.getTopCounts(count) ;
 	}
}

