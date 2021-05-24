package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.categorie.Categorie;
import fr.ul.miage.GenieLogiciel.model.categorie.CategorieRepository;
import fr.ul.miage.GenieLogiciel.model.commande.Commande;
import fr.ul.miage.GenieLogiciel.model.ingredient.Ingredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlat;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlatRepository;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientRepository;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import fr.ul.miage.GenieLogiciel.model.plat.PlatRepository;
import fr.ul.miage.GenieLogiciel.utils.Outil;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlatCmd {

    private final PlatRepository platRepository;
    private final CategorieRepository categorieRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientPlatRepository ingredientPlatRepository;

    public PlatCmd() {
        this.platRepository = new PlatRepository();
        this.categorieRepository = new CategorieRepository();
        this.ingredientRepository = new IngredientRepository();
        this.ingredientPlatRepository = new IngredientPlatRepository();
    }

    public void update(boolean isCreate) {
        Plat oldPlat = null;
        if (!isCreate) {
            Map<Integer, Plat> plats = platRepository.findAll();
            if (plats.isEmpty()) {
                System.err.println("Il n'y a aucun plat d'enregistré");
                Outil.waitTime(500);
                return;
            }

            plats.forEach((id, plat) -> System.out.println(plat));
            System.out.print("Id du plat à modifier : ");
            int id = ScannerWithCheck.scannerIntUtilisateur(false, -1);
            if (plats.containsKey(id)) {
                oldPlat = plats.get(id);
            } else {
                System.err.println("Erreur de saisie");
                Outil.waitTime(500);
                return;
            }
        }

        Map<Integer, Categorie> categories = categorieRepository.findAll();
        if (categories.isEmpty()) {
            System.err.println("Il n'y a pas de catégorie d'enregistrée");
            Outil.waitTime(500);
            return;
        }
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        if (ingredients.isEmpty()) {
            System.err.println("Il n'y a pas d'ingrédient enregistré");
            Outil.waitTime(500);
            return;
        }

        System.out.println();
        System.out.print("Nom du plat : ");
        String nom = ScannerWithCheck.scannerStringUtilisateur(50);

        System.out.print("Prix du plat : ");
        double prix = ScannerWithCheck.scannerDoubleUtilisateur();

        System.out.print("Est-ce un plat du jour (1/0) ? : ");
        int isPlatDuJour = ScannerWithCheck.scannerIntUtilisateur(true, 1);

        System.out.print("Est-il disponible (1/0) ? : ");
        int isDisponible = ScannerWithCheck.scannerIntUtilisateur(true, 1);

        System.out.println();
        categories.forEach((id, categorie) -> System.out.println(categorie));
        System.out.println("=============================================================");
        System.out.print("Id de la catégorie du plat : ");
        int idCategorie = ScannerWithCheck.scannerIntUtilisateur(false, -1);
        if (categories.containsKey(idCategorie)) {
            List<IngredientPlat> ingredientPlats = new ArrayList<>();
            boolean isFinishAddIngredient = false;
            int idIngredient, quantite;
            while (!isFinishAddIngredient) {
                System.out.println();
                ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
                System.out.println("=============================================================");
                System.out.print("Id de l'ingrédient à ajouter : ");
                idIngredient = ScannerWithCheck.scannerIntUtilisateur(false, -1);

                if (ingredients.containsKey(idIngredient)) {
                    int finalIdIngredient = idIngredient;
                    boolean isIngredientNonChoisi = ingredientPlats.stream()
                            .map(ingredientPlat -> ingredientPlat.getIngredient().getId()).noneMatch(id -> id == finalIdIngredient);
                    if (isIngredientNonChoisi) {
                        System.out.print("Quantité nécessaire pour faire le plat : ");
                        quantite = ScannerWithCheck.scannerIntUtilisateur(false, -1);
                        ingredientPlats.add(new IngredientPlat().setIngredient(ingredients.get(idIngredient)).setQuantite(quantite));

                        System.out.print("Ajouter un autre ingrédient (1/0) ? : ");
                        isFinishAddIngredient = ScannerWithCheck.scannerIntUtilisateur(true, 1) == 0;
                    } else {
                        System.err.println("L'ingrédient a déjà été ajouté au plat");
                        Outil.waitTime(500);
                    }
                } else {
                    System.err.println("Erreur de saisie");
                    Outil.waitTime(500);
                }
            }

            Plat newPlat = new Plat();
            newPlat.setLibelle(nom).setPrix(prix).setCategorie(categories.get(idCategorie)).setDisponible(isDisponible)
                    .setPlatDuJour(isPlatDuJour).setIngredients(ingredientPlats);
            if (!isCreate) {
                newPlat.setId(oldPlat.getId());
                ingredientPlatRepository.deleteByIdPlat(newPlat.getId());
            }
            newPlat.save();

            ingredientPlats.forEach(ingredientPlat -> ingredientPlat.setPlat(newPlat).save());

            System.out.println("Plat ajouté/modifié = " + newPlat);
        } else {
            System.err.println("Erreur de saisie");
            Outil.waitTime(500);
        }
    }

    public void delete() {
        System.out.println();
        Map<Integer, Plat> plats = platRepository.findAll();
        if (plats.isEmpty()) {
            System.err.println("Il n'y a aucun plat d'enregistré");
            Outil.waitTime(500);
            return;
        }
        plats.forEach((id, categorie) -> System.out.println(categorie));
        System.out.println("=============================================================");
        System.out.print("Id du plat à supprimer : ");
        int idPlat = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (plats.containsKey(idPlat)) {
            Plat plat = plats.get(idPlat);
            Map<Integer, Commande> commandeLieeAPlat = platRepository.findCommandesByIdPlat(idPlat);
            if (commandeLieeAPlat.isEmpty()) {
                plat.delete();
                System.out.println("Plat supprimé = " + plat);
            } else {
                System.err.println("Impossible de supprimer le plat = " + plat);
                Outil.waitTime(500);
                System.out.println("Les commandes suivantes sont liées au plat :");
                for (Commande commande : commandeLieeAPlat.values()) {
                    System.out.println(commande);
                }
            }
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void liste() {
        System.out.println();
        System.out.println("Liste des plats :");
        Map<Integer, Plat> plats = platRepository.findAll();
        plats.forEach((id, plat) -> System.out.println(plat));
    }
}
