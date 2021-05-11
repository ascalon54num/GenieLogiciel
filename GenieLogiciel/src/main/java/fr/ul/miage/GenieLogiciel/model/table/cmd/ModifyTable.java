package fr.ul.miage.GenieLogiciel.model.table.cmd;

import fr.ul.miage.GenieLogiciel.View.TableCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ModifyTable implements Command {

	private final TableCmd tableCmd;
	
	public ModifyTable(TableCmd tableCmd) {
		this.tableCmd = tableCmd;
	}
	
	@Override
	public void execute() {
		tableCmd.edit();
	}

}
