package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuAccueilView {

    private static final int NB_CHOIX = 5;

    public void show() {
        System.out.println("=============================================================");
        System.out.println("MENU PRINCIPAL :");
        displayMenuMain();
        System.out.print("Faites votre choix (1-" + NB_CHOIX + ") : ");
        int idMenu = ScannerWithCheck.scannerIntUtilisateur(false, NB_CHOIX);
        getPage(idMenu);
    }

    private void displayMenuMain() {
        System.out.println(
                "┌─────────────┬────────────┬───────┬────────┬─────────┐\n" +
                        "│ Ingrédients │ Catégories │ Plats │ Tables │ Quitter │\n" +
                        "├─────────────┼────────────┼───────┼────────┼─────────┤\n" +
                        "│ 1           │ 2          │   3   │   4    │    5    │\n" +
                        "└─────────────┴────────────┴───────┴────────┴─────────┘"
        );
    }

    private void getPage(int idMenu) {
        switch (idMenu) {
            case 1:
                CommandeController.getInstance().openIngredientMenu();
                break;
            case 2:
                CommandeController.getInstance().openCategorieMenu();
                break;
            case 3:
                CommandeController.getInstance().openPlatMenu();
                break;
            case 4:
            	CommandeController.getInstance().openTableMenu();
            	break;
            case 5:
                System.exit(0);
                break;
        }
    }
}
