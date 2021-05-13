package fr.ul.miage.GenieLogiciel.model.commande.cmd;

import fr.ul.miage.GenieLogiciel.View.menu.MenuCommandeView;
import fr.ul.miage.GenieLogiciel.View.menu.MenuServiceView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuCommande implements Command {

    private final MenuCommandeView menuView;

    public DisplayMenuCommande(MenuCommandeView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void execute() {
        menuView.show();
    }

}
