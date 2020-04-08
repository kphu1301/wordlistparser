package com.klp1301.wordlist;

import java.util.List;
import java.util.Map;

public class WordListService {
	
	private WordListDAO wlDAO;
	
	public WordListService() {
		this.wlDAO = new WordListDAO();
	}
	
	public void addWordList(Map<String, List<String>> wordList) {
		wlDAO.addWordList(wordList);
	}

}
