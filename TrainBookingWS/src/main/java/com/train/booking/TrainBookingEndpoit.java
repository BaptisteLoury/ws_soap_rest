package com.train.booking;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import localhost.train.booking.GetCountryRequest;
import localhost.train.booking.GetCountryResponse;
import localhost.train.booking.SignUpRequest;
import localhost.train.booking.SignUpResponse;

@Endpoint
public class TrainBookingEndpoit {
	private static final String NAMESPACE_URI = "http://localhost/train/booking";

	@Resource
	private TrainBookingRepository countryRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "signUpRequest")
	@ResponsePayload
	public SignUpResponse signUp(@RequestPayload SignUpRequest request) {
		SignUpResponse response = new SignUpResponse();
		response.setSuccess(true);

		return response;
	}
}