package fr.ul.miage.GenieLogiciel.controller;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.View.MenuAccueilView;
import fr.ul.miage.GenieLogiciel.View.MenuIngredientView;
import fr.ul.miage.GenieLogiciel.View.MenuTableView;
import fr.ul.miage.GenieLogiciel.View.TableCmd;
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
import fr.ul.miage.GenieLogiciel.model.table.cmd.AddTable;
import fr.ul.miage.GenieLogiciel.model.table.cmd.AffecterTable;
import fr.ul.miage.GenieLogiciel.model.table.cmd.DisplayMenuTable;
import fr.ul.miage.GenieLogiciel.model.table.cmd.ListeTable;

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
    
    //////////////////
   //     Tables   //
  //////////////////
    
    public void openTableMenu() {
		executor.executeOperation(new DisplayMenuTable(new MenuTableView()));
	}

	public void listeTable() {
		executor.executeOperation(new ListeTable(new TableCmd()));
        openTableMenu();
	}

	public void ajouterTable() {
		executor.executeOperation(new AddTable(new TableCmd()));
        openTableMenu();
	}

	public void modifierTable() {
		// TODO Auto-generated method stub
		
	}
	
	public void changerStatutTable( ) {
		
	}
	
	public void showTable( ) {
		
	}

	public void supprimerTable() {
		// TODO Auto-generated method stub
		
	}

	public void affecterTable() {
		executor.executeOperation(new AffecterTable(new TableCmd()));
        openTableMenu();
	}

}
