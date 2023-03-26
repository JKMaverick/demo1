package com.example.demo1.model.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quizQuestion")
public class QuizQuestionModel {

    // 123, 1, word, answer1
    // 124, 1, word, answer2
    // 125, 1, word, answer3
    // 126, 1, word, answer4


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctAnswer;
}
