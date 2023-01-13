package com.train.booking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import localhost.train.booking.SeeBookingRequest;
import localhost.train.booking.Train;

@Component
public class SeeBookingHandler {
    
    public List<Train> handle(SeeBookingRequest request) {
        List<Train> trains = new ArrayList<>();

        return trains;
    }
}
