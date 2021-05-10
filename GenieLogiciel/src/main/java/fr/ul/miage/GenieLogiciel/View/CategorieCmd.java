package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.categorie.Categorie;
import fr.ul.miage.GenieLogiciel.model.categorie.CategorieRepository;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import fr.ul.miage.GenieLogiciel.utils.Outil;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

import java.util.Map;

public class CategorieCmd {

    private final CategorieRepository categorieRepository;

    public CategorieCmd() {
        this.categorieRepository = new CategorieRepository();
    }

    public void add() {
        System.out.println();
        System.out.print("Nom de la catégorie : ");
        String nom = ScannerWithCheck.scannerStringUtilisateur(50);

        Categorie newCategorie = new Categorie();
        newCategorie.setLibelle(nom).save();
        System.out.println("Catégorie ajouté = " + newCategorie);
    }

    public void delete() {
        System.out.println();
        Map<Integer, Categorie> categories = categorieRepository.findAll();
        categories.forEach((id, categorie) -> System.out.println(categorie));
        System.out.println("=============================================================");
        System.out.print("Id de la catégorie à supprimer : ");
        int idCategorie = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (categories.containsKey(idCategorie)) {
            Categorie categorie = categories.get(idCategorie);
            Map<Integer, Plat> platsLiesACategorie = categorie.getPlats();
            if (platsLiesACategorie.isEmpty()) {
                categorie.delete();
                System.out.println("Catégorie supprimé = " + categorie);
            } else {
                System.err.println("Impossible de supprimer la catégorie = " + categorie);
                Outil.waitTime(500);
                System.out.println("Les plats suivants appartiennent à cette catégorie:");
                for (Plat plat : platsLiesACategorie.values()) {
                    System.out.println(plat);
                }
            }
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void edit() {
        System.out.println();
        Map<Integer, Categorie> categories = categorieRepository.findAll();
        categories.forEach((id, ingredient) -> System.out.println(ingredient));
        System.out.println("=============================================================");
        System.out.print("Id de la catégorie à modifier : ");
        int idCategorie = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (categories.containsKey(idCategorie)) {
            Categorie categorie = categories.get(idCategorie);
            System.out.print("Nouveau nom (" + categorie.getLibelle() + ") : ");
            categorie.setLibelle(ScannerWithCheck.scannerStringUtilisateur(50));
            categorie.save();

            System.out.println("Catégorie modifié = " + categorie);
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void liste() {
        System.out.println();
        System.out.println("Liste des catégories :");
        Map<Integer, Categorie> categories = categorieRepository.findAll();
        categories.forEach((id, categorie) -> System.out.println(categorie));
    }
}
