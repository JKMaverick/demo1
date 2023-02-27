package com.example.demo1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class QuizQuestion {
    private String word;
    private List<String> translations;
    private int correctAnswer;

    public QuizQuestion(String word, String translation){
        this.word = word;
        this.translations = new ArrayList<>();
        this.translations.add(translation);
        this.correctAnswer = 0;
    }


}
