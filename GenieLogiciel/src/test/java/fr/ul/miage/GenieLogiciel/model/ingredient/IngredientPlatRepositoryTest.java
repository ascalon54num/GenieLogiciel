package fr.ul.miage.GenieLogiciel.model.ingredient;

import fr.ul.miage.GenieLogiciel.model.categorie.Categorie;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import org.junit.jupiter.api.*;

class IngredientPlatRepositoryTest {
    private static IngredientPlat INGREDIENT_PLAT;

    private IngredientPlatRepository subject;

    private Ingredient CAROTTE;
    private Categorie LEGUME;
    private Plat SALADE_CAROTTE;

    void initElements() {
        CAROTTE = new Ingredient().setLibelle("CarotteTest").setQuantite(50);
        LEGUME = new Categorie().setLibelle("LégumeTest");
        SALADE_CAROTTE = new Plat().setLibelle("Salade de carotte Test").setCategorie(LEGUME).setPlatDuJour(false).setDisponible(true).setPrix(4.5);
        CAROTTE.save();
        LEGUME.save();
        SALADE_CAROTTE.save();
    }

    void resetElements() {
        CAROTTE.delete();
        SALADE_CAROTTE.delete();
        LEGUME.delete();
    }

    @BeforeEach
    void init() {
        initElements();
        INGREDIENT_PLAT = new IngredientPlat().setIngredient(CAROTTE).setQuantite(10).setPlat(SALADE_CAROTTE);
        this.subject = new IngredientPlatRepository();
    }

    @AfterEach
    void reset() {
        subject.deleteById(INGREDIENT_PLAT.getIngredient().getId(), INGREDIENT_PLAT.getPlat().getId());
        resetElements();
    }

    @Test
    @DisplayName("Devrait avoir un élément")
    void shouldCheckOk() {
        // When
        subject.save(INGREDIENT_PLAT);
        boolean actual = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait ne pas avoir d'élément")
    void shouldCheckKo() {
        // When
        boolean actual = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait être ajouté en base")
    void shouldCreate() {
        // Given
        boolean before = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // When
        subject.save(INGREDIENT_PLAT);
        boolean actual = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // Then
        Assertions.assertFalse(before);
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditQuantite() {
        // Given
        subject.save(INGREDIENT_PLAT);
        IngredientPlat old_ingredientplat = subject.findOneById(INGREDIENT_PLAT.getIngredient().getId(), INGREDIENT_PLAT.getPlat().getId());

        // When
        INGREDIENT_PLAT.setQuantite(200);
        subject.save(INGREDIENT_PLAT);
        IngredientPlat new_ingredientplat = subject.findOneById(INGREDIENT_PLAT.getIngredient().getId(), INGREDIENT_PLAT.getPlat().getId());

        // Then
        Assertions.assertEquals(200, new_ingredientplat.getQuantite(), "La quantite devrait être modifiée");
        Assertions.assertEquals(old_ingredientplat.getIngredient(), new_ingredientplat.getIngredient(), "L'ingrédient ne devrait pas changer");
        Assertions.assertEquals(old_ingredientplat.getPlat(), new_ingredientplat.getPlat(), "Le plat ne devrait pas changer");
    }

    @Test
    @DisplayName("Devrait ne plus être présent dans la base")
    void shouldDelete() {
        // Given
        subject.save(INGREDIENT_PLAT);
        boolean before = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // When
        subject.deleteById(INGREDIENT_PLAT.getIngredient().getId(), INGREDIENT_PLAT.getPlat().getId());
        boolean actual = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // Then
        Assertions.assertTrue(before);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait ne rien changer")
    void shouldDeleteNotCreateIngredient() {
        // Given
        boolean before = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // When
        subject.deleteById(INGREDIENT_PLAT.getIngredient().getId(), INGREDIENT_PLAT.getPlat().getId());
        boolean actual = subject.checkIfExistIngredientPlat(INGREDIENT_PLAT);

        // Then
        Assertions.assertFalse(before);
        Assertions.assertFalse(actual);
    }
}