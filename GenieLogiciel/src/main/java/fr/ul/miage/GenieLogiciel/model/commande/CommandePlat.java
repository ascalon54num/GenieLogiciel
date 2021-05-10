package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;

public class CommandePlat {
    private Commande commande;
    private Plat plat;
    private int quantite;

    public Commande getCommande() {
        return commande;
    }

    public CommandePlat setCommande(Commande commande) {
        this.commande = commande;
        return this;
    }

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
}
