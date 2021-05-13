package fr.ul.miage.GenieLogiciel.model.table.cmd;

import fr.ul.miage.GenieLogiciel.View.TableCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ModifyStatusTable implements Command {

	private final TableCmd tableCmd;
	
	public ModifyStatusTable(TableCmd tableCmd) {
		this.tableCmd = tableCmd;
	}
	
	@Override
	public void execute() {
		tableCmd.changeStatus();
	}

}
