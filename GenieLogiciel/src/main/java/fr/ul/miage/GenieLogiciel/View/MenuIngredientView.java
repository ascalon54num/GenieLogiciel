package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

public class MenuIngredientView {

    public void show() {
        System.out.println("=============================================================");
        System.out.println("MENU INGREDIENTS :");
        displayMenuMainIngredient();
        System.out.print("Faites votre choix (1-7) : ");
        int idMenu = ScannerWithCheck.scannerIntUtilisateur(false, 7);
        getPage(idMenu);
    }

    private void displayMenuMainIngredient() {
        System.out.println(
                "┌───────┬─────────┬──────────┬───────────┬─────────┬───────┬────────┐\n" +
                        "│ Liste │ Ajouter │ Modifier │ Supprimer │ Remplir │ Vider │ Retour │\n" +
                        "├───────┼─────────┼──────────┼───────────┼─────────┼───────┼────────┤\n" +
                        "│   1   │    2    │    3     │     4     │    5    │   6   │   7    │\n" +
                        "└───────┴─────────┴──────────┴───────────┴─────────┴───────┴────────┘"
        );
    }

    private void getPage(int idMenu) {
        switch (idMenu) {
            case 1:
                CommandeController.getInstance().listeIngredient();
                break;
            case 2:
                CommandeController.getInstance().ajouterIngredient();
                break;
            case 3:
                CommandeController.getInstance().modifierIngredient();
                break;
            case 4:
                CommandeController.getInstance().supprimerIngredient();
                break;
            case 5:
                CommandeController.getInstance().remplirIngredient();
                break;
            case 6:
                CommandeController.getInstance().viderIngredient();
                break;
            case 7:
                CommandeController.getInstance().openAccueilMenu();
                break;
        }
    }
}
