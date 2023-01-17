package com.train.booking.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import localhost.train.booking.Reservation;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationRest {

    @JsonProperty("RESERVATION_ID")
    private String id;

    @JsonProperty("RESERVATION_FIRSTNAME")
    private String firstName;

    @JsonProperty("RESERVATION_LASTNAME")
    private String lastName;

    @JsonProperty("TRAIN")
    private TrainRest train;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public TrainRest getTrain() {
        return train;
    }

    public Reservation toSoap() {
        Reservation reservation = new Reservation();

        reservation.setFirstName(firstName);
        reservation.setLastName(lastName);
        reservation.setTrain(train.toSoapTrain());

        return reservation;
    }
    
}
