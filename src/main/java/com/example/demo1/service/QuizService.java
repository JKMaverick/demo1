package com.example.demo1.service;

import com.example.demo1.model.Quiz;
import com.example.demo1.model.QuizQuestion;
import com.example.demo1.model.db.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private WordService wordService;

    public List<Word> generateRandomWords() {
        return wordService.getRandomWordsWithTranslations(10);
    }
    public Quiz generateQuiz(){
        List<Word> words = generateRandomWords();
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        for(Word word : words){
            QuizQuestion quizQuestion = new QuizQuestion(word.getWord(), word.getTranslations().get(0).getQuote());
            quizQuestions.add(quizQuestion);
        }

        return new Quiz(quizQuestions);
    }
}
