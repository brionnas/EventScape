package com.model;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Person(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // Getters
    public String getFirstName() { 
        return firstName; 
    }
    public String getLastName() { 
        return lastName; 
    }
    public Date getBirthDate() { 
        return birthDate; 
    }

    // setters
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }       
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    public void setBirthDate(Date birthDate) { 
        this.birthDate = birthDate; 
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
