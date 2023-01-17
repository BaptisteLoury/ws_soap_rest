package com.train.booking.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.train.booking.database.DatabaseManager;
import com.train.booking.model.User;

import localhost.train.booking.SeeBookingRequest;
import localhost.train.booking.SeeBookingResponse;
import localhost.train.booking.Train;

@Component
public class SeeBookingHandler {

    @Resource
    private TokenManager tokenManager;
    
    public SeeBookingResponse handle(SeeBookingRequest request) {
        List<Train> trains = new ArrayList<>();

        String selectReserv = "SELECT booking_id FROM main.USERSHASBOOKING WHERE user_id = ?";
        boolean tokenValid = true;

        try {
            User usr = DatabaseManager.getInstance().getUser(request.getUserToken());
            if(!tokenManager.isTokenExpired(request.getUserToken())) {

                PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(selectReserv);
                stmt.setInt(1, usr.getId());
    
                ResultSet result = stmt.executeQuery();
    
                while(result.next()) {
                    
                }

            } else {
                tokenValid = false;
            }
        } catch (SQLException e) {
            
        }

        SeeBookingResponse response = new SeeBookingResponse();
        response.setTokenValid(tokenValid);
        response.getTrains().addAll(trains);

        return response;
    }
}
