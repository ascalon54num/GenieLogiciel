package fr.ul.miage.GenieLogiciel.model;

public class ExempleOperation implements Command {
	
	private OperationExempleReceiver textFile;
	
	public ExempleOperation(OperationExempleReceiver opr) {
		textFile = opr;
	}

	@Override
	public void execute() {
		// Appel de la fonction
		textFile.open();
	}

}
