package fr.ul.miage.GenieLogiciel.model.table;


public class Table {
	private int id;
	private int nbCouvert;
	private String statut;
	
	private final TableRepository tableRepository;
	
	 public Table() {
	   this.tableRepository = new TableRepository();
	 }
	 
	 public Table(TableRepository tableRepository) {
	        this.tableRepository = tableRepository;
	 }
	 
	 public int getId() {
	   return id;
	 }

	 public Table setId(int id) {
	    this.id = id;
	    return this;
	 }

	public int getNbCouvert() {
		return nbCouvert;
	}

	public Table setNbCouvert(int nbCouvert) {
		this.nbCouvert = nbCouvert;
		return this;
	}

	public String getStatut() {
		return statut;
	}

	public Table setStatut(String statut) {
		this.statut = statut;
		return this;
	}
	
	public void save() {
        tableRepository.save(this);
    }
	
	@Override
    public String toString() {
        return "Table "+id + ": {statut = " + statut + ", nombre de couverts = " + nbCouvert + "}";
    }

	public void delete() {
		tableRepository.deleteById(this.id);
		
	}
}
