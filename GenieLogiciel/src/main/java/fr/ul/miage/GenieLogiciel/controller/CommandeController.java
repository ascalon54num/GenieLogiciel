package fr.ul.miage.GenieLogiciel.controller;



import fr.ul.miage.GenieLogiciel.model.CommandExecutor;
import fr.ul.miage.GenieLogiciel.model.ExempleOperation;
import fr.ul.miage.GenieLogiciel.model.OperationExempleReceiver;
import fr.ul.miage.GenieLogiciel.model.accueil.DisplayMenuAccueil;
import fr.ul.miage.GenieLogiciel.View.*;
import fr.ul.miage.GenieLogiciel.model.categorie.cmd.*;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.*;
import fr.ul.miage.GenieLogiciel.model.service.cmd.*;
import fr.ul.miage.GenieLogiciel.model.table.cmd.*;
import fr.ul.miage.GenieLogiciel.model.plat.cmd.*;

public class CommandeController {

    private final CommandExecutor executor;

    private static final CommandeController instance = new CommandeController();

    private final CategorieCmd categorieCmd;
    private final ServiceCmd serviceCmd;
    private final PlatCmd platCmd;

    private CommandeController() {
        executor = new CommandExecutor();
        categorieCmd = new CategorieCmd();
        platCmd = new PlatCmd();
        serviceCmd = new ServiceCmd();
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

    ///////////
    // PLATS //
    ///////////
    public void openPlatMenu() {
        executor.executeOperation(new DisplayMenuPlat(new MenuPlatView()));
    }

    public void listePlat() {
        executor.executeOperation(new ListePlat(platCmd));
        openPlatMenu();
    }

    public void ajouterPlat() {
        executor.executeOperation(new AddPlat(platCmd));
        openPlatMenu();
    }

    public void modifierPlat() {
        executor.executeOperation(new EditPlat(platCmd));
        openPlatMenu();
    }

    public void supprimerPlat() {
        executor.executeOperation(new DeletePlat(platCmd));
        openPlatMenu();
    }


    //////////////
    // COMMANDE //
    //////////////
    public void openServiceMenu() {
        executor.executeOperation(new DisplayMenuService(new MenuServiceView()));
    }

    public void listeService() {
        executor.executeOperation(new ListeService(serviceCmd));
        openServiceMenu();
    }

    public void ajouterService() {
        executor.executeOperation(new AddService(serviceCmd));
        openServiceMenu();
    }

    public void modifierService() {
        executor.executeOperation(new EditService(serviceCmd));
        openServiceMenu();
    }

    public void supprimerService() {
        executor.executeOperation(new DeleteService(serviceCmd));
        openServiceMenu();
    }
}
