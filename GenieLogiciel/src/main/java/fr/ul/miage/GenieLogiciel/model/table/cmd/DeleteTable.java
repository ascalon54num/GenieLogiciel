package fr.ul.miage.GenieLogiciel.model.table.cmd;

import fr.ul.miage.GenieLogiciel.View.TableCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DeleteTable implements Command {

	private final TableCmd tableCmd;
	
	public DeleteTable(TableCmd tableCmd) {
	   this.tableCmd = tableCmd;
	}
	@Override
	public void execute() {
		tableCmd.supprimer();
	}

}
