package com.example.demo1.service;

import com.example.demo1.model.Quiz;
import com.example.demo1.model.QuizQuestion;
import com.example.demo1.model.db.Translation;
import com.example.demo1.model.db.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
            addRandomTranslate(quizQuestion, words);
            quizQuestions.add(quizQuestion);
        }

        return new Quiz(quizQuestions);
    }
    public QuizQuestion addRandomTranslate(QuizQuestion quizQuestion, List<Word> words){
        String rightTranslation = quizQuestion.getTranslations().get(0);
        Random random = new Random();
        while(quizQuestion.getTranslations().size() < 4){
            int randomWord = random.nextInt(words.size());
            String translation = words.get(randomWord).getTranslations().get(0).getQuote();
            if(!quizQuestion.getTranslations().contains(translation)){
                quizQuestion.getTranslations().add(translation);
            }
        }
        Collections.shuffle(quizQuestion.getTranslations());
        quizQuestion.setCorrectAnswer(quizQuestion.getTranslations().indexOf(rightTranslation));
        return quizQuestion;
    }
}
