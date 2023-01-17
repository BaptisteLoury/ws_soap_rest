package com.train.booking.controller;

import java.sql.SQLException;

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

        try {
            User usr = DatabaseManager.getInstance().getUser(request.getUserToken());
            if(!tokenManager.isTokenExpired(request.getUserToken())) {
                int reservId = rest.bookTrain(request.getTrainId(), usr.getLastName(), usr.getFirstName());
                System.out.println(reservId);

                success = 1;
            } else {
                success = -2;
            }

        } catch(SQLException e) {

        }   

        return success;
    }
}
