package com.train.booking.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.train.booking.model.rest.ReservationRest;
import com.train.booking.model.rest.TrainRest;

import localhost.train.booking.Train;

@Component
public class RestHandler {

    private static final String trainsUrl = "http://restserver/trains";
    private static final String reservUrl = "http://restserver/reservations";
    RestTemplate template = new RestTemplate();

    public List<Train> fetchTrains(String orig, String dest, String time, boolean isDepartureTime) {

        String url = trainsUrl + "/from/" + orig + "/to/" + dest;

        if(isDepartureTime) {
            url += "/departure/"+time;
        } else {
            url += "/arrival/"+time;
        }

        List<Train> trains = new ArrayList<>();
        try {
            TrainRest[] trainArr = template.getForObject(url, TrainRest[].class);
    
            if(trainArr != null) {
                trains = Arrays.asList(trainArr).stream().map(TrainRest::toSoapTrain).collect(Collectors.toList());
            }

        } catch(Exception e) {
            // do nothing
        }

        return trains;
    }

    public int bookTrain(String trainId, String lastName, String firstName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject json = new JSONObject();
        json.put("train_id", trainId);
        json.put("first_name", firstName);
        json.put("last_name", lastName);

        HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

        ReservationRest reserv = null;
        try {
            reserv = template.postForObject(reservUrl, request, ReservationRest.class);
        } catch(Exception e) {
            // do nothing
        }

        int id = -1;
        if(reserv != null && reserv.getId() != null) {
            id = Integer.parseInt(reserv.getId());
        }
        return id;
    }

    public ReservationRest seeBooking(int reservationId) {
        ReservationRest reserv = new ReservationRest();

        try {
            reserv = template.getForObject(reservUrl+"/"+reservationId, ReservationRest.class);
        } catch(Exception e) {
            // do nothing
        }

        return reserv;
    }
}
