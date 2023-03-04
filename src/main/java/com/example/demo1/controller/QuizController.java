package com.example.demo1.controller;

import com.example.demo1.model.Quiz;
import com.example.demo1.model.QuizAnswers;
import com.example.demo1.model.db.QuizModel;
import com.example.demo1.model.db.Word;
import com.example.demo1.repository.QuizRepository;
import com.example.demo1.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/randomQuiz")
    public ModelAndView randomQuiz(){
        ModelAndView mav = new ModelAndView("randomQuiz");
        Quiz quiz = quizService.generateQuiz();
        mav.addObject("quiz",quiz);
        mav.addObject("quizAnswers", new QuizAnswers());
        System.out.println();
        quizService.addToDB(quiz);
        return mav;
    }

    @PostMapping("/randomQuiz")
    public ResponseEntity<String> randomQuizPost(@ModelAttribute("quizAnswers") QuizAnswers quizAnswers){
        // otrzymujemy Liste 10 intow
        return ResponseEntity.ok("");
    }
}