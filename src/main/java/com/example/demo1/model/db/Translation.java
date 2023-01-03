package com.example.demo1.model.db;

import com.example.demo1.model.xml.Type;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "translations")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Type type;
    private String partOfSpeech; //TODO Zamiana na enuma
    private String quote;
    //TODO Dojdzie kolejne pole w wypadku wystąpienia colloca

}
