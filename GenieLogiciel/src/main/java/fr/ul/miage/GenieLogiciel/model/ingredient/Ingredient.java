package fr.ul.miage.GenieLogiciel.model.ingredient;

import java.util.Objects;

public class Ingredient {
    private int id;
    private String libelle;
    private int quantite;

    private final IngredientRepository ingredientRepository;

    public Ingredient() {
        this.ingredientRepository = new IngredientRepository();
    }

    public Ingredient(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public int getId() {
        return id;
    }

    public Ingredient setId(int id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Ingredient setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public int getQuantite() {
        return quantite;
    }

    public Ingredient setQuantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

    public boolean enASuffisament(int quantiteATester) {
        return quantite >= quantiteATester;
    }

    public void utiliser(int quantiteAUtiliser) {
        if (enASuffisament(quantiteAUtiliser)) {
            quantite -= quantiteAUtiliser;
            save();
        }
    }

    public void ajouter(int quantiteAAjouter) {
        quantite += quantiteAAjouter;
    }

    public void save() {
        ingredientRepository.save(this);
    }

    public void delete() {
        ingredientRepository.deleteById(id);
    }

    @Override
    public String toString() {
        return id + " {nom = " + libelle + ", quantité = " + quantite + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && quantite == that.quantite && Objects.equals(libelle, that.libelle);
    }
}
