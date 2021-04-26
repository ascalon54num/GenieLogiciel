package fr.ul.miage.GenieLogiciel.controller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import fr.ul.miage.GenieLogiciel.model.User;
import fr.ul.miage.GenieLogiciel.utils.Session;

public class IdentificationController {
	private static final Logger LOG = Logger.getLogger(IdentificationController.class.getName());
	private Scanner sc;

	public IdentificationController(Scanner scan) {
		sc = scan;
	}

	public void connect(String login) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		User u = new User();
		try {
			System.out.println("Connexion en tant que "+login);
			u.findByLogin(login);
			System.out.println("Connexion utilisateur réussie");
			Session.getInstance().setCurrentUser(u);
			System.out.println("Session établie");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.severe("Erreur connexion utilisateur\r\n"+e.getMessage());
		}
	}

}
