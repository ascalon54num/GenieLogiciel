package fr.ul.miage.GenieLogiciel.model.plat.cmd;

import fr.ul.miage.GenieLogiciel.View.PlatCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DeletePlat implements Command {

    private final PlatCmd cmd;

    public DeletePlat(PlatCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.delete();
    }

}
