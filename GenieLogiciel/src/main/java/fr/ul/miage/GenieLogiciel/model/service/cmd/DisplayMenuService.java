package fr.ul.miage.GenieLogiciel.model.service.cmd;

import fr.ul.miage.GenieLogiciel.View.MenuIngredientView;
import fr.ul.miage.GenieLogiciel.View.MenuServiceView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuService implements Command {

    private final MenuServiceView menuServiceView;

    public DisplayMenuService(MenuServiceView menuServiceView) {
        this.menuServiceView = menuServiceView;
    }

    @Override
    public void execute() {
        menuServiceView.show();
    }

}
