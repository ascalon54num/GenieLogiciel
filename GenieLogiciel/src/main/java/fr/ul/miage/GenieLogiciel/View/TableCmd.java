package fr.ul.miage.GenieLogiciel.View;

import static fr.ul.miage.GenieLogiciel.utils.Constantes.MAX_COUVERTS;
import static fr.ul.miage.GenieLogiciel.utils.Constantes.STATUS_TABLE;
import static fr.ul.miage.GenieLogiciel.utils.Constantes.AVANCEMENT_REPAS;

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

	public Map<Integer, Table> liste() {
		System.out.println();
        System.out.println("Liste des tables :");
        TableRepository tableRepository = new TableRepository();
        Map<Integer, Table> tables = tableRepository.findAll();
        tables.forEach((id, table) -> {
        	System.out.println(table);
        });
        return tables;
	}
	
	private Map<Integer, Table> listeOccupees() {
		System.out.println();
        System.out.println("Liste des tables :");
        TableRepository tableRepository = new TableRepository();
        Map<Integer, Table> tables = tableRepository.findAllOccupee();
        tables.forEach((id, table) -> {
        	System.out.println(table);
        });
        return tables;
	}
	
	public void add() {
        System.out.println();
        System.out.print("Nombre de couvert : ");
        int nbCouverts = ScannerWithCheck.scannerIntUtilisateur(false, MAX_COUVERTS);
        String statut = "Libre";
        String advancementMeal = AVANCEMENT_REPAS[0];

        Table newTable = new Table();
        newTable.setNbCouvert(nbCouverts).setStatut(statut).setAdvancementMeal(advancementMeal).save();
        System.out.println("Table ajoutée = " + newTable);
    }

	public void affecter() {
		boolean continu= true;
		ArrayList<String> authorizedValues = new ArrayList<String>();
    	authorizedValues.add("y");
    	authorizedValues.add("n");
		while (continu) {
			Map<Integer, Table> tables = liste();
	        System.out.println("Choisir la table à affecter parmis la liste :");
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

	public void supprimer() {
		Map<Integer, Table> tables = liste();
        System.out.print("Id de la table à supprimer : ");
        int idTable = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (tables.containsKey(idTable)) {
            Table table = tables.get(idTable);
            table.delete();
            System.out.println("Table supprimée = " + table);
        } else {
            System.err.println("Erreur de saisie");
        }
	}

	public void edit() {
		Map<Integer, Table> tables = liste();
        System.out.print("Id de la table à modifier : ");
        int idTable = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (tables.containsKey(idTable)) {
            Table table = tables.get(idTable);
            modifierStatus(table);
            System.out.print("Nouveau nombre de couverts(" + table.getNbCouvert() + ") : ");
            table.setNbCouvert(ScannerWithCheck.scannerIntUtilisateur(false, MAX_COUVERTS));
            modifierAvancementMeal(table);
            table.save();

            System.out.println("Table modifiée = " + table);
        } else {
            System.err.println("Erreur de saisie");
        }
	}

	private void modifierStatus(Table table) {
		System.out.println("Liste des status:");
		for(int i = 0;i< STATUS_TABLE.length;i++) {
			System.out.println(i+" "+STATUS_TABLE[i]);
		}
		System.out.print("Nouveau status ("+table.getStatut()+") :");
		table.setStatut(STATUS_TABLE[ScannerWithCheck.scannerIntUtilisateur(true, STATUS_TABLE.length-1)]);	
		System.out.println("Status modifié : "+table.getStatut());
	}

	public void changeStatus() {
		Map<Integer, Table> tables = liste();
        System.out.print("Id de la table à modifier : ");
        int idTable = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (tables.containsKey(idTable)) {
            Table table = tables.get(idTable);
            modifierStatus(table);
            table.save();
        } else {
            System.err.println("Erreur de saisie");
        }
	}

	public void showDetailTables() {
		Map<Integer, Table> tables = liste();
        System.out.print("Id de la table à visualiser : ");
        int idTable = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (tables.containsKey(idTable)) {
            Table table = tables.get(idTable);
            System.out.println();
            System.out.println(table);
          	 Affectation affectation = new AffectationRepository().findOneByTable(table.getId());
          	 System.out.println("Serveur affecté:"+ (affectation != null ? affectation.getServeur() : "aucun"));
          	 System.out.println("-------------------------------------------------------------");
        }
	}

	public void changeAdvancementMeal() {
		Map<Integer, Table> tables = listeOccupees();
		System.out.print("Id de la table à modifier : ");
        int idTable = ScannerWithCheck.scannerIntUtilisateur(false, -1);
        if (tables.containsKey(idTable)) {
            Table table = tables.get(idTable);
            modifierAvancementMeal(table);
            table.save();
        } else {
            System.err.println("Erreur de saisie");
        }
	}

	private void modifierAvancementMeal(Table table) {
		System.out.println("Liste des étapes d'un repas:");
		for(int i = 0;i< AVANCEMENT_REPAS.length;i++) {
			System.out.println(i+" "+AVANCEMENT_REPAS[i]);
		}
		System.out.print("Nouvelle étape ("+table.getAdvancementMeal()+") :");
		table.setAdvancementMeal(AVANCEMENT_REPAS[ScannerWithCheck.scannerIntUtilisateur(true, AVANCEMENT_REPAS.length-1)]);	
		System.out.println("Etape repas modifiée : "+table.getAdvancementMeal());
	}

}
