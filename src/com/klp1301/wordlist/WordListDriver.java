package com.klp1301.wordlist;

import java.util.List;
import java.util.Map;

public class WordListDriver {
	public static void main(String[] args) {
		WordListParser wp = new Dota2WordListParser();
		WordListService wls = new WordListService();
		wls.addWordList(wp.getWordList());
	}
}
