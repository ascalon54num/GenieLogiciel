package fr.ul.miage.GenieLogiciel.model.ingredient.cmd;

import fr.ul.miage.GenieLogiciel.View.menu.MenuIngredientView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuIngredient implements Command {

    private final MenuIngredientView menuIngredientView;

    public DisplayMenuIngredient(MenuIngredientView menuIngredientView) {
        this.menuIngredientView = menuIngredientView;
    }

    @Override
    public void execute() {
        menuIngredientView.show();
    }

}
