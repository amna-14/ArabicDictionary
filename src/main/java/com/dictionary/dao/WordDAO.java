package com.dictionary.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dictionary.dto.WordDTO;
import com.dictionary.sql.DBConnection;

public class WordDAO implements IWordDAO {

	@Override
	public boolean addWord(WordDTO word) {
		String sql = "INSERT INTO word (arabic_word, urdu_meaning, root_id, pattern_id) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, word.getArabicWord());
	        stmt.setString(2, word.getUrduMeaning());

	        if (word.getRootID() <= 0) {
	            stmt.setNull(3, java.sql.Types.INTEGER);
	        } else {
	            stmt.setInt(3, word.getRootID());
	        }

if (word.getPatternID() <= 0) stmt.setNull(4, java.sql.Types.INTEGER); 
else stmt.setInt(4, word.getPatternID()); 

	        int rowsInserted = stmt.executeUpdate();
	        System.out.println("Insert rows: " + rowsInserted);
	        return rowsInserted > 0;

	    } catch (SQLException e) {
	        System.out.println("Add Word Error:");
	        e.printStackTrace();
	    }
	    return false;
	}

    @Override
    public List<WordDTO> getAllWords() {
        List<WordDTO> wordList = new ArrayList<>();
        String sql = "SELECT * FROM word";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WordDTO word = new WordDTO();
                word.setWordID(rs.getInt("word_id")); 
                word.setArabicWord(rs.getString("arabic_word"));
                word.setUrduMeaning(rs.getString("urdu_meaning"));
                word.setRootID(rs.getInt("root_id"));
                word.setPatternID(rs.getInt("pattern_id")); 

                wordList.add(word);
            }

        } catch (SQLException e) {
            System.out.println("Fetch All Words Error:");
            e.printStackTrace();
        }
        return wordList;
    }

    @Override
    public WordDTO searchWord(String arabicWord) {
        String sql = "SELECT * FROM word WHERE arabic_word = ?";
        WordDTO word = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, arabicWord);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                word = new WordDTO();
                word.setWordID(rs.getInt("word_id")); 
                word.setArabicWord(rs.getString("arabic_word"));
                word.setUrduMeaning(rs.getString("urdu_meaning"));
                word.setRootID(rs.getInt("root_id"));
                word.setPatternID(rs.getInt("pattern_id"));  


            }

        } catch (SQLException e) {
            System.out.println(" Search Word Error:");
            e.printStackTrace();
        }
        return word;
    }

    @Override
    public boolean updateWord(int id, String newMeaning) {
        String sql = "UPDATE word SET urdu_meaning = ? WHERE word_id = ?"; 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newMeaning);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(" Update Word Error:");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteWord(int id) {
        String sql = "DELETE FROM word WHERE word_id = ?"; 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(" Delete Word Error:");
            e.printStackTrace();
        }
        return false;
    }
}
