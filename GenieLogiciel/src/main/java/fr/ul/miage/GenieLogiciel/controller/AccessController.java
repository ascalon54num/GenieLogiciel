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
	
	/**
	 * Vérifie le droit d'accès au module des commandes
	 * @return Boolean
	 */
	public Boolean checkCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER") || currentUserRole == roles.get("COOK");
	}
	
	/**
	 * Vérifie droit d'accès à la visualisation et au listing des commandes
	 * @return Boolean
	 */
	public Boolean checkViewCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER") || currentUserRole == roles.get("COOK");
	}
	
	/**
	 * Vérifie droit d'accès à l'ajout et la modification des commandes
	 * @return Boolean
	 */
	public Boolean checkEditionCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}

	/**
	 * Vérifie le droit d'accès à la préparation des commandes
	 * @return Boolean
	 */
	public Boolean checkPreparationCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("COOK");
	}
	
	/**
	 * Vérifie le droit d'accès à la fonction servir des commandes
	 * @return Boolean
	 */
	public Boolean checkCommandesServirAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}
	
	/**
	 * Vérifie le droit d'accès à la facturation des commandes
	 * @return Boolean
	 */
	public Boolean checkBillCommandesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}
	
	/**
	 * Vérifie droit d'accès au module Ingrédient
	 * @return Boolean
	 */
	public Boolean checkIngredientsAccess() {
		return currentUserRole == roles.get("DIRECTOR");
	}
	
	/**
	 * Vérifie droit d'accès au module Catégories
	 * @return Boolean
	 */
	public Boolean checkCategoriesAccess() {
		return currentUserRole == roles.get("DIRECTOR");
	}
	
	/**
	 * Vérifie droit d'accès au module Plats
	 * @return Boolean
	 */
	public Boolean checkPlatsAccess() {
		return currentUserRole == roles.get("DIRECTOR")||currentUserRole == roles.get("COOK");
	}
	
	/**
	 * Vérifie droit d'accès au module Tables
	 * @return Boolean
	 */
	public Boolean checkTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER") || currentUserRole == roles.get("SERVER")||currentUserRole == roles.get("SERVICEASSISTANT");
	}
	
	/**
	 * Vérifie droit d'accès à la visualisation et au listing des tables
	 * @return Boolean
	 */
	public Boolean checkViewTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER") || currentUserRole == roles.get("SERVER")||currentUserRole == roles.get("SERVICEASSISTANT");
	}
	
	/**
	 * Vérifie droit d'accès à la modification du statut des tables (occupée, libre,...)
	 * @return Boolean
	 */
	public Boolean checkModifStatusTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER") || currentUserRole == roles.get("SERVER")||currentUserRole == roles.get("SERVICEASSISTANT");
	}
	
	/**
	 * Vérifie droit d'accès à la modification de l'avancement du repas
	 * @return Boolean
	 */
	public Boolean checkModifAdvancementMealAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("SERVER");
	}

	/**
	 * Vérifie droit d'accès aux opérations CRUD sur les tables
	 * @return Boolean
	 */
	public Boolean checkCRUDTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER");
	}
	
	/**
	 * Vérifie droit d'accès à l'affectation des tables
	 * @return Boolean
	 */
	public Boolean checkAffectationTablesAccess() {
		return currentUserRole == roles.get("DIRECTOR") || currentUserRole == roles.get("HOTELMASTER");
	}
	
	/**
	 * Vérifie droit d'accès au module Service
	 * @return Boolean
	 */
    public Boolean checkServiceAccess() {
    	return currentUserRole == roles.get("DIRECTOR");
	}
    
    /**
     * Vérifie droit d'accès au module Utilisateur
     * @return Boolean
     */
    public Boolean checkUtilisateursAccess() {
    	return currentUserRole == roles.get("DIRECTOR");
	}
}
