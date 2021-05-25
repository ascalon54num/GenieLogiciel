package fr.ul.miage.GenieLogiciel.model.commande;

public class CommandeStatut {
    public static final int EMISE = 1;
    public static final int EN_COURS = 2;
    public static final int FACTUREE = 3;
    public static final String STR_FACTUREE = "FACTUREE";
    public static final String STR_EN_COURS = "EN COURS";

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

    @Override
    public String toString() {
        return libelle;
    }
}
