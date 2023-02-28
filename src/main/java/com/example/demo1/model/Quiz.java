package com.example.demo1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
// TODO entity
@Data
public class Quiz {

    private Long id;

    // TODO OneToMany
    private List<QuizQuestion> quizQuestions;

    public Quiz(List<QuizQuestion> quizQuestions){
       this.quizQuestions = quizQuestions;
   }
}
