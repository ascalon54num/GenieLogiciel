package fr.ul.miage.GenieLogiciel.model.categorie.cmd;

import fr.ul.miage.GenieLogiciel.View.CategorieCmd;
import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class EditCategorie implements Command {

    private final CategorieCmd cmd;

    public EditCategorie(CategorieCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.edit();
    }

}
