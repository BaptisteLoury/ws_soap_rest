package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.train.booking.wsdl.GetCountryResponse;

@SpringBootApplication
public class WSClientApp {

	public static void main(String[] args) {
		SpringApplication.run(WSClientApp.class, args);
	}

	@Bean
	CommandLineRunner lookup(Client quoteClient) {
	  return args -> {
		String country = "Spain";
  
		if (args.length > 0) {
		  country = args[0];
		}
		GetCountryResponse response = quoteClient.getCountry(country);
		System.err.println(response.getCountry().getName());
		System.err.println(response.getCountry().getCapital());
		System.err.println(response.getCountry().getPopulation());
		System.err.println(response.getCountry().getCurrency());
	  };
	}
}
