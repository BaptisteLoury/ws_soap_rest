package com.train.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TrainBookingWS extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TrainBookingWS.class, args);
	}
}