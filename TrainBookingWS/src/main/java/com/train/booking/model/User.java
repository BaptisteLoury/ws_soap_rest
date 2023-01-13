package com.train.booking.model;

import java.sql.Date;

public class User {

    private int id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    
    public User(int id, String lastName, String firstName, Date birthDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    
}
