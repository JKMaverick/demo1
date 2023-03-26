package com.example.demo1.service;

import com.example.demo1.model.Quiz;
import com.example.demo1.model.QuizAnswers;
import com.example.demo1.model.QuizQuestion;
import com.example.demo1.model.QuizType;
import com.example.demo1.model.db.QuizModel;
import com.example.demo1.model.db.QuizQuestionModel;
import com.example.demo1.model.db.SearchedWord;
import com.example.demo1.model.db.Word;
import com.example.demo1.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private WordService wordService;

    public Quiz generateQuiz(QuizType quizType) throws Exception {
        List<Word> words = new ArrayList<>();
        if(QuizType.RANDOM.equals(quizType)) {
             words = getRandomWords();
        }
        else if(QuizType.SEARCHED_WORD.equals(quizType)){
             words = getSearchedWords();
        }
        if(words.size()<10){
            throw new Exception("Znaleziono za mało słów");
        }
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        for(Word word : words){
            QuizQuestion quizQuestion = new QuizQuestion(word.getWord(), word.getTranslations().get(0).getQuote());
            addRandomTranslate(quizQuestion, words);
            quizQuestions.add(quizQuestion);
        }

        return new Quiz(quizQuestions);
    }

    // TODO
    private List<Word> getSearchedWords() {
        return wordService.getWordListFromSearchedWordsList();
    }

    public List<Word> getRandomWords() {
        return wordService.getRandomWordsWithTranslations(10);
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

    public Long addToDB(Quiz quiz){
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
        QuizModel entity = quizRepository.save(quizModel);
        return entity.getId();
    }
    public List<String> getResultRandomQuizList(QuizAnswers quizAnswers){
        Optional<QuizModel> quizModel = quizRepository.findOneById(quizAnswers.getQuizId());
        List<String> resultList = new ArrayList<>();
        System.out.println();
        for(int i = 0; i<quizAnswers.getAnswerList().size(); i++ ){
            if(quizAnswers.getAnswerList().get(i) == quizModel.get().getQuizQuestionModelList().get(i).getCorrectAnswer()){
               resultList.add("Prawda");
            }
            else resultList.add("Fałsz");
        }
        return resultList;
    }
    public Quiz getQuizById(Long id){
        Optional<QuizModel> optionalQuizModel = quizRepository.findOneById(id);
        QuizModel quizModel = optionalQuizModel.get();
        List<QuizQuestion> quizQuestionList = new ArrayList<>();
        for(int i = 0; i< quizModel.getQuizQuestionModelList().size(); i++){
            QuizQuestion quizQuestion = new QuizQuestion(quizModel.getQuizQuestionModelList().get(i).getWord());
            quizQuestion.setCorrectAnswer(quizModel.getQuizQuestionModelList().get(i).getCorrectAnswer());

            quizQuestion.getTranslations().add(quizModel.getQuizQuestionModelList().get(i).getAnswer1());
            quizQuestion.getTranslations().add(quizModel.getQuizQuestionModelList().get(i).getAnswer2());
            quizQuestion.getTranslations().add(quizModel.getQuizQuestionModelList().get(i).getAnswer3());
            quizQuestion.getTranslations().add(quizModel.getQuizQuestionModelList().get(i).getAnswer4());
            quizQuestionList.add(quizQuestion);
        }
        Quiz quiz = new Quiz(quizQuestionList);
        quiz.setId(quizModel.getId());
        return quiz;
    }
}
