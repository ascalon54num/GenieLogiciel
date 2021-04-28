package fr.ul.miage.GenieLogiciel.model.ingredient.cmd;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ListeIngredient implements Command {

    private final IngredientCmd ingredientCmd;

    public ListeIngredient(IngredientCmd ingredientCmd) {
        this.ingredientCmd = ingredientCmd;
    }

    @Override
    public void execute() {
        ingredientCmd.liste();
    }

}
