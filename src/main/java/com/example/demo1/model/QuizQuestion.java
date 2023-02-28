package com.example.demo1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// TODO Entity
@Data
public class QuizQuestion {
    private String word;

    // TODO
    // mozna tlumaczenia trzymac jako kilka osobnych pol lub jedno z wartosciami rozdzielonymi np. srednikiem
    private List<String> translations;
    private int correctAnswer;

    public QuizQuestion(String word, String translation){
        this.word = word;
        this.translations = new ArrayList<>();
        this.translations.add(translation);
        this.correctAnswer = 0;
    }


}
