package com.example.demo1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParser {

    public void start() {
        // TODO stworzyc analogiczny przyklad do Employee tylko z ParsedLetter (z xml'em z tlumaczeniami per litera)
        File file = new File(this.getClass().getResource("/static/text.xml").getPath());
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Employee employee = (Employee)unmarshaller.unmarshal(file);
            System.out.println();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
