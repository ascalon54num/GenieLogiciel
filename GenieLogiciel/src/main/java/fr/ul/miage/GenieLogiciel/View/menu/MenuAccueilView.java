package fr.ul.miage.GenieLogiciel.View.menu;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuAccueilView {

    private static final int NB_CHOIX = 7;

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
                        "┌─────────────┬─────────────┬────────────┬───────┬────────┬─────────┬─────────┐\n" +
                        "│  Commandes  │ Ingrédients │ Catégories │ Plats │ Tables │ Service │ Quitter │\n" +
                        "├─────────────┼─────────────┼────────────┼───────┼────────┼─────────┼─────────┤\n" +
                        "│      1      │     2       │     3      │   4   │   5    │    6    │    7    │\n" +
                        "└─────────────┴─────────────┴────────────┴───────┴────────┴─────────┴─────────┘"
        );
    }

    private void getPage(int idMenu) {
        switch (idMenu) {
            case 1:
                CommandeController.getInstance().openCommandeMenu();
                break;
            case 2:
                CommandeController.getInstance().openIngredientMenu();
                break;
            case 3:
                CommandeController.getInstance().openCategorieMenu();
                break;
            case 4:
                CommandeController.getInstance().openPlatMenu();
                break;
            case 5:
            	CommandeController.getInstance().openTableMenu();
            	break;
            case 6:
                CommandeController.getInstance().openServiceMenu();
                break;
            case 7:
                System.exit(0);
                break;
        }
    }
}
