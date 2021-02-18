 package main.java.com.wordcount.model;
 import java.util.Map;
/**
 * 
 * @author Jaya
 * Class encapsulating the search results
 * SearchResults class containing an array of Maps of the words and their counts
 */
public class SearchResults {

	Map<String,Integer>[] counts;
	
	public SearchResults() {
	}
 
	public Map<String,Integer>[] getCounts() {
		return counts;
	}
	public void setCounts(Map<String,Integer>[] counts) {
		this.counts = counts;
	}
}