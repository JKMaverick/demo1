package com.example.demo1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Quiz {
   private List<QuizQuestion> quizQuestions;

   public Quiz(List<QuizQuestion> quizQuestions){
       this.quizQuestions = quizQuestions;
   }
}
