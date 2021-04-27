package fr.ul.miage.GenieLogiciel.model;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
	
	private final List<Command> commands
    = new ArrayList<>();
	
	public void executeOperation(Command operation) {
		commands.add(operation);
        operation.execute();
    }
}
