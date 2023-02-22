package com.example.demo1.service;

import com.example.demo1.model.db.Word;
import com.example.demo1.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public Long maxID(){
        return wordRepository.findMaxID();
    }

    public List<Word> getRandomWords(int numberOfWords){
        Random random = new Random();
        Long maxId = wordRepository.findMaxID();
        List<Word> words = new ArrayList<>();
        while(words.size()<numberOfWords){
            Long randomId = random.nextLong() % maxId + 1;
            Optional<Word> wordOpt = wordRepository.findOneById(randomId);
            if(wordOpt.isPresent()) {
                words.add(wordOpt.get());
            }
        }

        return words;
    }
}
