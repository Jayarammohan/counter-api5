package main.java.com.wordcount.model;

import java.util.List;
//class encapsulating the Search Criteria 
public class SearchCriteria   {
 
  	private List<String> searchText;	 
  	
	public SearchCriteria() {
		
	}
	
	public SearchCriteria(List<String> searchText) {
		super();
		this.searchText = searchText;
	}	
 
	public List<String> getSearchText() {
		return searchText;
	}

	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}
}

 