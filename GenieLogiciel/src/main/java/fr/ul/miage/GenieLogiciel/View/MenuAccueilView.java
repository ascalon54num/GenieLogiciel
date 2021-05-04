package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuAccueilView {

    public void show() {
        System.out.println("=============================================================");
        System.out.println("MENU PRINCIPAL :");
        displayMenuMain();
        System.out.print("Faites votre choix (1-4) : ");
        int idMenu = ScannerWithCheck.scannerIntUtilisateur(false, 4);
        getPage(idMenu);
    }

    private void displayMenuMain() {
        System.out.println(
                "┌─────────────┬────────┬─────────┐\n" +
                        "│ Ingredients │ Tables │ Quitter │\n" +
                        "├─────────────┼────────┼─────────┤\n" +
                        "│      1      │    3   │    4    │\n" +
                        "└─────────────┴────────┴─────────┘"
        );
    }

    private void getPage(int idMenu) {
        switch (idMenu) {
            case 1:
                CommandeController.getInstance().openIngredientMenu();
                break;
            case 3:
            	CommandeController.getInstance().openTableMenu();
            	break;
            case 4:
                System.exit(0);
                break;
        }
    }
}
