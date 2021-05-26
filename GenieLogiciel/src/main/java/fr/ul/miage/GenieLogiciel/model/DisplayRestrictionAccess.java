package fr.ul.miage.GenieLogiciel.model;

import fr.ul.miage.GenieLogiciel.View.RestrictionAccessView;

public class DisplayRestrictionAccess implements Command {

    private final RestrictionAccessView restrictionAccessView;
    
    public DisplayRestrictionAccess(RestrictionAccessView restrict) {
		restrictionAccessView = restrict;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		restrictionAccessView.show();
	}

}
