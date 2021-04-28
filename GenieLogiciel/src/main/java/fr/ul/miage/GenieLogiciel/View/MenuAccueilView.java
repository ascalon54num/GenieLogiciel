package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuAccueilView {

    public void show() {
        System.out.println("=============================================================");
        System.out.println("MENU PRINCIPAL :");
        displayMenuMain();
        System.out.print("Faites votre choix (1-2) : ");
        int idMenu = ScannerWithCheck.scannerIntUtilisateur(false, 2);
        getPage(idMenu);
    }

    private void displayMenuMain() {
        System.out.println(
                "┌─────────────┬─────────┐\n" +
                        "│ Ingredients │ Quitter │\n" +
                        "├─────────────┼─────────┤\n" +
                        "│      1      │    2    │\n" +
                        "└─────────────┴─────────┘"
        );
    }

    private void getPage(int idMenu) {
        switch (idMenu) {
            case 1:
                CommandeController.getInstance().openIngredientMenu();
                break;

            case 2:
                System.exit(0);
                break;
        }
    }
}
