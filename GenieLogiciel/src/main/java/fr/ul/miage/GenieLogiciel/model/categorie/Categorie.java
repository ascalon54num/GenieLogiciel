package fr.ul.miage.GenieLogiciel.model.categorie;

import java.util.Objects;

public class Categorie {
    private int id;
    private String libelle;

    private final CategorieRepository categorieRepository;

    public Categorie() {
        this.categorieRepository = new CategorieRepository();
    }

    public Categorie(CategorieRepository platRepository) {
        this.categorieRepository = platRepository;
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
