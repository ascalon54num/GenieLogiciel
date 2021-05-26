package fr.ul.miage.GenieLogiciel.controller;

import java.util.HashMap;

import fr.ul.miage.GenieLogiciel.utils.Consts;
import fr.ul.miage.GenieLogiciel.utils.Session;

public class AccessController {
	
	private HashMap<String, Integer> roles;
	private int currentUserRole;
	
	public AccessController() {
		roles = Consts.getConstants().get("ROLE");
	    currentUserRole = Session.getInstance().getCurrentUser().getRole();
	}
	
	public Boolean checkCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER") || currentUserRole == roles.get("COOK");
	}
	
	public Boolean checkViewCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER") || currentUserRole == roles.get("COOK");
	}
	
	public Boolean checkEditionCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}

	public Boolean checkPreparationCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("COOK");
	}
	
	public Boolean checkCommandesServirAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}
	
	public Boolean checkBillCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}
	
	public Boolean checkIngredientsAccess() {
		return currentUserRole == roles.get("DIRECTOR");
	}
	
	public Boolean checkCategoriesAccess() {
		return currentUserRole == roles.get("DIRECTOR");
	}
	
	public Boolean checkPlatsAccess() {
		return currentUserRole == roles.get("DIRECTOR")||currentUserRole == roles.get("COOK");
	}
	
	public Boolean checkTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER") || currentUserRole == roles.get("SERVER")||currentUserRole == roles.get("SERVICEASSISTANT");
	}
	
	public Boolean checkViewTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER") || currentUserRole == roles.get("SERVER")||currentUserRole == roles.get("SERVICEASSISTANT");
	}
	
	public Boolean checkModifStatusTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER") || currentUserRole == roles.get("SERVER")||currentUserRole == roles.get("SERVICEASSISTANT");
	}
	
	public Boolean checkModifAdvancementMealAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}

	public Boolean checkCRUDTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER");
	}
	
	public Boolean checkAffectationTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER");
	}
	
    public Boolean checkServiceAccess() {
    	return currentUserRole == roles.get("DIRECTOR");
	}
    
    public Boolean checkUtilisateursAccess() {
    	return currentUserRole == roles.get("DIRECTOR");
	}
}
