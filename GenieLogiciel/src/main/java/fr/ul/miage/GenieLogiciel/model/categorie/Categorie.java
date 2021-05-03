package fr.ul.miage.GenieLogiciel.model.categorie;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;

import java.util.Map;
import java.util.Objects;

public class Categorie {
    private int id;
    private String libelle;

    private final CategorieRepository categorieRepository;

    public Categorie() {
        this.categorieRepository = new CategorieRepository();
    }

    public Categorie(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public int getId() {
        return id;
    }

    public Categorie setId(int id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Categorie setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void save() {
        categorieRepository.save(this);
    }

    public void delete() {
        categorieRepository.deleteById(id);
    }

    public Map<Integer, Plat> getPlats() {
        return categorieRepository.findPlatsByIdCategory(id);
    }

    @Override
    public String toString() {
        return id + " {nom = " + libelle + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return id == categorie.id && Objects.equals(libelle, categorie.libelle);
    }
}
