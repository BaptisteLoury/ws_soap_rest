package com.train.booking.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationRest {

    @JsonProperty("RESERVATION_ID")
    private String id;

    public String getId() {
        return id;
    }
    
}
