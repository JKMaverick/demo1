package com.example.demo1.model;

import com.example.demo1.model.db.Translation;
import com.example.demo1.model.json.Meaning;

import java.util.List;

public class CombinedWord {

    private List<Translation> translations;
    private String pronunciation;
    private String word;
    private List<Meaning> meanings;
    private String phonetic;

    public CombinedWord(com.example.demo1.model.json.Word[] wordFromExternalApi, com.example.demo1.model.db.Word wordFromDatabase) {

    }
}
