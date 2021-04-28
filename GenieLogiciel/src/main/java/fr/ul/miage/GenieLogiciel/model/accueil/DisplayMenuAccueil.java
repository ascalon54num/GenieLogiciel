package fr.ul.miage.GenieLogiciel.model.accueil;

import fr.ul.miage.GenieLogiciel.View.MenuAccueilView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuAccueil implements Command {

    private final MenuAccueilView menuAccueilView;

    public DisplayMenuAccueil(MenuAccueilView menuAccueilView) {
        this.menuAccueilView = menuAccueilView;
    }

    @Override
    public void execute() {
        menuAccueilView.show();
    }

}
