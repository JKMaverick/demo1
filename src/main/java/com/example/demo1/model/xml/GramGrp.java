package com.example.demo1.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GramGrp implements Serializable {
    // TODO mozna sprobowac zapis pos tak jak Type czyli jako enum
    private String pos;

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
