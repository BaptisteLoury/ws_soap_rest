package com.train.booking.model;

public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String birthDate;

    public User(Integer id, String email, String firstName, String lastName,
            String birthDate) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }
}