package fr.ul.miage.GenieLogiciel.controller;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.View.MenuAccueilView;
import fr.ul.miage.GenieLogiciel.View.MenuIngredientView;
import fr.ul.miage.GenieLogiciel.model.CommandExecutor;
import fr.ul.miage.GenieLogiciel.model.ExempleOperation;
import fr.ul.miage.GenieLogiciel.model.OperationExempleReceiver;
import fr.ul.miage.GenieLogiciel.model.accueil.DisplayMenuAccueil;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.AddIngredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.DeleteIngredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.EditIngredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.ListeIngredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.DisplayMenuIngredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.RemplirIngredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.ViderIngredient;

public class CommandeController {

    private final CommandExecutor executor;

    private static CommandeController instance = new CommandeController();

    private CommandeController() {
        executor = new CommandExecutor();

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
}
