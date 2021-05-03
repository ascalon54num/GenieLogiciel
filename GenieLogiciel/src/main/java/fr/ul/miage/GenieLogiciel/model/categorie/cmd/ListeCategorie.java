package fr.ul.miage.GenieLogiciel.model.categorie.cmd;

import fr.ul.miage.GenieLogiciel.View.CategorieCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ListeCategorie implements Command {

    private final CategorieCmd cmd;

    public ListeCategorie(CategorieCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.liste();
    }

}
