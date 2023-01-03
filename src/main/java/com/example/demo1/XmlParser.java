package com.example.demo1;

import com.example.demo1.model.db.Translation;
import com.example.demo1.model.db.Word;
import com.example.demo1.model.xml.*;
import com.example.demo1.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlParser {

    @Autowired
    WordRepository wordRepository;

    @PostConstruct
    private void postConstruct() {
        List<Word> words = start();
        wordRepository.saveAll(words);
    }

    public List<Word> start() {
        File file = new File(this.getClass().getResource("/static/a.xml").getPath());
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Div.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Div div = (Div) unmarshaller.unmarshal(file);
            // TODO sprawdzic czy nie brakuje jeszcze jakiegos tagu/wartosci w klasach
            /**
             * Word: id, word, pronunciation, translations (bez gramGrp bo ona nie musi tu wystapic - do sprawdzenia gdzie moze)
             * Translation: id, word_id, type, partOfSpeech (gramGrp), quote
             */

            List<Word> entitiesFromDiv = getEntitiesFromDiv(div);
            return entitiesFromDiv;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Word> getEntitiesFromDiv(Div div) {
        List<Word> words = new ArrayList<>();
        for(Entry entry : div.getEntries()){
            Word word = new Word();
            word.setWord(entry.getForm().getOrth());
            word.setPronunciation(entry.getForm().getPron());

            List<Translation> translations = new ArrayList<>();
            Sense[] senses = entry.getSenses();
            loadTranslationsFromSenses(entry, translations, senses);
            word.setTranslations(translations);
            words.add(word);
        }
        return words;
    }

    // TODO do poprawy part of speech (nie zawsze odczytuje)
    private static void loadTranslationsFromSenses(Entry entry, List<Translation> translations, Sense[] senses) {
        for(Sense sense : senses){
            if(sense.getSenses() != null){
                loadTranslationsFromSenses(entry, translations, sense.getSenses());
            }
            if(sense.getCits() != null) {
                for (Cit cit : sense.getCits()) {
                    Translation translation = new Translation();
                    translation.setQuote(cit.getQuote());
                    translation.setType(cit.getType());
                    GramGrp gramGrp = entry.getGramGrp();
                    if(gramGrp != null){
                        translation.setPartOfSpeech(gramGrp.getPos());
                    }
                    translations.add(translation);
                }
            }
        }
    }
}
