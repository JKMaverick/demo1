package com.example.demo1.model.json;

import lombok.Data;

@Data
public class Meaning {
    String partOfSpeech;
    String[] synonyms;
    Definition[] definitions;
}
