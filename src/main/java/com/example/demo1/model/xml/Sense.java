package com.example.demo1.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Sense implements Serializable {
    private Xr xr;
    private Cit[] cits;

    private Sense[] senses;

    public Xr getXr() {
        return xr;
    }

    public void setXr(Xr xr) {
        this.xr = xr;
    }

    @XmlElement(name = "cit")
    public Cit[] getCits() {
        return cits;
    }

    public void setCits(Cit[] cits) {
        this.cits = cits;
    }

    @XmlElement(name = "sense")
    public Sense[] getSenses() {
        return senses;
    }

    public void setSenses(Sense[] senses) {
        this.senses = senses;
    }
}
