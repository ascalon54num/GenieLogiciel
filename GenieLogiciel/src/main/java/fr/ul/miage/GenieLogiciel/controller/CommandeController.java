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

    /**
     * Execute l'affichage du menu principale
     */
    public void openAccueilMenu() {
        executor.executeOperation(new DisplayMenuAccueil(new MenuAccueilView()));
    }


    /////////////////
    // INGREDIENTS //
    /////////////////
    /**
     * Execute l'affichage du menu du module Ingredient
     */
    public void openIngredientMenu() {
    	if(accessController.checkIngredientsAccess()) {
    		executor.executeOperation(new DisplayMenuIngredient(new MenuIngredientView()));
    	} else {
    		executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
    		openAccueilMenu();
    	}
    }
    /**
     * Affiche la liste des ingrédients en base
     */
    public void listeIngredient() {
        executor.executeOperation(new ListeIngredient(new IngredientCmd()));
        openIngredientMenu();
    }
    
    /**
     * Execute la commande d'ajout d'ingredient
     */
    public void ajouterIngredient() {
        executor.executeOperation(new AddIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    /**
     * Execute la commande de modification d'ingredient
     */
    public void modifierIngredient() {
        executor.executeOperation(new EditIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    /**
     * Execute la commande de suppression d'ingredient
     */
    public void supprimerIngredient() {
        executor.executeOperation(new DeleteIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    /**
     * Execute la commande pour remplir le stock d'ingredients
     */
    public void remplirIngredient() {
        executor.executeOperation(new RemplirIngredient(new IngredientCmd()));
        openIngredientMenu();
    }

    /**
     * Execute la commande pour vider le stock d'ingredients
     */
    public void viderIngredient() {
        executor.executeOperation(new ViderIngredient(new IngredientCmd()));
        openIngredientMenu();
    }


/////////////////
// UTILISATEURS //
/////////////////
    /**
     * Affiche le menu du module Utilisateur
     */
    public void openUserMenu() {
    	if(accessController.checkUtilisateursAccess()) {
        	executor.executeOperation(new DisplayMenuUser(new MenuUserView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }

    /**
     * Affiche la liste des Utilisateurs en base
     */
    public void listeUser() {
        executor.executeOperation(new ListeUsers(new UserCmd()));
        openUserMenu();
    }

    /**
     * Execute la commande d'ajout d'utilisateurs
     */
    public void ajouterUser() {
        executor.executeOperation(new AddUser(new UserCmd()));
        openUserMenu();
    }

    /**
     * Execute la commande de modification d'utilisateurs
     */
    public void modifierUser() {
        executor.executeOperation(new EditUser(new UserCmd()));
        openUserMenu();
    }

    /**
     * Execute la commande de suppression d'utilisateurs
     */
    public void supprimerUser() {
        executor.executeOperation(new DeleteUser(new UserCmd()));
        openUserMenu();
    }


    //////////////////
    //     Tables   //
    //////////////////
    /**
     * Affiche le menu du module table
     */
    public void openTableMenu() {
    	if(accessController.checkTablesAccess()) {
         executor.executeOperation(new DisplayMenuTable(new MenuTableView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }
    /**
     * Affiche la liste des tables en base
     */
    public void listeTable() {
    	if(accessController.checkViewTablesAccess()) {
        	executor.executeOperation(new ListeTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    /**
     * Execute la commande d'ajout de table
     */
    public void ajouterTable() {
    	if(accessController.checkCRUDTablesAccess()) {
        	executor.executeOperation(new AddTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    /**
     * Execute la commande de modification de table
     */
    public void modifierTable() {
    	if(accessController.checkCRUDTablesAccess()) {
        	executor.executeOperation(new ModifyTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }
    
    /**
     * Execute la commande de changement de statut des tables
     */
    public void changerStatutTable() {
    	if(accessController.checkModifStatusTablesAccess()) {
    		executor.executeOperation(new ModifyStatusTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    /**
     * Execute la commande de changement de l'avancement du repas des tables
     */
    public void changerAvancementRepasTable() {
    	if(accessController.checkModifAdvancementMealAccess()) {
        	executor.executeOperation(new ModifyAdvancementMealTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    /**
     * Execute la commande de visualisation des tables
     */
    public void showTable() {
    	if(accessController.checkViewTablesAccess()) {
        	executor.executeOperation(new ShowDetailTables(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    /**
     * Execute la commande de suppression des tables
     */
    public void supprimerTable() {
    	if(accessController.checkCRUDTablesAccess()) {
        	executor.executeOperation(new DeleteTable(new TableCmd()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openTableMenu();
    }

    /**
     * Execute la commande d'affectation des tables
     */
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
    /**
     * Affiche le menu du module Categorie
     */
    public void openCategorieMenu() {
    	if (accessController.checkCategoriesAccess()) {
        	executor.executeOperation(new DisplayMenuCategorie(new MenuCategorieView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }

    /**
     * Execute l'affichage de la liste des catégories
     */
    public void listeCategorie() {
        executor.executeOperation(new ListeCategorie(categorieCmd));
        openCategorieMenu();
    }

    /**
     * Execute la commande d'ajout des catégories
     */
    public void ajouterCategorie() {
        executor.executeOperation(new AddCategorie(categorieCmd));
        openCategorieMenu();
    }

    /**
     * Execute la commande de modification des catégories
     */
    public void modifierCategorie() {
        executor.executeOperation(new EditCategorie(categorieCmd));
        openCategorieMenu();
    }

    /**
     * Execute la commande de suppression des catégories
     */
    public void supprimerCategorie() {
        executor.executeOperation(new DeleteCategorie(categorieCmd));
        openCategorieMenu();
    }

    ///////////
    // PLATS //
    ///////////
    /**
     * Affichage menu du module Plats
     */
    public void openPlatMenu() {
    	if(accessController.checkPlatsAccess()) {
          executor.executeOperation(new DisplayMenuPlat(new MenuPlatView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }
    
    /**
     * Execute l'affichage de la liste des plats en base
     */
    public void listePlat() {
        executor.executeOperation(new ListePlat(platCmd));
        openPlatMenu();
    }

    /**
     * Execute la commande d'ajout des plats
     */
    public void ajouterPlat() {
        executor.executeOperation(new AddPlat(platCmd));
        openPlatMenu();
    }

    /**
     * Execute la commande de modification des plats
     */
    public void modifierPlat() {
        executor.executeOperation(new EditPlat(platCmd));
        openPlatMenu();
    }

    /**
     * Execute la commande de suppression des plats
     */
    public void supprimerPlat() {
        executor.executeOperation(new DeletePlat(platCmd));
        openPlatMenu();
    }


    /////////////
    // SERVICE //
    /////////////
    /**
     * Affichage du menu du module Service
     */
    public void openServiceMenu() {
    	if(accessController.checkServiceAccess()) {
         executor.executeOperation(new DisplayMenuService(new MenuServiceView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }
    
    
    /**
     * Execute l'affichage de la liste des Services en base
     */
    public void listeService() {
        executor.executeOperation(new ListeService(serviceCmd));
        openServiceMenu();
    }

    /**
     * Execute la commande d'ajout des services
     */
    public void ajouterService() {
        executor.executeOperation(new AddService(serviceCmd));
        openServiceMenu();
    }

    /**
     * Execute la commande de modification des services
     */
    public void modifierService() {
        executor.executeOperation(new EditService(serviceCmd));
        openServiceMenu();
    }
    
    /**
     * Execute la commande de suppression des services
     */
    public void supprimerService() {
        executor.executeOperation(new DeleteService(serviceCmd));
        openServiceMenu();
    }


    //////////////
    // COMMANDE //
    //////////////
    /**
     * Affichage du menu du module Commande
     */
    public void openCommandeMenu() {
    	if(accessController.checkCommandesAccess()) {
         executor.executeOperation(new DisplayMenuCommande(new MenuCommandeView()));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
			openAccueilMenu();
		}
    }
    
    /**
     * Execute l'affichage de la liste des commandes en base
     */
    public void listeCommande() {
    	if(accessController.checkViewCommandesAccess()) {
        	executor.executeOperation(new ListeCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    /**
     * Execute la commande d'ajout des commandes
     */
    public void ajouterCommande() {
    	if(accessController.checkEditionCommandesAccess()) {
        	executor.executeOperation(new AddCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    /**
     * Execute la commande de modification des commandes
     */
    public void modifierCommande() {
    	if(accessController.checkEditionCommandesAccess()) {
        	executor.executeOperation(new EditCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }
    
    /**
     * Execute la commande de facturation des commandes
     */
    public void facturerCommande() {
    	if(accessController.checkBillCommandesAccess()) {
        	executor.executeOperation(new FacturerCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    /**
     * Execute la fonction de préparation des commandes (par le cuisinier)
     */
    public void preparerPlatCommande() {
    	if(accessController.checkPreparationCommandesAccess()) {
        	executor.executeOperation(new PreparerPlatCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    /**
     * Execute la commande d'affichage des commandes à servir (prêtes) et confirmation d'avoir délivré la commande.
     */
    public void servirPlatCommande() {
    	if(accessController.checkCommandesServirAccess()) {
        	executor.executeOperation(new ServirPlatCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }

    /**
     * Execute la commande de visualisation des commandes entrantes
     */
    public void visualiserCommandesEntrante() {
    	if(accessController.checkViewCommandesAccess()) {
        	executor.executeOperation(new VisualiserCommande(commandeCmd));
	    } else {
			executor.executeOperation(new DisplayRestrictionAccess(new RestrictionAccessView()));
		}
        openCommandeMenu();
    }
}
