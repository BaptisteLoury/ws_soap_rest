package com.train.booking.model.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import localhost.train.booking.Train;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationRest {

    private static final String timeFormat = "yyyy-MM-dd hh:mm";

    @JsonProperty("TRAIN_ID")
    private String id;

    // public Train toSoapTrain() {
    //     Train train = new Train();
    //     train.setId(id);
    //     train.setDestination(destination);
    //     train.setOrigin(origin);
    //     train.setSeatsLeft(Integer.parseInt(seatsLeft));
        
    //     GregorianCalendar calDep = new GregorianCalendar();
    //     GregorianCalendar calArr = new GregorianCalendar();
    //     try {
    //         calDep.setTime(new SimpleDateFormat(timeFormat).parse(getDepartureTime()));
    //         calArr.setTime(new SimpleDateFormat(timeFormat).parse(getArrivalTime()));
    //     } catch (ParseException e) {
    //         calDep.setTimeInMillis(System.currentTimeMillis());
    //         calArr.setTimeInMillis(System.currentTimeMillis());
    //     }
    //     try {
    //         XMLGregorianCalendar depTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDep);
    //         XMLGregorianCalendar arrTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(calArr);

    //         train.setDepartureTime(depTime);
    //         train.setArrivalTime(arrTime);
    //     } catch (DatatypeConfigurationException e) {
    //         // do nothing
    //     }

    //     return train;
    // }
    
}
