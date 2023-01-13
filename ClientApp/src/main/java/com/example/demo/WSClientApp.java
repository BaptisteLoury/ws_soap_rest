package com.example.demo;

import java.util.Scanner;

import com.train.booking.wsdl.BookTrainResponse;
import com.train.booking.wsdl.FetchTrainResponse;
import com.train.booking.wsdl.SeeBookingResponse;
import com.train.booking.wsdl.SignInResponse;
import com.train.booking.wsdl.SignUpResponse;
import com.train.booking.wsdl.Train;

public class WSClientApp {

	private static Scanner sc = new Scanner(System.in);
	private Client client = new Client();

	public static void main(String[] args) {
		WSClientApp app = new WSClientApp();

		app.showDefautMenu();

		sc.close();
	}

	private void showDefautMenu() {
		boolean keep = true;

		while (keep) {
			System.out.println("1. Se connecter");
			System.out.println("2. S'inscire");
			System.out.println("3. Quitter");

			switch (sc.nextInt()) {
				case 1:
					keep = 	loginMenu();
					break;
				case 2:
					signUpMenu();
					break;
				default:
					System.out.println("Fermeture de l'application");
					keep = false;
					break;
			}
		}
	}

	private void signUpMenu() {
		System.out.println("\n\n\n");

		System.out.println("Inscription");
		System.out.println("Saisir un email :");

		sc.nextLine();
		String email = sc.nextLine();

		System.out.println("Saisir un prénom :");

		String firstName = sc.nextLine();

		System.out.println("Saisir un nom :");

		String lastName = sc.nextLine();

		System.out.println("Saisir votre date de naissance au format \"jour/mois/année\" :");

		String birthDate = sc.nextLine();

		System.out.println("Saisir un mot de passe :");

		String password = new String(System.console().readPassword());

		SignUpResponse response = client.signUp(email, password, lastName, firstName, birthDate);

		if (response.isSuccess()) {
			System.out.println("Inscription effectuée avec succès !");
		} else {
			System.out.println("Cet email est déjà utilisé ou la date n'a pas été saisie au bon format !");
		}
	}

	private boolean loginMenu() {
		System.out.println("\n\n\n");

		boolean keep = true;

		System.out.println("Connexion");
		System.out.println("Saisir votre email :");

		sc.nextLine();
		String email = sc.nextLine();

		System.out.println("Saisir votre mot de passe :");

		String password = new String(System.console().readPassword());

		SignInResponse response = client.signIn(email, password);

		System.out.println(response.getToken());

		if("err".equals(response.getToken())) {
			System.out.println("Email ou mot de passe erroné");
		} else {
			keep = mainMenu(response.getToken());
		}

		return keep;
	}

	private boolean mainMenu(String token) {
		System.out.println("\n\n\n");

		boolean keep = true;
		boolean stay = true;

		while(stay) {
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1. Chercher un train");
			System.out.println("2. Réserver un ticket");
			System.out.println("3. Voir vos réservations");
			System.out.println("4. Se déconnecter");
			System.out.println("5. Quitter l'application");

			switch(sc.nextInt()) {
				case 1:
					fetchTrain();
					break;
				case 2:
					bookTrain(token);
					break;
				case 3:
					seeBooking(token);
					break;
				case 4:
					System.out.println("Déconnexion");
					stay = false;
					break;
				default:
					System.out.println("Fermeture de l'application");
					stay = false;
					keep = false;
					break;
			}
		}

		return keep;
	}

	private void fetchTrain() {
		System.out.println("\n\n\n");

		System.out.println("Saisir une gare de départ :");
		String origin = sc.nextLine();

		System.out.println("Saisir une gare d'arrivée :");
		String destination = sc.nextLine();

		int dateType = 0;

		while(dateType != 1 || dateType != 2) {
			System.out.println("Allez-vous saisir une date de départ (1) ou d'arrivée (2) ?");
			dateType = sc.nextInt();
		}

		boolean valid = false;
		String date = "";

		while(!valid) {
			switch(dateType) {
				case 1:
					System.out.println("Saisir une date de départ (sous la forme jj/MM/AAAA)");
					break;
				case 2:
					System.out.println("Saisir une date d'arrivée (sous la forme jj/MM/AAAA)");
					break;
				default:
					break;
			}
			date = sc.nextLine();
			valid = client.isDateValid(date);
		}

		valid = false;
		String time = "";

		while(!valid) {
			switch(dateType) {
				case 1:
					System.out.println("Saisir une heure de départ (sous la forme hh:mm)");
					break;
				case 2:
					System.out.println("Saisir une heure d'arrivée (sous la forme hh:mm)");
					break;
				default:
					break;
			}
			time = sc.nextLine();
			valid = client.isTimeValid(time);
		}

		FetchTrainResponse response = client.fetchTrain(origin, destination, date, time, dateType == 1);

		System.out.println("\n\n\n");
		System.out.println("Trains au départ de "+origin+" et à l'arrivée de "+destination+"\n");
		for(Train train : response.getTrains()) {
			System.out.println("Identifiant : "+train.getId()+" | Date de départ : "+train.getDepartureTime()+" | Date d'arrivée : "+train.getArrivalTime()+" | Places restantes : "+train.getSeatsLeft());
		}

		System.out.println("<Appuyer sur une touche pour revenir au menu>");
		sc.nextLine();

	}

	private void bookTrain(String token) {
		System.out.println("\n\n\n");
		System.out.println("Réservation");

		System.out.println("Saisir l'identifiant du train :");
		String trainId = sc.nextLine();

		BookTrainResponse response = client.bookTrain(trainId, token);

	}

	private void seeBooking(String token) {

		SeeBookingResponse response = client.seeBooking(token);

		System.out.println("\n\n\n");
		System.out.println("Vos réservations :");
		for(Train train : response.getTrains()) {
			System.out.println("Gare de départ : "+train.getOrigin()+" | Gare d'arrivée : "+train.getDestination()+" : "+train.getId()+" | Date de départ : "+train.getDepartureTime()+" | Date d'arrivée : "+train.getArrivalTime());
		}

	}
}
