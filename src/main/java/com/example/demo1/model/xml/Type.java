package com.example.demo1.model.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Type {
    @XmlEnumValue("trans") TRANS,
    @XmlEnumValue("colloc") COLLOC,
    @XmlEnumValue("idiom") IDIOM,
    @XmlEnumValue("example") EXAMPLE
}
