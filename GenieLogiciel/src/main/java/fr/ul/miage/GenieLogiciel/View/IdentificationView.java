package fr.ul.miage.GenieLogiciel.View;

import java.util.Scanner;

public class IdentificationView {
	private Scanner sc;
	public IdentificationView(Scanner scan) {
		sc = scan;
	}
	
	public String askLogin() {
		System.out.println("==========Identifiez-vous===========");
		System.out.println("Veuillez renseigner votre login :");
		String login = sc.next();
		while(login == "" || login == null) {
			System.out.println("Veuillez renseigner votre login :");
			login = sc.next();
		}
		System.out.println("====================================");
		return login;
	}

}
