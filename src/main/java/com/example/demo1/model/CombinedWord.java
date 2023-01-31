package com.example.demo1.model;

import com.example.demo1.model.db.Translation;
import com.example.demo1.model.db.Word;
import com.example.demo1.model.json.Meaning;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CombinedWord {

    private List<Translation> translations;
    private String pronunciation;
    private String word;
    private List<Meaning> meanings;
    private String phonetic;

    public CombinedWord(com.example.demo1.model.json.Word[] wordsFromExternalApi, List<com.example.demo1.model.db.Word> wordsFromDatabase) {
        this.translations = new ArrayList<>();
        // sposob 1
        for(int i = 0; i < wordsFromDatabase.size(); i++){
            List<Translation> translations = wordsFromDatabase.get(i).getTranslations();
            for(int j = 0; j < translations.size(); j++) {
                this.translations.add(translations.get(j));
            }
        }
        // sposob 2
        for(Word word : wordsFromDatabase){
            List<Translation> translations = word.getTranslations();
            for(Translation translation : translations) {
                this.translations.add(translation);
            }
        }
        // sposob 3
        for(Word word : wordsFromDatabase){
            this.translations.addAll(word.getTranslations());
        }
        // sposob 4
        this.translations = wordsFromDatabase.stream()
                .map(Word::getTranslations)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
