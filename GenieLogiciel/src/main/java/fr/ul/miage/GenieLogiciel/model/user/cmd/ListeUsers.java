package fr.ul.miage.GenieLogiciel.model.user.cmd;

import fr.ul.miage.GenieLogiciel.View.UserCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ListeUsers implements Command {

    private final UserCmd userCmd;

    public ListeUsers(UserCmd userCmd) {
        this.userCmd = userCmd;
    }

    @Override
    public void execute() {
        userCmd.liste();
    }

}
