package com.train.booking.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.train.booking.database.DatabaseManager;

import localhost.train.booking.SignUpRequest;

@Component
public class SignUpHandler {
    
    public boolean handle(SignUpRequest request) {
        boolean success = true;
        String insertUser = "INSERT INTO main.USERS(user_email, user_password, user_first_name, user_last_name, user_birth_date) VALUES(?,?,?,?,?)";
        System.err.println("POST TRY");

        try(PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(insertUser)) {
            System.err.println("POST prepare");
            stmt.setString(1, request.getEmail());
            stmt.setString(2, request.getPassword());
            stmt.setString(3, request.getFirstName());
            stmt.setString(4, request.getLastName());
            stmt.setDate(5, new java.sql.Date((request.getBirthDate().toGregorianCalendar().getTime().getTime())));

            stmt.executeUpdate();
            System.err.println("POST exec update");
        } catch(SQLException e) {
            success = false;
            System.err.println(e.getMessage() );
        }

        return success;
    }
}
