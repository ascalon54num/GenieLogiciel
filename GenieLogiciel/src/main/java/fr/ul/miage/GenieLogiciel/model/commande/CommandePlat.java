package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;

public class CommandePlat {
    private Plat plat;
    private int quantite;
    private String etat;

    public Plat getPlat() {
        return plat;
    }

    public CommandePlat setPlat(Plat plat) {
        this.plat = plat;
        return this;
    }

    public int getQuantite() {
        return quantite;
    }

    public CommandePlat setQuantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

    public String getEtat() {
        return etat;
    }

    public CommandePlat setEtat(String etat) {
        this.etat = etat;
        return this;
    }
}
