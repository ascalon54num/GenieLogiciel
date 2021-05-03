package fr.ul.miage.GenieLogiciel.controller;

import fr.ul.miage.GenieLogiciel.View.*;
import fr.ul.miage.GenieLogiciel.model.CommandExecutor;
import fr.ul.miage.GenieLogiciel.model.ExempleOperation;
import fr.ul.miage.GenieLogiciel.model.OperationExempleReceiver;
import fr.ul.miage.GenieLogiciel.model.accueil.DisplayMenuAccueil;
import fr.ul.miage.GenieLogiciel.model.categorie.cmd.*;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.*;

public class CommandeController {

    private final CommandExecutor executor;

    private static final CommandeController instance = new CommandeController();

    private final CategorieCmd categorieCmd;

    private CommandeController() {
        executor = new CommandExecutor();
        categorieCmd = new CategorieCmd();
    }

    public static CommandeController getInstance() {
        return instance;
    }

    public void executeTest() {
        executor.executeOperation(new ExempleOperation(new OperationExempleReceiver()));
    }

    public void openAccueilMenu() {
        executor.executeOperation(new DisplayMenuAccueil(new MenuAccueilView()));
    }


    /////////////////
    // INGREDIENTS //
    /////////////////
    public void openIngredientMenu() {
        executor.executeOperation(new DisplayMenuIngredient(new MenuIngredientView()));
    }

    public void listeIngredient() {
        executor.executeOperation(new ListeIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    public void ajouterIngredient() {
        executor.executeOperation(new AddIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    public void modifierIngredient() {
        executor.executeOperation(new EditIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    public void supprimerIngredient() {
        executor.executeOperation(new DeleteIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    public void remplirIngredient() {
        executor.executeOperation(new RemplirIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    public void viderIngredient() {
        executor.executeOperation(new ViderIngredient(new IngredientCmd()));
        openIngredientMenu();
    }


    ////////////////
    // CATEGORIES //
    ////////////////
    public void openCategorieMenu() {
        executor.executeOperation(new DisplayMenuCategorie(new MenuCategorieView()));
    }

    public void listeCategorie() {
        executor.executeOperation(new ListeCategorie(categorieCmd));
        openCategorieMenu();
    }

    public void ajouterCategorie() {
        executor.executeOperation(new AddCategorie(categorieCmd));
        openCategorieMenu();
    }

    public void modifierCategorie() {
        executor.executeOperation(new EditCategorie(categorieCmd));
        openCategorieMenu();
    }

    public void supprimerCategorie() {
        executor.executeOperation(new DeleteCategorie(categorieCmd));
        openCategorieMenu();
    }
}
