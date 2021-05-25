package fr.ul.miage.GenieLogiciel.View.menu;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuCommandeView {

    public void show() {
        System.out.println("=============================================================");
        System.out.println("MENU COMMANDES :");
        displayMenuMainIngredient();
        System.out.print("Faites votre choix (1-7) : ");
        int idMenu = ScannerWithCheck.scannerIntUtilisateur(false, 7);
        getPage(idMenu);
    }

    private void displayMenuMainIngredient() {
        System.out.println(
                        "┌───────┬─────────┬──────────┬────────────┬────────────┬────────────┬──────────┐\n" +
                        "│ Liste │ Ajouter │ Modifier │ Visualiser │  Préparer  │  Facturer  │  Retour  │\n" +
                        "├───────┼─────────┼──────────┼────────────┼────────────┼────────────┼──────────┤\n" +
                        "│   1   │    2    │    3     │     4      │     5      │     6      │     7    │\n" +
                        "└───────┴─────────┴──────────┴────────────┴────────────┴────────────┴──────────┘"
        );
    }

    private void getPage(int idMenu) {
        switch (idMenu) {
            case 1:
                CommandeController.getInstance().listeCommande();
                break;
            case 2:
                CommandeController.getInstance().ajouterCommande();
                break;
            case 3:
                CommandeController.getInstance().modifierCommande();
                break;
            case 4:
                CommandeController.getInstance().visualiserCommandesEntrante();
                break;
            case 5:
                CommandeController.getInstance().preparerPlatCommande();
                break;
            case 6:
                CommandeController.getInstance().facturerCommande();
                break;
            case 7:
                CommandeController.getInstance().openAccueilMenu();
                break;
        }
    }
}
