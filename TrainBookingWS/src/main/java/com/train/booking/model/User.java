package com.train.booking.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String birthDate;

    public User(Integer id, String email, String password, String firstName, String lastName,
            String birthDate) {
        this.id = id;
        this.email = email;

        MessageDigest hashFunc;
        try {
            hashFunc = MessageDigest.getInstance("SHA-256");
            hashFunc.update(password.getBytes());
            this.password = new String(hashFunc.digest());
        } catch (NoSuchAlgorithmException e) {
            this.password = ""+password.hashCode();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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