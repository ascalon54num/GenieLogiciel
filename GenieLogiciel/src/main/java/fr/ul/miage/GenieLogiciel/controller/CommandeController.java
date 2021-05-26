package fr.ul.miage.GenieLogiciel.controller;


import fr.ul.miage.GenieLogiciel.View.*;
import fr.ul.miage.GenieLogiciel.View.menu.*;
import fr.ul.miage.GenieLogiciel.model.CommandExecutor;
import fr.ul.miage.GenieLogiciel.model.DisplayRestrictionAccess;
import fr.ul.miage.GenieLogiciel.model.ExempleOperation;
import fr.ul.miage.GenieLogiciel.model.OperationExempleReceiver;
import fr.ul.miage.GenieLogiciel.model.accueil.DisplayMenuAccueil;
import fr.ul.miage.GenieLogiciel.model.categorie.cmd.*;
import fr.ul.miage.GenieLogiciel.model.commande.cmd.*;
import fr.ul.miage.GenieLogiciel.model.ingredient.cmd.*;
import fr.ul.miage.GenieLogiciel.model.plat.cmd.*;
import fr.ul.miage.GenieLogiciel.model.service.cmd.*;
import fr.ul.miage.GenieLogiciel.model.table.cmd.*;
import fr.ul.miage.GenieLogiciel.model.user.cmd.*;

public class CommandeController {

    private final CommandExecutor executor;

    private static final CommandeController instance = new CommandeController();

    private final CategorieCmd categorieCmd;
    private final ServiceCmd serviceCmd;
    private final CommandeCmd commandeCmd;
    private final PlatCmd platCmd;
    private AccessController accessController;

    private CommandeController() {
        executor = new CommandExecutor();
        categorieCmd = new CategorieCmd();
        platCmd = new PlatCmd();
        serviceCmd = new ServiceCmd();
        commandeCmd = new CommandeCmd();
        accessController = new AccessController();
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
    	if(accessController.checkIngredientsAccess()) {
    		executor.executeOperation(new DisplayMenuIngredient(new MenuIngredientView()));
    	} else {
    		executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
    		openAccueilMenu();
    	}
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


/////////////////
// UTILISATEURS //
/////////////////

    public void openUserMenu() {
    	if(accessController.checkUtilisateursAccess()) {
        	executor.executeOperation(new DisplayMenuUser(new MenuUserView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }

    public void listeUser() {
        executor.executeOperation(new ListeUsers(new UserCmd()));
        openUserMenu();
    }

    public void ajouterUser() {
        executor.executeOperation(new AddUser(new UserCmd()));
        openUserMenu();
    }

    public void modifierUser() {
        executor.executeOperation(new EditUser(new UserCmd()));
        openUserMenu();
    }

    public void supprimerUser() {
        executor.executeOperation(new DeleteUser(new UserCmd()));
        openUserMenu();
    }


    //////////////////
    //     Tables   //
    //////////////////

    public void openTableMenu() {
    	if(accessController.checkTablesAccess()) {
         executor.executeOperation(new DisplayMenuTable(new MenuTableView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }

    public void listeTable() {
    	if(accessController.checkViewTablesAccess()) {
        	executor.executeOperation(new ListeTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    public void ajouterTable() {
    	if(accessController.checkCRUDTablesAccess()) {
        	executor.executeOperation(new AddTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    public void modifierTable() {
    	if(accessController.checkCRUDTablesAccess()) {
        	executor.executeOperation(new ModifyTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    public void changerStatutTable() {
    	if(accessController.checkModifStatusTablesAccess()) {
    		executor.executeOperation(new ModifyStatusTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    public void changerAvancementRepasTable() {
    	if(accessController.checkModifAdvancementMealAccess()) {
        	executor.executeOperation(new ModifyAdvancementMealTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    public void showTable() {
    	if(accessController.checkViewTablesAccess()) {
        	executor.executeOperation(new ShowDetailTables(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    public void supprimerTable() {
    	if(accessController.checkCRUDTablesAccess()) {
        	executor.executeOperation(new DeleteTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    public void affecterTable() {
    	if(accessController.checkAffectationTablesAccess()) {
        	executor.executeOperation(new AffecterTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }


    ////////////////
    // CATEGORIES //
    ////////////////
    public void openCategorieMenu() {
    	if (accessController.checkCategoriesAccess()) {
        	executor.executeOperation(new DisplayMenuCategorie(new MenuCategorieView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
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
    	if(accessController.checkPlatsAccess()) {
          executor.executeOperation(new DisplayMenuPlat(new MenuPlatView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
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


    /////////////
    // SERVICE //
    /////////////
    public void openServiceMenu() {
    	if(accessController.checkServiceAccess()) {
         executor.executeOperation(new DisplayMenuService(new MenuServiceView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
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


    //////////////
    // COMMANDE //
    //////////////
    public void openCommandeMenu() {
    	if(accessController.checkCommandesAccess()) {
         executor.executeOperation(new DisplayMenuCommande(new MenuCommandeView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }

    public void listeCommande() {
    	if(accessController.checkViewCommandesAccess()) {
        	executor.executeOperation(new ListeCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    public void ajouterCommande() {
    	if(accessController.checkEditionCommandesAccess()) {
        	executor.executeOperation(new AddCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    public void modifierCommande() {
    	if(accessController.checkEditionCommandesAccess()) {
        	executor.executeOperation(new EditCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    public void facturerCommande() {
    	if(accessController.checkBillCommandesAccess()) {
        	executor.executeOperation(new FacturerCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    public void preparerPlatCommande() {
    	if(accessController.checkPreparationCommandesAccess()) {
        	executor.executeOperation(new PreparerPlatCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    public void servirPlatCommande() {
    	if(accessController.checkCommandesServirAccess()) {
        	executor.executeOperation(new ServirPlatCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    public void visualiserCommandesEntrante() {
    	if(accessController.checkViewCommandesAccess()) {
        	executor.executeOperation(new VisualiserCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }
}
