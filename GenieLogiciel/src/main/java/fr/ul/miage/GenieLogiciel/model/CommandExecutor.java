package fr.ul.miage.GenieLogiciel.model;

public class CommandExecutor {
    public void executeOperation(Command operation) {
        operation.execute();
    }
}
