package com.example.demo1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizAnswers {
    private Long quizId;
    private List<Integer> answerList = new ArrayList<>(10); // new ArrayList<>(10);

    public QuizAnswers() {
        for(int i = 0; i < 10; i++){
            answerList.add(-1);
        }
    }
}
