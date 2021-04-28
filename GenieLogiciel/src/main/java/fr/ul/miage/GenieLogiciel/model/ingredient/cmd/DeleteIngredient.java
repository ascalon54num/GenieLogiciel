package fr.ul.miage.GenieLogiciel.model.ingredient.cmd;

import fr.ul.miage.GenieLogiciel.model.Command;
import fr.ul.miage.GenieLogiciel.View.IngredientCmd;

public class DeleteIngredient implements Command {

    private final IngredientCmd ingredientCmd;

    public DeleteIngredient(IngredientCmd ingredientCmd) {
        this.ingredientCmd = ingredientCmd;
    }

    @Override
    public void execute() {
        ingredientCmd.delete();
    }

}
