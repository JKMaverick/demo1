package com.example.demo1.model.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quizQuestion")
public class QuizQuestionModel {

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
