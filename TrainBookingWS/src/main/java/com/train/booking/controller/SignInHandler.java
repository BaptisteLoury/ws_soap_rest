package com.train.booking.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.train.booking.database.DatabaseManager;

import localhost.train.booking.SignInRequest;

@Component
public class SignInHandler {

    @Resource
    TokenManager tokenManager;

    public String handle(SignInRequest request) {
        String token = "err";
        String selectUser = "SELECT user_id FROM main.USERS WHERE user_email = ? AND user_password = ?";
        String updateUser = "UPDATE main.USERS SET user_token = ? WHERE user_id = ?";

        try(PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(selectUser)) {
            System.err.println("POST prepare");
            stmt.setString(1, request.getEmail());
            stmt.setString(2, request.getPassword());

            ResultSet res = stmt.executeQuery();
            System.err.println("POST exec");
            if(res.next()) {
                PreparedStatement stmt2 = DatabaseManager.getInstance().getConnection().prepareStatement(updateUser);
                System.err.println("POST next + prepare");
                String tempToken = tokenManager.genToken();
                stmt2.setString(1, tempToken);
                stmt2.setInt(2, res.getInt(1));

                stmt2.executeUpdate();
                System.err.println("POST exec update");

                token = tempToken;
            }
        } catch(SQLException e) {
            // do nothing
        }


        return token;
    }
    
}
