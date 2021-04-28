package fr.ul.miage.GenieLogiciel.model.ingredient;

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

    public void utiliser(int quantiteAUtiliser) {
        if (quantite >= quantiteAUtiliser) {
            quantite -= quantiteAUtiliser;
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
}
