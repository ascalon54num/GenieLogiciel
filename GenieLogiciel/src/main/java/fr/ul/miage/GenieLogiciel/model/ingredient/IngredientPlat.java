package fr.ul.miage.GenieLogiciel.model.ingredient;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;

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
        if (ingredient.getQuantite() >= quantite) {
            ingredient.utiliser(quantite);
        }
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
}
