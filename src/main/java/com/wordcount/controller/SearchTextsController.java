 package main.java.com.wordcount.controller;

import main.java.com.wordcount.model.SearchResults;
import main.java.com.wordcount.model.SearchCriteria;
import main.java.com.wordcount.service.WordCountService;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

@RestController
@RequestMapping("/search")
/**
 * 
 * @author Jaya
 * Controller class for /search curl command. Gives the counts of the desired words in json representation
 *
 */
public class SearchTextsController{
	@Autowired  
	WordCountService wordCountService;  
	@RequestMapping(  method = RequestMethod.POST, produces = "application/json", consumes = "application/json" )
	//The Valid annotation for the RequestBody below, in conjunction with the ConrollerAdvice within RestHandler class handle the input json error
	public @ResponseBody SearchResults showCounts(@Valid @RequestBody SearchCriteria searchCriteria) {

		List<String> wordsList = searchCriteria.getSearchText();   	//the search Curl command words input in json format gets into this method as SearchCriteria RequestBody input object. Jackson library enables reading
																	//	JSON string and converting it to a Java object.  

		SearchResults searchResults = new SearchResults();
		//pass the input words to the WordCountService class's method to obtain the counts of those words
		Map<String,Integer>[] wordCounts = wordCountService.getWordCounts(wordsList);
		searchResults.setCounts(wordCounts);													// set the obtained counts in the search results object
		return searchResults;                                                                //and return the search results in the method's ResponseBody return parameter; jackson turns it into json representation
	}
}