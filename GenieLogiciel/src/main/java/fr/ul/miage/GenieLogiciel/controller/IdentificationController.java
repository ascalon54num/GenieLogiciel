package fr.ul.miage.GenieLogiciel.controller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import fr.ul.miage.GenieLogiciel.View.IdentificationView;
import fr.ul.miage.GenieLogiciel.model.user.User;
import fr.ul.miage.GenieLogiciel.model.user.UserRepository;
import fr.ul.miage.GenieLogiciel.utils.Session;

public class IdentificationController {
	private static final Logger LOG = Logger.getLogger(IdentificationController.class.getName());
	private IdentificationView view;

	public IdentificationController(Scanner scan) {
		 view = new IdentificationView(scan);
	}
	
	public void initIdentification() {
		String login = view.askLogin();
		connect(login);
	}

	public void connect(String login) {
		
		try {
			System.out.println("Connexion en tant que "+login);
			User u = new UserRepository().findByLogin(login);
			if(u != null) {
				System.out.println("Connexion utilisateur réussie");
				Session.getInstance().setCurrentUser(u);
				System.out.println("Session établie");
			} else {
				System.out.println("Login inconnu");
			}
		} catch (SQLException|ClassNotFoundException |InstantiationException |IllegalAccessException e) {
			// TODO Auto-generated catch block
			LOG.severe("Erreur connexion utilisateur\r\n"+e.getMessage());
		}
	}

}
