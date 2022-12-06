package com.example.demo1.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Cit {
    private String quote;

    private Type type;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @XmlAttribute
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}