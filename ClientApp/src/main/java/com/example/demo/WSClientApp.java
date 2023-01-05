package com.example.demo;

import java.util.Scanner;

import com.train.booking.wsdl.GetCountryResponse;

public class WSClientApp {

	public static void main(String[] args) {
		Client client = new Client();
		GetCountryResponse response = client.getCountry("Spain");
		System.err.println(response.getCountry().getName());
		System.err.println(response.getCountry().getCapital());
		System.err.println(response.getCountry().getPopulation());
		System.err.println(response.getCountry().getCurrency());

		Scanner sc = new Scanner(System.in);
		String aled = sc.nextLine();
		System.err.println(aled);
		sc.close();
	}
}
