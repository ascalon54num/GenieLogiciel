package fr.ul.miage.GenieLogiciel.model.plat;

import fr.ul.miage.GenieLogiciel.model.categorie.Categorie;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlat;

import java.util.List;

public class Plat {
    private int id;
    private String libelle;
    private double prix;
    private boolean isPlatDuJour;
    private Categorie categorie;
    private boolean isDisponible;
    private List<IngredientPlat> ingredients;

    private final PlatRepository platRepository;

    public Plat() {
        this.platRepository = new PlatRepository();
    }

    public Plat(PlatRepository platRepository) {
        this.platRepository = platRepository;
    }

    public int getId() {
        return id;
    }

    public Plat setId(int id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Plat setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public double getPrix() {
        return prix;
    }

    public Plat setPrix(double prix) {
        this.prix = prix;
        return this;
    }

    public boolean isPlatDuJour() {
        return isPlatDuJour;
    }

    public Plat setPlatDuJour(boolean platDuJour) {
        isPlatDuJour = platDuJour;
        return this;
    }

    public Plat setPlatDuJour(int platDuJour) {
        isPlatDuJour = platDuJour == 1;
        return this;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Plat setCategorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public Plat setDisponible(boolean disponible) {
        isDisponible = disponible;
        return this;
    }

    public Plat setDisponible(int disponible) {
        isDisponible = disponible == 1;
        return this;
    }

    public List<IngredientPlat> getIngredients() {
        return ingredients;
    }

    public Plat setIngredients(List<IngredientPlat> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public void choisir() {
        // TODO Choix d'un plat qui implique la consommation d'ingrédients
    }

    public void save() {
        platRepository.save(this);
    }

    public void delete() {
        platRepository.deleteById(id);
    }

    @Override
    public String toString() {
        return id + " {nom = " + libelle + ", prix = " + prix + ", categorie = " + categorie.getLibelle() + ", plat du jour = " + isPlatDuJour + ", disponible = " + isDisponible + "}";
    }
}
