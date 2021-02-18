package main.java.com.wordcount.dao;

import java.util.stream.Stream;

/**
 * WordCountDao interface which is implemented by WordCountDaoImpl class
 * @author Jaya
 *
 */
public interface WordCountDao {
	public Stream<String> getLines();		//read lines into stream of string
 }