package fr.ul.miage.GenieLogiciel.model.plat.cmd;

import fr.ul.miage.GenieLogiciel.View.CategorieCmd;
import fr.ul.miage.GenieLogiciel.View.PlatCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class AddPlat implements Command {

    private final PlatCmd cmd;

    public AddPlat(PlatCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.update(true);
    }

}
