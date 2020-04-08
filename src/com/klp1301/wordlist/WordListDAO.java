package com.klp1301.wordlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class WordListDAO {
	
	public void addWordList(Map<String, List<String>> wordList) {
		Connection conn = null;
		String url = "jdbc:postgresql://localhost/db_name";
		String user = "user";
		String password = "password";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO word (word, category) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			wordList.forEach((category, words) -> {
				words.forEach(word -> {
						try {
							ps.setString(1, word);
							ps.setString(2, category);
							ps.addBatch();
						}
						catch (SQLException e) {
							e.printStackTrace();
						}
				});
			});
			ps.executeBatch();
			conn.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
