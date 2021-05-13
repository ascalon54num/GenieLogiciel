package fr.ul.miage.GenieLogiciel.model.commande.cmd;

import fr.ul.miage.GenieLogiciel.View.CommandeCmd;
import fr.ul.miage.GenieLogiciel.View.ServiceCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ListeCommande implements Command {

    private final CommandeCmd cmd;

    public ListeCommande(CommandeCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.liste();
    }

}
