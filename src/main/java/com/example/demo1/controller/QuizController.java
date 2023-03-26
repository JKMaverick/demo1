package com.example.demo1.controller;

import com.example.demo1.model.Quiz;
import com.example.demo1.model.QuizAnswers;
import com.example.demo1.model.QuizQuestion;
import com.example.demo1.model.QuizType;
import com.example.demo1.model.db.QuizModel;
import com.example.demo1.model.db.Word;
import com.example.demo1.repository.QuizRepository;
import com.example.demo1.service.QuizService;
import com.example.demo1.service.SearchedWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping()
    public ResponseEntity<String> start() {
        randomQuiz();
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/{type}")
    public ModelAndView quiz(@PathVariable String type) {
        ModelAndView mav = new ModelAndView("randomQuiz"); // TODO zmiana nazwy szablonu?
        QuizType quizType = QuizType.of(type);
        Quiz quiz;
        try {
            quiz = quizService.generateQuiz(quizType);
        } catch (Exception e){
            mav.setViewName("error");
            mav.addObject("error", e.getMessage());
            return mav;
        }
        long id = quizService.addToDB(quiz);
        mav.addObject("quiz",quiz);
        mav.addObject("quizAnswers", new QuizAnswers());
        mav.addObject("id", id);
        System.out.println();
        return mav;
    }

    @GetMapping("/randomQuiz")
    public ModelAndView randomQuiz(){
        ModelAndView mav = new ModelAndView("randomQuiz");
        Quiz quiz;
        try {
            quiz = quizService.generateQuiz(QuizType.RANDOM);
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("error", e.getMessage());
            return mav;
        }
        long id = quizService.addToDB(quiz);
        mav.addObject("quiz",quiz);
        mav.addObject("quizAnswers", new QuizAnswers());
        mav.addObject("id", id);
        System.out.println();
        quizService.addToDB(quiz);
        return mav;
    }

    @PostMapping("/randomQuiz")
    public ModelAndView randomQuizPost(@ModelAttribute("quizAnswers") QuizAnswers quizAnswers){
        // otrzymujemy Liste 10 intow
        List<String> list = quizService.getResultRandomQuizList(quizAnswers);
        Quiz quiz = quizService.getQuizById(quizAnswers.getQuizId());
        ModelAndView modelAndView = new ModelAndView("quizAnswerResult");
        /*modelAndView.addObject("list", list);*/
        modelAndView.addObject("quiz", quiz);
        modelAndView.addObject("quizAnswers", quizAnswers);

        modelAndView.addObject("quizQuestions", quiz.getQuizQuestions());

        // TODO do opakowania w obiekt
        List<String> answers = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        List<QuizQuestion> quizQuestions = quiz.getQuizQuestions();
        for (int i = 0; i < quizQuestions.size(); i++) {
            QuizQuestion quizQuestion = quizQuestions.get(i);
            Integer translationIndex = quizAnswers.getAnswerList().get(i);
            if (translationIndex >= 0) {
                answers.add(quizQuestion.getTranslations().get(translationIndex));
            } else {
                answers.add("");
            }
            correctAnswers.add(quizQuestion.getTranslations().get(quizQuestion.getCorrectAnswer()));
        }
        modelAndView.addObject("quizAnswers2", answers);
        modelAndView.addObject("quizCorrectAnswers", correctAnswers);



        System.out.println();
        return modelAndView;
    }
}