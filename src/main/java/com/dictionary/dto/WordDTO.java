package com.dictionary.dto;

public class WordDTO {
    private int wordID;
    private String arabicWord;
    private String urduMeaning;
    private int rootID;
    private int patternID; 

    public WordDTO() {}

    public WordDTO(int wordID, String arabicWord, String urduMeaning, int rootID, int patternID) {
        this.wordID = wordID;
        this.arabicWord = arabicWord;
        this.urduMeaning = urduMeaning;
        this.rootID = rootID;
        this.patternID = patternID; 
    }

    // -------- Getters --------
    public int getWordID() { return wordID; }
    public String getArabicWord() { return arabicWord; }
    public String getUrduMeaning() { return urduMeaning; }
    public int getRootID() { return rootID; }
    public int getPatternID() { return patternID; } 

    // -------- Setters --------
    public void setWordID(int wordID) { this.wordID = wordID; }
    public void setArabicWord(String arabicWord) { this.arabicWord = arabicWord; }
    public void setUrduMeaning(String urduMeaning) { this.urduMeaning = urduMeaning; }
    public void setRootID(int rootID) { this.rootID = rootID; }
    public void setPatternID(int patternID) { this.patternID = patternID; }  // ✅ NEW
}
