package com.example.demo1;

import lombok.Data;

@Data
public class Word {
    String word;
    String phonetic;
    Meaning[] meanings;
}
