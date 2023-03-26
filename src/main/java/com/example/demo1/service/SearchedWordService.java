package com.example.demo1.service;

import com.example.demo1.model.db.SearchedWord;
import com.example.demo1.repository.SearchedWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchedWordService {

    @Autowired
    private SearchedWordRepository searchedWordRepository;

    public void create(Long wordId){
        Optional<SearchedWord> searchedWordOpt = searchedWordRepository.findOneByWordId(wordId);
        /*
        if(!searchedWordOpt.isPresent()){
            searchedWordRepository.save(new SearchedWord(wordId));
        }
        else {
            SearchedWord searchedWord = searchedWordOpt.get();
            searchedWord.increaseCounter();
            searchedWordRepository.save(searchedWord);
        }*/

        SearchedWord searchedWord = searchedWordOpt.orElse(new SearchedWord(wordId));
        searchedWord.increaseCounter();
        searchedWordRepository.save(searchedWord);
    }

    public List<SearchedWord> ListWithSearchedWords(){
        return searchedWordRepository.wordsToQuiz();
    }



}
