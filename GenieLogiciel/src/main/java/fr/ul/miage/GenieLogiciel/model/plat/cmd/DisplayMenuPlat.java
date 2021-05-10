package fr.ul.miage.GenieLogiciel.model.plat.cmd;

import fr.ul.miage.GenieLogiciel.View.MenuPlatView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuPlat implements Command {

    private final MenuPlatView cmd;

    public DisplayMenuPlat(MenuPlatView cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.show();
    }

}
