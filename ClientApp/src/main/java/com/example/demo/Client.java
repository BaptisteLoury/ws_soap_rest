package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.train.booking.wsdl.GetCountryRequest;
import com.train.booking.wsdl.GetCountryResponse;

public class Client extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(Client.class);

  public GetCountryResponse getCountry(String country) {

    GetCountryRequest request = new GetCountryRequest();
    request.setName(country);

    log.info("Requesting location for " + country);

    GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://soap-ws:8080/train-booking/ws/trainBooking", request,
            new SoapActionCallback(
                "http://localhost/train/booking/GetCountryRequest"));

    return response;
  }

}