package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuTableView {


	public void show() {
		 System.out.println("=============================================================");
	     System.out.println("MENU TABLES :");
	     displayMenuMainTable();
	     System.out.print("Faites votre choix (1-7) : ");
	     int idMenu = ScannerWithCheck.scannerIntUtilisateur(false, 7);
	     getPage(idMenu);
	}

	private void getPage(int idMenu) {
		switch (idMenu) {
	        case 1:
	            CommandeController.getInstance().listeTable();
	            break;
	        case 2:
	            CommandeController.getInstance().ajouterTable();
	            break;
	        case 3:
	            CommandeController.getInstance().modifierTable();
	            break;
	        case 4:
	            CommandeController.getInstance().supprimerTable();
	            break;
	        case 5:
	            CommandeController.getInstance().attribuerTable();
	            break;
	        case 6:
	            CommandeController.getInstance().viderTable();
	            break;
	        case 7:
	            CommandeController.getInstance().openAccueilMenu();
	            break;
		}
	}

	private void displayMenuMainTable() {
		System.out.println(
                "┌───────┬─────────┬──────────┬───────────┬───────────┬───────┬────────┐\n" +
                        "│ Liste │ Ajouter │ Modifier │ Supprimer │ Attribuer │ Vider │ Retour │\n" +
                        "├───────┼─────────┼──────────┼───────────┼───────────┼───────┼────────┤\n" +
                        "│   1   │    2    │    3     │     4     │     5     │   6   │   7    │\n" +
                        "└───────┴─────────┴──────────┴───────────┴───────────┴───────┴────────┘"
        );
	}

}
