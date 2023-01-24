package com.example.demo1.service;

import com.example.demo1.model.db.Word;
import com.example.demo1.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordService {
    @Autowired
    WordRepository wordRepository;

    public void saveWord(Word word){
        Optional<Word> optionalWord = wordRepository.findOneByWord(word.getWord());
        if(optionalWord.isPresent()) {
            Word dbWord = optionalWord.get();
            // TODO dopisac nowe dane do rekordu w bazie
        } else {
            wordRepository.save(word);
        }
    }

    @Nullable
    public Word search(String word) {
        Optional<Word> optionalWord = wordRepository.findOneByWord(word);
        if (optionalWord.isPresent()) {
            return optionalWord.get();
        }
        return null;
    }
}
