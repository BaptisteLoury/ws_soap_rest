package com.train.booking.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import localhost.train.booking.FetchTrainRequest;
import localhost.train.booking.Train;

@Component
public class FetchTrainHandler {

    @Resource
    RestHandler rest;
    
    public List<Train> handle(FetchTrainRequest request) {
        List<Train> trains = rest.fetchTrains(request.getOrigin(), request.getDestination(), request.getTime(), request.isIsDepartureTime());

        return trains;
    }
}