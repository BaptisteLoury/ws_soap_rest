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

import com.train.booking.model.rest.TrainRest;

import localhost.train.booking.Train;

@Component
public class RestHandler {

    private static final String trainsUrl = "http://restserver/trains";
    private static final String reservUrl = "http://restserver/reservationsjson";
    RestTemplate template = new RestTemplate();

    public List<Train> fetchTrains(String orig, String dest, String time) {

        String url = trainsUrl + "/from/" + orig + "/to/" + dest;

        TrainRest[] trainArr = template.getForObject(url, TrainRest[].class);

        System.err.println(trainArr);

        List<Train> trains = new ArrayList<>();
        if(trainArr != null) {
            trains = Arrays.asList(trainArr).stream().map(TrainRest::toSoapTrain).collect(Collectors.toList());
        }
        return trains;
    }

    public int bookTrain(String trainId, String lastName, String firstName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject json = new JSONObject();
        json.put("train_id", Integer.parseInt(trainId));
        json.put("first_name", firstName);
        json.put("last_name", lastName);

        HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

        Integer id = template.postForObject(reservUrl, request, Integer.class);

        return id != null ? id : -1;
    }
}
