package com.example.demo;

import java.util.Scanner;

import com.train.booking.wsdl.SignInResponse;
import com.train.booking.wsdl.SignUpResponse;

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
					break;
				case 2:
					break;
				case 3:
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
}
