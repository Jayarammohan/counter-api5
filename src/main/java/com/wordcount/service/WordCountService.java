package main.java.com.wordcount.service;
import java.util.Map;
import java.util.List;
/**
 * WordCountService interface  
 * @author Jaya
 *
 */
public interface WordCountService {
  	public String getTopCounts(Integer topItems );									//top most topItems number of words (occurrence wise)
  	public Map<String,Integer>[] getWordCounts(List<String> words);					//given the list of words, to return the map containing word and their counts
}