package fr.ul.miage.GenieLogiciel.model.commande.cmd;

import fr.ul.miage.GenieLogiciel.View.CommandeCmd;
import fr.ul.miage.GenieLogiciel.View.ServiceCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class EditCommande implements Command {

    private final CommandeCmd cmd;

    public EditCommande(CommandeCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.update(false);
    }

}
