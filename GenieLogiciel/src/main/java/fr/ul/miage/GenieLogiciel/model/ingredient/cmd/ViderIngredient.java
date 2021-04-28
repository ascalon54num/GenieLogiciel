package fr.ul.miage.GenieLogiciel.model.ingredient.cmd;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ViderIngredient implements Command {

    private final IngredientCmd ingredientCmd;

    public ViderIngredient(IngredientCmd ingredientCmd) {
        this.ingredientCmd = ingredientCmd;
    }

    @Override
    public void execute() {
        ingredientCmd.vider();
    }

}
