package fr.ul.miage.GenieLogiciel.model.user.cmd;

import fr.ul.miage.GenieLogiciel.View.UserCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class EditUser implements Command {

    private final UserCmd cmd;

    public EditUser(UserCmd cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        cmd.update(false);
    }

}
