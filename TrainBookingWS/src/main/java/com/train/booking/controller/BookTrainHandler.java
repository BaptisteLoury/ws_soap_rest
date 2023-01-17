package com.train.booking.controller;

import java.sql.PreparedStatement;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.train.booking.database.DatabaseManager;
import com.train.booking.model.User;

import localhost.train.booking.BookTrainRequest;

@Component
public class BookTrainHandler {

    @Resource
    private TokenManager tokenManager;
    @Resource
    private RestHandler rest;

    public int handle(BookTrainRequest request) {
        int success = -1;

        String insertReserv = "INSERT INTO main.usersHasBooking(user_id, booking_id) VALUES(?, ?)";

        try {
            User usr = DatabaseManager.getInstance().getUser(request.getUserToken());
            if(!tokenManager.isTokenExpired(request.getUserToken())) {
                int reservId = rest.bookTrain(request.getTrainId(), request.getLastName(), request.getFirstName());

                PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(insertReserv);
                stmt.setInt(1, usr.getId());
                stmt.setInt(2, reservId);

                stmt.executeUpdate();


                success = 1;
            } else {
                success = -2;
            }

        } catch(Exception e) {
            // do nothing
        }   

        return success;
    }
}
