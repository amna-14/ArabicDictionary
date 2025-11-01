package com.dictionary.bo;

import java.util.List;
import com.dictionary.dao.IWordDAO;
import com.dictionary.dao.WordDAO;
import com.dictionary.dto.WordDTO;

public class WordBO {

    private IWordDAO wordDAL;

    // Dependency Injection 
    public WordBO(WordDAO word) {
        this.wordDAL = word;
    }

    public WordBO(IWordDAO wordDAL) {
        this.wordDAL = wordDAL;
    }

    // Method to add word with root ID
    public String addWord(String arabicWord, String urduMeaning, int rootID, int patternID) {
        if (arabicWord == null || arabicWord.trim().isEmpty()) return "Error! Arabic word is required.";
        if (urduMeaning == null || urduMeaning.trim().isEmpty()) return "Error! Urdu meaning is required.";
        if (rootID <= 0) return "Error! Root ID is required.";
        if (patternID <= 0) return "Error! Pattern ID is required."; // ✅ NEW

        WordDTO word = new WordDTO();
        word.setArabicWord(arabicWord.trim());
        word.setUrduMeaning(urduMeaning.trim());
        word.setRootID(rootID);
        word.setPatternID(patternID); 

        boolean success = wordDAL.addWord(word);
        return success ? "Word added successfully!" : "Failed to add word.";
    }


    // View all words
    public List<WordDTO> getAllWords() {
        return wordDAL.getAllWords();
    }

    // Search word
    public WordDTO searchWord(String arabicWord) {
        if (arabicWord == null || arabicWord.trim().isEmpty()) {
            return null;
        } else {
            return wordDAL.searchWord(arabicWord.trim());
        }
    }

    // Update Word Meaning
    public String updateWord(int id, String newMeaning) {
        if (id <= 0) {
            return "Invalid Word ID.";
        } else if (newMeaning == null || newMeaning.trim().isEmpty()) {
            return "New meaning cannot be empty.";
        } else {
            boolean success = wordDAL.updateWord(id, newMeaning.trim());
            if (success) {
                return "Word updated successfully!";
            } else {
                return "Failed to update word.";
            }
        }
    }

    // Delete Word
    public String deleteWord(int id) {
        if (id <= 0) {
            return "Invalid Word ID.";
        } else {
            boolean success = wordDAL.deleteWord(id);
            if (success) {
                return "Word deleted successfully!";
            } else {
                return "Failed to delete word.";
            }
        }
    }
}
