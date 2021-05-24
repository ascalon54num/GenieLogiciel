package fr.ul.miage.GenieLogiciel.model.user.cmd;

import fr.ul.miage.GenieLogiciel.View.MenuUserView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuUser implements Command {

    private final MenuUserView menuUserView;

    public DisplayMenuUser(MenuUserView menuUserView) {
        this.menuUserView = menuUserView;
    }

    @Override
    public void execute() {
        menuUserView.show();
    }

}
