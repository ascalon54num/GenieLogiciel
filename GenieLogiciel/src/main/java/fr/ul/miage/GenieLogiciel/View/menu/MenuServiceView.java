package fr.ul.miage.GenieLogiciel.View.menu;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuServiceView {

    public void show() {
        System.out.println("=============================================================");
        System.out.println("MENU SERVICES :");
        displayMenuMainIngredient();
        System.out.print("Faites votre choix (1-5) : ");
        int idMenu = ScannerWithCheck.scannerIntUtilisateur(false, 5);
        getPage(idMenu);
    }

    private void displayMenuMainIngredient() {
        System.out.println(
                "┌───────┬─────────┬──────────┬───────────┬──────────┐\n" +
                        "│ Liste │ Ajouter │ Modifier │ Supprimer │  Retour  │\n" +
                        "├───────┼─────────┼──────────┼───────────┼──────────┤\n" +
                        "│   1   │    2    │    3     │     4     │     5    │\n" +
                        "└───────┴─────────┴──────────┴───────────┴──────────┘"
        );
    }

    private void getPage(int idMenu) {
        switch (idMenu) {
            case 1:
                CommandeController.getInstance().listeService();
                break;
            case 2:
                CommandeController.getInstance().ajouterService();
                break;
            case 3:
                CommandeController.getInstance().modifierService();
                break;
            case 4:
                CommandeController.getInstance().supprimerService();
                break;
            case 5:
                CommandeController.getInstance().openAccueilMenu();
                break;
        }
    }
}
