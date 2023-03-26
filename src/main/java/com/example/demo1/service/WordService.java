package com.example.demo1.service;

import com.example.demo1.model.db.SearchedWord;
import com.example.demo1.model.db.Word;
import com.example.demo1.repository.SearchedWordRepository;
import com.example.demo1.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class WordService {
    @Autowired
    WordRepository wordRepository;

    @Autowired
    SearchedWordService searchedWordService;

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
            searchedWordService.create(words.get(0).getId());
            return words;
        }
        return null;
    }

    public Long maxID(){
        return wordRepository.findMaxID();
    }

    public List<Word> getRandomWordsWithTranslations(int numberOfWords){
        Random random = new Random();
        Long maxId = wordRepository.findMaxID();
        List<Word> words = new ArrayList<>();
        while(words.size()<numberOfWords){
            Long randomId = random.nextLong() % maxId + 1;
            Optional<Word> wordOpt = wordRepository.findOneById(randomId);
            if(wordOpt.isPresent() && !CollectionUtils.isEmpty(wordOpt.get().getTranslations())) {
                words.add(wordOpt.get());
            }
        }

        return words;
    }
    public List<Word> getWordListFromSearchedWordsList(){
        List<SearchedWord> searchedWordsList = searchedWordService.ListWithSearchedWords();
        List<Word> words = new ArrayList<>();
        for(SearchedWord searchedWord: searchedWordsList){
            Optional<Word> word = wordRepository.findOneById(searchedWord.getWordId());
            if(word.isPresent()){
                words.add(word.get());
            }
        }
        return words;
    }
}
