package fr.ul.miage.GenieLogiciel.utils;

import fr.ul.miage.GenieLogiciel.model.User;

public class Session {

	private static Session instance = new Session();
	private User currentUser;
	
	private Session() {
		// TODO Auto-generated constructor stub
	}
	
	public static Session getInstance() {
		return instance;
	}
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return currentUser ;
	}

	public void setCurrentUser(User u) {
		// TODO Auto-generated method stub
		currentUser = u;
	}

}
