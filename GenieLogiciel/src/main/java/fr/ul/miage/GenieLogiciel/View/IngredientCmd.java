package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.ingredient.Ingredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientRepository;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

import java.util.Map;

import static fr.ul.miage.GenieLogiciel.utils.Constantes.QUANTITE_MAX_INGREDIENT;

public class IngredientCmd {

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
        IngredientRepository ingredientRepository = new IngredientRepository();
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
        System.out.print("Id de l'ingrédient à supprimer : ");
        int idIngredient = ScannerWithCheck.scannerIntUtilisateur(false, -1);

        if (ingredients.containsKey(idIngredient)) {
            Ingredient ingredient = ingredients.get(idIngredient);
            ingredient.delete();
            System.out.println("Ingrédient supprimé = " + ingredient);
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void edit() {
        System.out.println();
        IngredientRepository ingredientRepository = new IngredientRepository();
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
        IngredientRepository ingredientRepository = new IngredientRepository();
        Map<Integer, Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach((id, ingredient) -> System.out.println(ingredient));
    }

    public void remplir() {
        System.out.println();
        IngredientRepository ingredientRepository = new IngredientRepository();
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
        IngredientRepository ingredientRepository = new IngredientRepository();
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
