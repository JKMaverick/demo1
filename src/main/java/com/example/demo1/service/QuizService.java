package com.example.demo1.service;

import com.example.demo1.model.db.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private WordService wordService;

    public List<Word> generateRandomWords() {
        return wordService.getRandomWords(10);
    }
}
