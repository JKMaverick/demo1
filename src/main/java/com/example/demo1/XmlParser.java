package com.example.demo1;

import com.example.demo1.model.xml.Div;
import com.example.demo1.model.xml.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public void start() {
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
            System.out.println();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Word> getEntitiesFromDiv(Div div) {
        List<Word> words = new ArrayList<>();
        for(Entry entry : div.getEntries()){
            Word word = new Word();
            // TODO sprobowac dokonczyc ile da rade

        }
        return words;
    }
}
