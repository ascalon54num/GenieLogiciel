package fr.ul.miage.GenieLogiciel.controller;


import fr.ul.miage.GenieLogiciel.View.*;
import fr.ul.miage.GenieLogiciel.View.menu.*;
import fr.ul.miage.GenieLogiciel.model.CommandExecutor;
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

    private CommandeController() {
        executor = new CommandExecutor();
        categorieCmd = new CategorieCmd();
        platCmd = new PlatCmd();
        serviceCmd = new ServiceCmd();
        commandeCmd = new CommandeCmd();
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


/////////////////
// UTILISATEURS //
/////////////////

    public void openUserMenu() {
        executor.executeOperation(new DisplayMenuUser(new MenuUserView()));
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
        executor.executeOperation(new ModifyTable(new TableCmd()));
        openTableMenu();
    }

    public void changerStatutTable() {
        executor.executeOperation(new ModifyStatusTable(new TableCmd()));
        openTableMenu();
    }

    public void changerAvancementRepasTable() {
        executor.executeOperation(new ModifyAdvancementMealTable(new TableCmd()));
        openTableMenu();
    }

    public void showTable() {
        executor.executeOperation(new ShowDetailTables(new TableCmd()));
        openTableMenu();
    }

    public void supprimerTable() {
        executor.executeOperation(new DeleteTable(new TableCmd()));
        openTableMenu();
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


    /////////////
    // SERVICE //
    /////////////
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


    //////////////
    // COMMANDE //
    //////////////
    public void openCommandeMenu() {
        executor.executeOperation(new DisplayMenuCommande(new MenuCommandeView()));
    }

    public void listeCommande() {
        executor.executeOperation(new ListeCommande(commandeCmd));
        openCommandeMenu();
    }

    public void ajouterCommande() {
        executor.executeOperation(new AddCommande(commandeCmd));
        openCommandeMenu();
    }

    public void modifierCommande() {
        executor.executeOperation(new EditCommande(commandeCmd));
        openCommandeMenu();
    }

    public void facturerCommande() {
        executor.executeOperation(new FacturerCommande(commandeCmd));
        openCommandeMenu();
    }

    public void preparerPlatCommande() {
        executor.executeOperation(new PreparerPlatCommande(commandeCmd));
        openCommandeMenu();
    }

    public void servirPlatCommande() {
        executor.executeOperation(new ServirPlatCommande(commandeCmd));
        openCommandeMenu();
    }

    public void visualiserCommandesEntrante() {
        executor.executeOperation(new VisualiserCommande(commandeCmd));
        openCommandeMenu();
    }
}
