package fr.ul.miage.GenieLogiciel.model.categorie.cmd;

import fr.ul.miage.GenieLogiciel.View.CategorieCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class AddCategorie implements Command {

    private final CategorieCmd cmd;

    public AddCategorie(CategorieCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.add();
    }

}
