package fr.ul.miage.GenieLogiciel.model.affectation;

import fr.ul.miage.GenieLogiciel.model.table.Table;
import fr.ul.miage.GenieLogiciel.model.table.TableRepository;
import fr.ul.miage.GenieLogiciel.model.user.User;
import fr.ul.miage.GenieLogiciel.model.user.UserRepository;

public class Affectation {
	private int id;
	private int idServeur;
	private User serveur;
	private Table table;
	private int idTable;
	
	private final AffectationRepository affectationRepository;
	
	public Affectation() {
		affectationRepository = new AffectationRepository();
	}
	
	public Affectation(AffectationRepository affectationRepository) {
		this.affectationRepository = affectationRepository;
	}

	public int getId() {
		return id;
	}

	public Affectation setId(int id) {
		this.id = id;
		return this;
	}

	public int getIdServeur() {
		return idServeur;
	}

	public Affectation setIdServeur(int idServeur) {
		this.idServeur = idServeur;
		this.serveur = new UserRepository().findOneById(idServeur);
		return this;
	}

	public int getIdTable() {
		return idTable;
	}

	public Affectation setIdTable(int idTable) {
		this.idTable = idTable;
		this.table = new TableRepository().findOneById(idTable);
		return this;
	}
	
	 public void save() {
	    affectationRepository.save(this);
	 }

	 public void delete() {
	    affectationRepository.deleteById(id);
	 }
	 
	 @Override
	    public String toString() {
	        return id + " {Serveur = " + serveur.getPrenom() +" "+serveur.getNom() + ", Table = " + table.getId() + "}";
	    }

	public String getServeur() {
		return serveur.getPrenom()+" "+serveur.getNom();
	}
}
