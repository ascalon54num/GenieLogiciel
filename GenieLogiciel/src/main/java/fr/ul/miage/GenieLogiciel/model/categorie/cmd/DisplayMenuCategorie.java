package fr.ul.miage.GenieLogiciel.model.categorie.cmd;

import fr.ul.miage.GenieLogiciel.View.MenuCategorieView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuCategorie implements Command {

    private final MenuCategorieView cmd;

    public DisplayMenuCategorie(MenuCategorieView cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.show();
    }

}
