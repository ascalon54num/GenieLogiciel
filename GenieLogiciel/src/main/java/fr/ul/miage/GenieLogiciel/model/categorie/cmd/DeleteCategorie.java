package fr.ul.miage.GenieLogiciel.model.categorie.cmd;

import fr.ul.miage.GenieLogiciel.View.CategorieCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DeleteCategorie implements Command {

    private final CategorieCmd cmd;

    public DeleteCategorie(CategorieCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.delete();
    }

}
