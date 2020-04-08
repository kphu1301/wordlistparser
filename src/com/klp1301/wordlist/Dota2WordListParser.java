package com.klp1301.wordlist;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Dota2WordListParser implements WordListParser {
	private Map<String, List<String>> wordList;
	
	public Dota2WordListParser() {
		this.wordList = new HashMap<>();
	}
	
	@Override
	public Map<String, List<String>> getWordList() {
		String category = "Dota 2 - Heroes";
		wordList.put(category, getHeroList());
		
		category = "Dota 2 - Items";
		wordList.put(category, getItemList());
		
		return wordList;
	}

	private List<String> getHeroList() {
		String baseUrl = "https://www.dota2.com";
		String path = "/heroes";
		String url = baseUrl + path;
		List<String> heroes = new LinkedList<>();
		
		try {
			// get html from page
			Document doc = Jsoup.connect(url).get();
		
			// extract heroes
			Elements options = doc.select("select[id=filterName] > option:gt(1)");
			
			options.forEach(option -> {
				String hero = option.ownText();
				heroes.add(hero);
			});
		}
		catch (IOException e) {
			System.out.println("We dun goofed");
		}
		
		return heroes;
	}
	
	private List<String> getItemList() {
		String baseUrl = "https://liquipedia.net";
		String path = "/dota2/Portal:Items";
		String url = baseUrl + path;
		List<String> items = new LinkedList<>();
		
		try {
			// get html from page
			Document doc = Jsoup.connect(url).get();
		
			// extract items
			Elements imgs = doc.select(".responsive > a > img");
	
			imgs.forEach(img -> {
				String item = img.attr("alt");
				items.add(item);
			});
		}
		catch (IOException e) {
			System.out.println("We dun goofed");
		}
		
		return items;
	}
}
