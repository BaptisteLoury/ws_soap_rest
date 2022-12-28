package com.train.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import localhost.train.booking.GetCountryRequest;
import localhost.train.booking.GetCountryResponse;

@Endpoint
public class TrainBookingEndpoit {
	private static final String NAMESPACE_URI = "http://localhost/train/booking";

	private TrainBookingRepository countryRepository;

	@Autowired
	public TrainBookingEndpoit(TrainBookingRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
}