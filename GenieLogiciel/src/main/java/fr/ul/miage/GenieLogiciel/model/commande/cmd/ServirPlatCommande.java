package fr.ul.miage.GenieLogiciel.model.commande.cmd;

import fr.ul.miage.GenieLogiciel.View.CommandeCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ServirPlatCommande implements Command {

    private final CommandeCmd cmd;

    public ServirPlatCommande(CommandeCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.servirPlatCommande();
    }

}
