package com.example.demo1.controller;

import com.example.demo1.model.db.Word;
import com.example.demo1.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<List<Word>> randomQuiz(){

        return new ResponseEntity<>(quizService.generateRandomWords(), HttpStatus.OK);
    }
}