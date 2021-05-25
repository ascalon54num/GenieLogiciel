package fr.ul.miage.GenieLogiciel.model.commande.cmd;

import fr.ul.miage.GenieLogiciel.View.CommandeCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class PreparerPlatCommande implements Command {

    private final CommandeCmd cmd;

    public PreparerPlatCommande(CommandeCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.preparerPlatCommande();
    }

}
