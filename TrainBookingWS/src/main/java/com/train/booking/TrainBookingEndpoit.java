package com.train.booking;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.train.booking.controller.BookTrainHandler;
import com.train.booking.controller.FetchTrainHandler;
import com.train.booking.controller.SeeBookingHandler;
import com.train.booking.controller.SignInHandler;
import com.train.booking.controller.SignUpHandler;

import localhost.train.booking.BookTrainRequest;
import localhost.train.booking.BookTrainResponse;
import localhost.train.booking.FetchTrainRequest;
import localhost.train.booking.FetchTrainResponse;
import localhost.train.booking.SeeBookingRequest;
import localhost.train.booking.SeeBookingResponse;
import localhost.train.booking.SignInRequest;
import localhost.train.booking.SignInResponse;
import localhost.train.booking.SignUpRequest;
import localhost.train.booking.SignUpResponse;

@Endpoint
public class TrainBookingEndpoit {
	private static final String NAMESPACE_URI = "http://localhost/train/booking";

	@Resource
	private SignUpHandler signUp;
	@Resource
	private SignInHandler signIn;
	@Resource
	private BookTrainHandler bookTrain;
	@Resource
	private FetchTrainHandler fetchTrain;
	@Resource
	private SeeBookingHandler seeBooking;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "signUpRequest")
	@ResponsePayload
	public SignUpResponse signUp(@RequestPayload SignUpRequest request) {
		SignUpResponse response = new SignUpResponse();
		response.setSuccess(signUp.handle(request));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "signInRequest")
	@ResponsePayload
	public SignInResponse signIn(@RequestPayload SignInRequest request) {
		SignInResponse response = new SignInResponse();
		response.setToken(signIn.handle(request));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "fetchTrainRequest")
	@ResponsePayload
	public FetchTrainResponse fetchTrain(@RequestPayload FetchTrainRequest request) {
		FetchTrainResponse response = new FetchTrainResponse();
		response.getTrains().addAll(fetchTrain.handle(request));
		

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "bookTrainRequest")
	@ResponsePayload
	public BookTrainResponse bookTrain(@RequestPayload BookTrainRequest request) {
		BookTrainResponse response = new BookTrainResponse();
		response.setSuccess(bookTrain.handle(request));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "seeBookingRequest")
	@ResponsePayload
	public SeeBookingResponse seeBooking(@RequestPayload SeeBookingRequest request) {
		SeeBookingResponse response = seeBooking.handle(request);

		return response;
	}
}