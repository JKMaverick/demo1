package com.example.demo1.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Entry {
    private Form form;
    private GramGrp gramGrp;
    private Sense[] senses;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public GramGrp getGramGrp() {
        return gramGrp;
    }

    public void setGramGrp(GramGrp gramgrp) {
        this.gramGrp = gramgrp;
    }

    @XmlElement(name = "sense")
    public Sense[] getSenses() {
        return senses;
    }

    public void setSenses(Sense[] senses) {
        this.senses = senses;
    }

}
