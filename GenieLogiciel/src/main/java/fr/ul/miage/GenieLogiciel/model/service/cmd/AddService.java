package fr.ul.miage.GenieLogiciel.model.service.cmd;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.View.ServiceCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class AddService implements Command {

    private final ServiceCmd cmd;

    public AddService(ServiceCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.update(true);
    }

}
