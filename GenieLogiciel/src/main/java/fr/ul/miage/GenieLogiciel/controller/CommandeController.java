package fr.ul.miage.GenieLogiciel.controller;

import java.util.Scanner;

import fr.ul.miage.GenieLogiciel.model.CommandExecutor;
import fr.ul.miage.GenieLogiciel.model.ExempleOperation;
import fr.ul.miage.GenieLogiciel.model.OperationExempleReceiver;

public class CommandeController {
	
	private Scanner sc;
	private CommandExecutor executor;
	
	public CommandeController(Scanner scan) {
		sc = scan;
		executor = new CommandExecutor();
	}
	
	public void executeTest() {
		executor.executeOperation(new ExempleOperation(new OperationExempleReceiver()));
	}
}
