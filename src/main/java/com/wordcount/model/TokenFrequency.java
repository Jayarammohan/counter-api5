package main.java.com.wordcount.model;
/**
 * 
 * @author Jaya
 * This class encapsulates a token in the input file in terms of the token and its number of occurrences within the file 
 */
public class TokenFrequency {
	
	private String token;
	private int frequency;
	

	public TokenFrequency(String token) {
		this.token = token;
		frequency = 1;	//the initial frequency of the word of 1
	}
	
	public TokenFrequency(String token, int frequency) {
		this.token = token;
		this.frequency = frequency;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	//increment the occurrences of the word by 1
	public int incrementFrequency() {
		frequency += 1;
		return frequency;
	}
	
	public String toString() {
		return frequency + "|" + token + " " ;
	}

}
