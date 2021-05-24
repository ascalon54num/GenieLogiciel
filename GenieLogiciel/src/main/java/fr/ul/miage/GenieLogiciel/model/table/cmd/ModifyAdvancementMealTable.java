package fr.ul.miage.GenieLogiciel.model.table.cmd;

import fr.ul.miage.GenieLogiciel.View.TableCmd;
import fr.ul.miage.GenieLogiciel.model.Command;

public class ModifyAdvancementMealTable implements Command {
	
	private final TableCmd tableCmd;
	
	public ModifyAdvancementMealTable(TableCmd tableCmd) {
		this.tableCmd = tableCmd;
	}

	@Override
	public void execute() {
		tableCmd.changeAdvancementMeal();
	}

}
