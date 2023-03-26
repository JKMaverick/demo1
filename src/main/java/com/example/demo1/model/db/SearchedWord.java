package com.example.demo1.model.db;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "searched_word")
public class SearchedWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long wordId;

    private int counter;

    public SearchedWord() {
    }

    public SearchedWord(Long wordId) {
        this.wordId = wordId;
    }
    public void increaseCounter(){
        this.counter += 1;
    }
}
