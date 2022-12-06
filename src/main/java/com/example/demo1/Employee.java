package com.example.demo1;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private Department[] department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department[] getDepartment() {
        return department;
    }

    public void setDepartment(Department[] department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department="
                + department + "]";
    }

    // It is called immediately after the object is created and before the unmarshalling begins.
    // The callback provides an opportunity to initialize JavaBean properties prior to unmarshalling.
    void beforeUnmarshal(Unmarshaller unmarshaller, Object parent) {
        System.out.println("Before Unmarshaller Callback");
    }

    // It is called after all the properties are unmarshalled for this object,
    // but before this object is set to the parent object.
    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        System.out.println("After Unmarshaller Callback");
    }
}