package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlat;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;

public class CommandePlat {
    public static final String EMIS = "EMIS";
    public static final String PRET = "PRET";
    public static final String SERVI = "SERVI";

    private Plat plat;
    private int quantite;
    private String etat;

    private final CommandePlatRepository repository;

    public CommandePlat() {
        this.repository = new CommandePlatRepository();
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

    public String getEtat() {
        return etat;
    }

    public CommandePlat setEtat(String etat) {
        this.etat = etat;
        return this;
    }

    public void update(int idCommande) {
        repository.update(this, idCommande);
    }

    public boolean checkIfIngredientsOk() {
        for (IngredientPlat ingredientPlat : plat.getIngredients()) {
            if (!ingredientPlat.getIngredient().enASuffisament(ingredientPlat.getQuantite() * quantite)) {
                return false;
            }
        }
        return true;
    }

    public void preparer(int commandeId) {
        if (checkIfIngredientsOk()) {
            etat = PRET;
            plat.preparer();
            update(commandeId);
        }
    }
}
