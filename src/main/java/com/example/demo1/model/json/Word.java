package com.example.demo1.model.json;

import lombok.Data;

@Data
public class Word {
    String word;
    String phonetic;
    Meaning[] meanings;
}
