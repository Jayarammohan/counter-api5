package main.java.com.wordcount.service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import main.java.com.wordcount.dao.WordCountDao;
import main.java.com.wordcount.model.TokenFrequency;

/**
 * @author Jaya
 * The service class which returns the 1. occurrences for a given list of words and 2. The topmost occurring N number of words
 * * of words, counts the word occurrences and
 * stores a Map of words and their occurrences and
 
 * Assumptions:
 *      	1. If the file contents are modified, then a server restart is needed to produce correct output
 *      2. Note: The regex used in the split command in this class is just for the sample file provided, this will not work for splitting words in an arbitrary file.
 *         For example, hyphenated words and words with apostrophes will break this.
 */
@Service
public class WordCountServiceImpl implements WordCountService {
	@Autowired  
	WordCountDao wordCountDao;

	
	private HashMap<String,TokenFrequency> countWordsMap = new HashMap<>();			     	//Map with the word as the key & TokenFrequency as value
	private List<TokenFrequency> sortedTokenFrequency ;                                      //has list of tokenFrequency objects sorted by frequency(desc)
	
	public HashMap<String, TokenFrequency> getCountWordsMap() {
		return countWordsMap;
	}

	public void setCountWordsMap(HashMap<String, TokenFrequency> countWordsMap) {
		this.countWordsMap = countWordsMap;
	}

	public List<TokenFrequency> getSortedTokenFrequency() {
		return sortedTokenFrequency;
	}

	public void setSortedTokenFrequency(List<TokenFrequency> sortedTokenFrequency) {
		this.sortedTokenFrequency = sortedTokenFrequency;
	}
	
	public WordCountServiceImpl() {
	};

	@PostConstruct
	public void init() {
		countWords();							//the countMap created is used for word count service (given the word)
		generateSortedListOfWordsByFrequency();	//this list is used for top items service
	}	
 
	private void countWords() {
		Stream<String> lines = wordCountDao.getLines();																  //the lines of the input stream
		List<String> wordList = lines.flatMap(line -> Arrays.stream(line.split("\\W+"))).collect(Collectors.toList()); //produce list of words out of lines	  

		//go through the list of words and create a map containing the word and the frequency of the word within the file
		wordList.forEach(item->{
			item = item.toLowerCase();								//due to requirement in the spec that words are to be counted case insensitive
			if (countWordsMap.containsKey(item)) {
				countWordsMap.get(item).incrementFrequency();		//increment the count
			}
			else {                                                  //this word occurs for the first time in the file
				countWordsMap.put(item, new TokenFrequency(item));  //add a new entry in the map with newly instantiated TokenFrequency with count =1
			}	
		});
	}

	/** go through the countWordsMap which has word as the key
	 *  and construct the list sorted with occurrences (descending order)
	 **/
	private void generateSortedListOfWordsByFrequency() {

		sortedTokenFrequency = countWordsMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()); //convert map to list
		sortedTokenFrequency.sort((TokenFrequency o1, TokenFrequency o2)->o2.getFrequency()-o1.getFrequency());         //sort list based on frequency

	}
	
	/**
	 * Return the frequency of a given word, that is the number of occurrences within the file
	 * @param word
	 * @return the frequency
	 */
	private int getFrequencyOfWord(String word) {
		word = word.toLowerCase();	//transformed to lower case because in the spec doc. the words are case insensitive
		return (getCountWordsMap().containsKey(word) ? getCountWordsMap().get(word).getFrequency() : 0);
	}		

	
	/**
	 * Given the number of top most items needed, return a string in the below format
	 * 17|vel 17|eget 16|sed 15|in 14
	 * Viz., the frequency appended with pipe sign, the word and then a space character and so on, as many times as the required topItems input parameter
	 * @param topItems
	 * @return
 	 * if topItems input arg is > the total number of words, then all the items are returned with the count frequency order
	 * and if the topItems <=0, empty String is returned
	 */
	public String getTopCounts(Integer topItems ) {

 		StringBuffer resultBuffer = new StringBuffer();
		if(topItems < 1)
			return resultBuffer.toString();	//if topItems input arg is either 0 or negative, then return empty String
	
		StringBuffer topItemsConcatenated = new StringBuffer();

		//select only the topmost required items from the list by limiting the items
		List<TokenFrequency> topmostTokenFrequency = sortedTokenFrequency.stream().limit(topItems).collect(Collectors.toList()); 
	                                                                                                                        
		topmostTokenFrequency.forEach(item-> {
			topItemsConcatenated.append(item.toString()).append("\n");                                                   //form the output by concatenating
		});
	
		return topItemsConcatenated.toString();
	}
	
  	/** 
	 * @param words
	 * @return count information of the given passed in words in terms of Array of Maps with each element of the Map
	 * being a Key value pair consisting of the word and the frequency of occurrence of the word 
	 * This enables the JSON output representation to be in the desired format
	 * Viz., {"counts":[{"Duis":11},{"Sed":4},{"Augue":0},{"Donec":8},{"Pellentesque":5},{"123":0}]}
	 */
	public Map<String,Integer>[] getWordCounts(List<String> words) { 
		HashMap<String, Integer>[] countMap = new HashMap[words.size()];
		int count = 0;

 		for(String word : words) {		 
  			countMap[count] = new HashMap<String, Integer>();
			countMap[count].put(word, getFrequencyOfWord(word));
			count++;
		} 	   
 		return countMap;
	}
}
