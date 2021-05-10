package fr.ul.miage.GenieLogiciel.View;

import static fr.ul.miage.GenieLogiciel.utils.Constantes.MAX_COUVERTS;

import java.util.ArrayList;
import java.util.Map;

import fr.ul.miage.GenieLogiciel.model.affectation.Affectation;
import fr.ul.miage.GenieLogiciel.model.affectation.AffectationRepository;
import fr.ul.miage.GenieLogiciel.model.table.Table;
import fr.ul.miage.GenieLogiciel.model.table.TableRepository;
import fr.ul.miage.GenieLogiciel.model.user.User;
import fr.ul.miage.GenieLogiciel.model.user.UserRepository;
import fr.ul.miage.GenieLogiciel.utils.Consts;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;



public class TableCmd {

	public TableCmd() {
		// TODO Auto-generated constructor stub
	}

	public void liste() {
		System.out.println();
        System.out.println("Liste des tables :");
        TableRepository tableRepository = new TableRepository();
        Map<Integer, Table> tables = tableRepository.findAll();
        tables.forEach((id, table) -> System.out.println(table));
	}
	
	public void add() {
        System.out.println();
        System.out.print("Nombre de couvert : ");
        int nbCouverts = ScannerWithCheck.scannerIntUtilisateur(false, MAX_COUVERTS);
        String statut = "Libre";

        Table newTable = new Table();
        newTable.setNbCouvert(nbCouverts).setStatut(statut).save();
        System.out.println("Table ajoutée = " + newTable);
    }

	public void affecter() {
		boolean continu= true;
		ArrayList<String> authorizedValues = new ArrayList<String>();
    	authorizedValues.add("y");
    	authorizedValues.add("n");
		while (continu) {
			System.out.println();
			System.out.println("Choisir la table à affecter parmis la liste :");
			TableRepository tableRepository = new TableRepository();
	        Map<Integer, Table> tables = tableRepository.findAll();
	        tables.forEach((id, table) -> System.out.println(table));
	        int tableIdChoiced = ScannerWithCheck.scannerIntUtilisateur(false,-1);
	        while (!tables.containsKey(tableIdChoiced)) {
	        	System.out.println("Erreur table inconnue, choisissez une table de la liste:");
	        	tables.forEach((id, table) -> System.out.println(table));
	        	tableIdChoiced = ScannerWithCheck.scannerIntUtilisateur(false,-1);
	        }
	        boolean attribution = false;
	        Affectation affectation = new AffectationRepository().findOneByTable(tableIdChoiced);
	        if(affectation != null) {
	        	System.out.println("La table est déjà affectée, voulez-vous la réaffecter (y/n) :");
	        	if (ScannerWithCheck.scannerStringPreciseValue(1, authorizedValues).equals("y")) {
	        		attribution = true;
	        	}
	        } else {
	        	affectation = new Affectation().setIdTable(tableIdChoiced);
	        	attribution = true;
	        }
	        
	        if(attribution) {
	        	int serveurIdChoiced = getServeur();
	        	affectation.setIdServeur(serveurIdChoiced);
	        	affectation.save();
	        	System.out.println("Affectation effectuée");
	        }
	        
	        System.out.println("Souhaitez-vous continuer les affectations de table (y/n) ?");
	        if (ScannerWithCheck.scannerStringPreciseValue(1, authorizedValues).equals("n")) {
        		continu = false;
        	}
		}
        
	}

	private int getServeur() {
		System.out.println();
		System.out.println("Choisir le serveur auquel la table va être affectée parmis la liste :");
		UserRepository userRepository = new UserRepository();
		Map<Integer, User>  users = userRepository.findByRole(Consts.getConstants().get("ROLE").get("SERVER"));
		users.forEach((id, user) -> System.out.println(user));
		int userIdChoiced = ScannerWithCheck.scannerIntUtilisateur(false,-1);
		while (!users.containsKey(userIdChoiced)) {
			System.out.println("Erreur utilisateur inconnu ou n'est pas un serveur, choisissez un utilisateur de la liste:");
        	users.forEach((id, user) -> System.out.println(user));
        	userIdChoiced = ScannerWithCheck.scannerIntUtilisateur(false,-1);
		}
		return userIdChoiced;
	}
}
