package com.example.demo1;

import lombok.Data;

@Data
public class Meaning {
    String partOfSpeech;
    String[] synonyms;
    Definition[] definitions;
}
