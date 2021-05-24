package fr.ul.miage.GenieLogiciel.model.service.cmd;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.View.ServiceCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class EditService implements Command {

    private final ServiceCmd cmd;

    public EditService(ServiceCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.update(false);
    }

}
