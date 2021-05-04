package fr.ul.miage.GenieLogiciel.model.plat.cmd;

import fr.ul.miage.GenieLogiciel.View.PlatCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ListePlat implements Command {

    private final PlatCmd cmd;

    public ListePlat(PlatCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.liste();
    }

}
