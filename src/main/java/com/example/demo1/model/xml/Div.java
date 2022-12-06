package com.example.demo1.model.xml;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
@XmlRootElement(name = "div")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Div implements Serializable {
    private Entry[] entries;

    @XmlElement(name = "entry")
    public Entry[] getEntries() {
        return entries;
    }

    public void setEntries(Entry[] entries) {
        this.entries = entries;
    }
}

