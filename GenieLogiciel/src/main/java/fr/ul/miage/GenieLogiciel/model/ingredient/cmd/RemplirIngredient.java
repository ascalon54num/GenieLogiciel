package fr.ul.miage.GenieLogiciel.model.ingredient.cmd;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class RemplirIngredient implements Command {

    private final IngredientCmd ingredientCmd;

    public RemplirIngredient(IngredientCmd ingredientCmd) {
        this.ingredientCmd = ingredientCmd;
    }

    @Override
    public void execute() {
        ingredientCmd.remplir();
    }

}
