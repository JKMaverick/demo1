package com.example.demo1.service;

import com.example.demo1.model.Quiz;
import com.example.demo1.model.QuizQuestion;
import com.example.demo1.model.db.QuizModel;
import com.example.demo1.model.db.QuizQuestionModel;
import com.example.demo1.model.db.Translation;
import com.example.demo1.model.db.Word;
import com.example.demo1.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
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
    public void addToDB(Quiz quiz){
        QuizModel quizModel = new QuizModel();
        List<QuizQuestionModel> quizQuestionModelList = new ArrayList<>();
        for(QuizQuestion quizQuestion :quiz.getQuizQuestions()){
            QuizQuestionModel quizQuestionModel = new QuizQuestionModel();
            quizQuestionModel.setWord(quizQuestion.getWord());
            quizQuestionModel.setAnswer1(quizQuestion.getTranslations().get(0));
            quizQuestionModel.setAnswer2(quizQuestion.getTranslations().get(1));
            quizQuestionModel.setAnswer3(quizQuestion.getTranslations().get(2));
            quizQuestionModel.setAnswer4(quizQuestion.getTranslations().get(3));
            quizQuestionModel.setCorrectAnswer(quizQuestion.getCorrectAnswer());
            quizQuestionModelList.add(quizQuestionModel);
        }
        quizModel.setQuizQuestionModelList(quizQuestionModelList);
        quizRepository.save(quizModel);
    }
}
