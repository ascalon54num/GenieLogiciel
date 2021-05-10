package fr.ul.miage.GenieLogiciel.model.commande;

public class CommandeStatut {
    private int id;
    private String libelle;

    public int getId() {
        return id;
    }

    public CommandeStatut setId(int id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public CommandeStatut setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }
}
