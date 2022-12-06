package com.example.demo1.model.db;

import lombok.Data;

import java.util.List;

@Data
public class Word {
    private Long id;
    private String word;
    private String pronunciation;
    private List<Translation> translations;
}
