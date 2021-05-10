package fr.ul.miage.GenieLogiciel.model.table.cmd;

import fr.ul.miage.GenieLogiciel.View.MenuTableView;
import fr.ul.miage.GenieLogiciel.model.Command;

public class DisplayMenuTable implements Command {

	private final MenuTableView menuTableView;

    public DisplayMenuTable(MenuTableView menuTableView) {
        this.menuTableView = menuTableView;
    }

    
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		menuTableView.show();
	}

}
