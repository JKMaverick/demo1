package com.example.demo1.model;

import com.example.demo1.model.db.Translation;
import com.example.demo1.model.db.Word;
import com.example.demo1.model.json.Meaning;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CombinedWord {

    private List<Translation> translations;
    private String pronunciation;
    private String word;
    private List<Meaning> meanings;
    private String phonetic;

    public CombinedWord(com.example.demo1.model.json.Word[] wordsFromExternalApi, List<com.example.demo1.model.db.Word> wordsFromDatabase) {
        //Translation
        this.translations = new ArrayList<>();
        // sposob 1
//        for(int i = 0; i < wordsFromDatabase.size(); i++){
//            List<Translation> translations = wordsFromDatabase.get(i).getTranslations();
//            for(int j = 0; j < translations.size(); j++) {
//                this.translations.add(translations.get(j));
//            }
//        }
        // sposob 2
//        for(Word word : wordsFromDatabase){
//            List<Translation> translations = word.getTranslations();
//            for(Translation translation : translations) {
//                this.translations.add(translation);
//            }
//        }
        // sposob 3
        if (!CollectionUtils.isEmpty(wordsFromDatabase)) {
            // if(wordsFromDatabase != null && !wordsFromDatabase.isEmpty()) {
            for (Word word : wordsFromDatabase) {
                this.translations.addAll(word.getTranslations());
            }
            // sposob 4
//        this.translations = wordsFromDatabase.stream()
//                .map(Word::getTranslations)
//                .flatMap(List::stream)
//                .collect(Collectors.toList());
            //Pronunciation
            this.pronunciation = wordsFromDatabase.get(0).getPronunciation();

            //Word
            this.word = wordsFromDatabase.get(0).getWord();
        }

        //Meaning
        if(!ArrayUtils.isEmpty(wordsFromExternalApi)) {
            this.meanings = new ArrayList<>();
            for (com.example.demo1.model.json.Word word1 : wordsFromExternalApi) {
                this.meanings.addAll(List.of(word1.getMeanings()));
            }
            this.phonetic = wordsFromExternalApi[0].getPhonetic();
        }

    }

    @Override
    public String toString() {
        return "CombinedWord{" +
                "translations=" + translations +
                ", pronunciation='" + pronunciation + '\'' +
                ", word='" + word + '\'' +
                ", meanings=" + meanings +
                ", phonetic='" + phonetic + '\'' +
                '}';
    }
}
