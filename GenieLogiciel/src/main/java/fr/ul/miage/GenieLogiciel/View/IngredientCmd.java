package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.ingredient.Ingredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientRepository;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import fr.ul.miage.GenieLogiciel.utils.Outil;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

import java.util.Map;

import static fr.ul.miage.GenieLogiciel.utils.Constantes.QUANTITE_MAX_INGREDIENT;

public class IngredientCmd {
    private final IngredientRepository ingredientRepository;

    public IngredientCmd() {
        this.ingredientRepository = new IngredientRepository();
    }

    public void add() {
        System.out.println();
        System.out.print("Nom de l'ingrédient : ");
        String nom = ScannerWithCheck.scannerStringUtilisateur(50);
        System.out.print("Quantité initiale : ");
        int quantite = ScannerWithCheck.scannerIntUtilisateur(true, QUANTITE_MAX_INGREDIENT);

        Ingredient newIngredient = new Ingredient();
        newIngredient.setLibelle(nom).setQuantite(quantite).save();
        System.out.println("Ingrédient ajouté = " + newIngredient);
    }

    public void delete() {
        System.out.println();
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        if (ingredients.isEmpty()) {
            System.err.println("Il n'y a pas d'ingrédient d'enregistrée");
            Outil.waitTime(500);
            return;
        }
        ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
        System.out.print("Id de l'ingrédient à supprimer : ");
        int idIngredient = ScannerWithCheck.scannerIntUtilisateur(false, -1);
        if (ingredients.containsKey(idIngredient)) {
            Ingredient ingredient = ingredients.get(idIngredient);
            Map<Integer, Plat> platsLiesAIngredient = ingredientRepository.findPlatsByIdIngredient(idIngredient);
            if (platsLiesAIngredient.isEmpty()) {
                ingredient.delete();
                System.out.println("Ingrédient supprimé = " + ingredient);
            } else {
                System.err.println("Impossible de supprimer l'ingrédient = " + ingredient);
                Outil.waitTime(500);
                System.out.println("Les plats suivants sont utilisés par cette ingrédient :");
                for (Plat plat : platsLiesAIngredient.values()) {
                    System.out.println(plat);
                }
            }
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void edit() {
        System.out.println();
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
        System.out.print("Id de l'ingrédient à modifier : ");
        int idIngredient = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (ingredients.containsKey(idIngredient)) {
            Ingredient ingredient = ingredients.get(idIngredient);
            System.out.print("Nouveau nom (" + ingredient.getLibelle() + ") : ");
            ingredient.setLibelle(ScannerWithCheck.scannerStringUtilisateur(50));
            System.out.print("Nouvelle quantité (" + ingredient.getQuantite() + ") : ");
            ingredient.setQuantite(ScannerWithCheck.scannerIntUtilisateur(true, QUANTITE_MAX_INGREDIENT));
            ingredient.save();

            System.out.println("Ingrédient modifié = " + ingredient);
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void liste() {
        System.out.println();
        System.out.println("Liste des ingrédients :");
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
    }

    public void remplir() {
        System.out.println();
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
        System.out.print("Id de l'ingrédient à remplir : ");
        int idIngredient = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (ingredients.containsKey(idIngredient)) {
            Ingredient ingredient = ingredients.get(idIngredient);
            System.out.print("Quantité à ajouter : ");
            ingredient.ajouter(ScannerWithCheck.scannerIntUtilisateur(false, -1));
            ingredient.save();
            System.out.println("Ingrédient modifié = " + ingredient);
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void vider() {
        System.out.println();
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
        System.out.print("Id de l'ingrédient à vider : ");
        int idIngredient = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (ingredients.containsKey(idIngredient)) {
            Ingredient ingredient = ingredients.get(idIngredient);
            System.out.print("Quantité à enlever : ");
            ingredient.utiliser(ScannerWithCheck.scannerIntUtilisateur(false, ingredient.getQuantite()));
            ingredient.save();
            System.out.println("Ingrédient modifié = " + ingredient);
        } else {
            System.err.println("Erreur de saisie");
        }
    }
}
