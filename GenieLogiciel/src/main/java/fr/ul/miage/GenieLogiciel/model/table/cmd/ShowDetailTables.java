package fr.ul.miage.GenieLogiciel.model.table.cmd;

import fr.ul.miage.GenieLogiciel.View.TableCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ShowDetailTables implements Command {
	
	private final TableCmd tableCmd;

	public ShowDetailTables(TableCmd tableCmd) {
		this.tableCmd = tableCmd;
	}

	@Override
	public void execute() {
		tableCmd.showDetailTables();
	}

}
