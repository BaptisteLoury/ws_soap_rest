package com.train.booking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import localhost.train.booking.FetchTrainRequest;
import localhost.train.booking.Train;

@Component
public class FetchTrainHandler {
    
    public List<Train> handle(FetchTrainRequest request) {
        List<Train> trains = new ArrayList<>();

        return trains;
    }
}
