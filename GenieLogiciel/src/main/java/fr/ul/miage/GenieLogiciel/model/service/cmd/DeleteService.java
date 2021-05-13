package fr.ul.miage.GenieLogiciel.model.service.cmd;

import fr.ul.miage.GenieLogiciel.View.IngredientCmd;
import fr.ul.miage.GenieLogiciel.View.ServiceCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DeleteService implements Command {

    private final ServiceCmd cmd;

    public DeleteService(ServiceCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.delete();
    }

}
