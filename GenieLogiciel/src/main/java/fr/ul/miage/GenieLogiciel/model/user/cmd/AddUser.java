package fr.ul.miage.GenieLogiciel.model.user.cmd;

import fr.ul.miage.GenieLogiciel.model.Command;
import fr.ul.miage.GenieLogiciel.View.UserCmd;

public class AddUser implements Command {

    private final UserCmd userCmd;

    public AddUser(UserCmd userCmd) {
        this.userCmd = userCmd;
    }

    @Override
    public void execute() {
        userCmd.add();
    }

}
