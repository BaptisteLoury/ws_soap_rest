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
import com.train.booking.model.rest.ReservationRest;

import localhost.train.booking.Reservation;
import localhost.train.booking.SeeBookingRequest;
import localhost.train.booking.SeeBookingResponse;

@Component
public class SeeBookingHandler {

    @Resource
    private TokenManager tokenManager;
    @Resource
    private RestHandler rest;
    
    public SeeBookingResponse handle(SeeBookingRequest request) {
        List<Reservation> reservations = new ArrayList<>();

        String selectReserv = "SELECT booking_id FROM main.USERSHASBOOKING WHERE user_id = ?";
        boolean tokenValid = true;

        try {
            User usr = DatabaseManager.getInstance().getUser(request.getUserToken());
            if(!tokenManager.isTokenExpired(request.getUserToken())) {

                PreparedStatement stmt = DatabaseManager.getInstance().getConnection().prepareStatement(selectReserv);
                stmt.setInt(1, usr.getId());
    
                ResultSet result = stmt.executeQuery();
    
                while(result.next()) {
                    ReservationRest reserv = rest.seeBooking(result.getInt(1));

                    reservations.add(reserv.toSoap());
                }

            } else {
                tokenValid = false;
            }
        } catch (SQLException e) {
            
        }

        SeeBookingResponse response = new SeeBookingResponse();
        response.setTokenValid(tokenValid);
        response.getReservations().addAll(reservations);

        return response;
    }
}
