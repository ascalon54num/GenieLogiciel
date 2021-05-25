package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.model.ingredient.Ingredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlat;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CommandePlatTest {
    private CommandePlat subject;

    @BeforeEach
    void beforeEach() {
        subject = new CommandePlat();
    }

    @Test
    @DisplayName("Les ingrédients devraient être disponible")
    void checkIngredientOk() {
        // Given
        Ingredient FARINE = new Ingredient().setLibelle("Farine").setQuantite(1000);
        Ingredient LAIT = new Ingredient().setLibelle("Lait").setQuantite(10);
        Plat CREPES = new Plat().setLibelle("Crêpes");
        List<IngredientPlat> ingredientPlats = new ArrayList<>() {{
            add(new IngredientPlat().setPlat(CREPES).setIngredient(FARINE).setQuantite(100));
            add(new IngredientPlat().setPlat(CREPES).setIngredient(LAIT).setQuantite(1));
        }};
        CREPES.setIngredients(ingredientPlats);
        subject.setPlat(CREPES).setQuantite(1);

        // When
        boolean actual = subject.checkIfIngredientsOk();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Les ingrédients devraient être disponible si quantité limite")
    void checkIngredientOk2() {
        // Given
        Ingredient FARINE = new Ingredient().setLibelle("Farine").setQuantite(100);
        Ingredient LAIT = new Ingredient().setLibelle("Lait").setQuantite(1);
        Plat CREPES = new Plat().setLibelle("Crêpes");
        List<IngredientPlat> ingredientPlats = new ArrayList<>() {{
            add(new IngredientPlat().setPlat(CREPES).setIngredient(FARINE).setQuantite(100));
            add(new IngredientPlat().setPlat(CREPES).setIngredient(LAIT).setQuantite(1));
        }};
        CREPES.setIngredients(ingredientPlats);
        subject.setPlat(CREPES).setQuantite(1);

        // When
        boolean actual = subject.checkIfIngredientsOk();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Les ingrédients devraient être disponible si quantité limite (2 plats)")
    void checkIngredientOk2Plats() {
        // Given
        Ingredient FARINE = new Ingredient().setLibelle("Farine").setQuantite(200);
        Ingredient LAIT = new Ingredient().setLibelle("Lait").setQuantite(2);
        Plat CREPES = new Plat().setLibelle("Crêpes");
        List<IngredientPlat> ingredientPlats = new ArrayList<>() {{
            add(new IngredientPlat().setPlat(CREPES).setIngredient(FARINE).setQuantite(100));
            add(new IngredientPlat().setPlat(CREPES).setIngredient(LAIT).setQuantite(1));
        }};
        CREPES.setIngredients(ingredientPlats);
        subject.setPlat(CREPES).setQuantite(2);

        // When
        boolean actual = subject.checkIfIngredientsOk();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Les ingrédients ne devrait pas être suffisant pour faire le plat")
    void checkIngredientKo() {
        // Given
        Ingredient FARINE = new Ingredient().setLibelle("Farine").setQuantite(100);
        Ingredient LAIT = new Ingredient().setLibelle("Lait").setQuantite(1);
        Plat CREPES = new Plat().setLibelle("Crêpes");
        List<IngredientPlat> ingredientPlats = new ArrayList<>() {{
            add(new IngredientPlat().setPlat(CREPES).setIngredient(FARINE).setQuantite(101));
            add(new IngredientPlat().setPlat(CREPES).setIngredient(LAIT).setQuantite(2));
        }};
        CREPES.setIngredients(ingredientPlats);
        subject.setPlat(CREPES).setQuantite(1);

        // When
        boolean actual = subject.checkIfIngredientsOk();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Les ingrédients ne devrait pas être suffisant pour faire les 2 plats")
    void checkIngredientKo2Plats() {
        // Given
        Ingredient FARINE = new Ingredient().setLibelle("Farine").setQuantite(100);
        Ingredient LAIT = new Ingredient().setLibelle("Lait").setQuantite(1);
        Plat CREPES = new Plat().setLibelle("Crêpes");
        List<IngredientPlat> ingredientPlats = new ArrayList<>() {{
            add(new IngredientPlat().setPlat(CREPES).setIngredient(FARINE).setQuantite(150));
            add(new IngredientPlat().setPlat(CREPES).setIngredient(LAIT).setQuantite(2));
        }};
        CREPES.setIngredients(ingredientPlats);
        subject.setPlat(CREPES).setQuantite(2);

        // When
        boolean actual = subject.checkIfIngredientsOk();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Les ingrédients ne devrait pas être suffisant pour faire les 3 plats")
    void checkIngredientKo3Plats() {
        // Given
        Ingredient FARINE = new Ingredient().setLibelle("Farine").setQuantite(200);
        Ingredient LAIT = new Ingredient().setLibelle("Lait").setQuantite(2);
        Plat CREPES = new Plat().setLibelle("Crêpes");
        List<IngredientPlat> ingredientPlats = new ArrayList<>() {{
            add(new IngredientPlat().setPlat(CREPES).setIngredient(FARINE).setQuantite(100));
            add(new IngredientPlat().setPlat(CREPES).setIngredient(LAIT).setQuantite(1));
        }};
        CREPES.setIngredients(ingredientPlats);
        subject.setPlat(CREPES).setQuantite(3);

        // When
        boolean actual = subject.checkIfIngredientsOk();

        // Then
        Assertions.assertFalse(actual);
    }
}