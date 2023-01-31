package com.example.demo1.service;

import com.example.demo1.model.db.Word;
import com.example.demo1.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    @Autowired
    WordRepository wordRepository;

    public void saveWord(Word word){
        List<Word> words = wordRepository.findAllByWord(word.getWord());
        if(!words.isEmpty()) {
            // TODO dopisac nowe dane do rekordu w bazie
        } else {
            wordRepository.save(word);
        }
    }

    @Nullable
    public List<Word> search(String word) {
        List<Word> words = wordRepository.findAllByWord(word);
        if (words.size() > 0) {
            return words;
        }
        return null;
    }
}
