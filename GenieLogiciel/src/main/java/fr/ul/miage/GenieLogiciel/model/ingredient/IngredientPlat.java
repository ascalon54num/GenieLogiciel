package fr.ul.miage.GenieLogiciel.model.ingredient;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;

import java.util.Objects;

public class IngredientPlat {
    private Ingredient ingredient;
    private Plat plat;
    private int quantite;

    private final IngredientPlatRepository ingredientPlatRepository;

    public IngredientPlat() {
        this.ingredientPlatRepository = new IngredientPlatRepository();
    }

    public IngredientPlat(IngredientPlatRepository ingredientPlatRepository) {
        this.ingredientPlatRepository = ingredientPlatRepository;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public IngredientPlat setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    public Plat getPlat() {
        return plat;
    }

    public IngredientPlat setPlat(Plat plat) {
        this.plat = plat;
        return this;
    }

    public int getQuantite() {
        return quantite;
    }

    public IngredientPlat setQuantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

    public void utiliser() {
        if (canUse()) {
            ingredient.utiliser(quantite);
        }
    }

    public boolean canUse() {
        return ingredient.getQuantite() >= quantite;
    }

    public void save() {
        ingredientPlatRepository.save(this);
    }

    public void delete() {
        ingredientPlatRepository.deleteById(ingredient.getId(), plat.getId());
    }

    @Override
    public String toString() {
        return " {nom = " + ingredient.getLibelle() + ", quantité = " + quantite + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientPlat that = (IngredientPlat) o;
        return quantite == that.quantite && ingredient.equals(that.ingredient) && plat.equals(that.plat);
    }
}
