package com.dictionary.dao;

import java.util.List;
import com.dictionary.dto.WordDTO;
public interface IWordDAO {
    boolean addWord(WordDTO word);
    List<WordDTO> getAllWords();
    WordDTO searchWord(String arabicWord);
    boolean updateWord(int id, String newMeaning);
    boolean deleteWord(int id);
}
