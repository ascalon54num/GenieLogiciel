package fr.ul.miage.GenieLogiciel.model.categorie;

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
}
