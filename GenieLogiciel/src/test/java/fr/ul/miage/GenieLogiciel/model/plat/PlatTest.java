package fr.ul.miage.GenieLogiciel.model.plat;

import fr.ul.miage.GenieLogiciel.model.ingredient.Ingredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PlatTest {
    private Plat subject;

    @Mock
    private PlatRepository platRepository;

    @Captor
    private ArgumentCaptor<Plat> platArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idPlatArgumentCaptor;

    @BeforeEach
    void init() {
        subject = new Plat(platRepository);
    }

    @Test
    @DisplayName("Devrait pouvoir être choisi")
    void shouldCanChooseOk() {
        // Given
        Ingredient CAROTTE = new Ingredient().setQuantite(50);
        IngredientPlat INGREDIENT_PLAT = new IngredientPlat().setIngredient(CAROTTE).setPlat(subject).setQuantite(20);
        List<IngredientPlat> ingredients = new ArrayList<>();
        ingredients.add(INGREDIENT_PLAT);
        subject.setLibelle("Salade de carotte").setCategorie(null).setId(1).setPlatDuJour(false).setDisponible(true).setPrix(4.5).setIngredients(ingredients);

        // When
        boolean actual = subject.canChoose();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait pas être choisissable")
    void shouldCanChooseKo() {
        // Given
        Ingredient CAROTTE = new Ingredient().setQuantite(50);
        IngredientPlat INGREDIENT_PLAT = new IngredientPlat().setIngredient(CAROTTE).setPlat(subject).setQuantite(100);
        List<IngredientPlat> ingredients = new ArrayList<>();
        ingredients.add(INGREDIENT_PLAT);
        subject.setLibelle("Salade de carotte").setCategorie(null).setId(1).setPlatDuJour(false).setDisponible(true).setPrix(4.5).setIngredients(ingredients);

        // When
        boolean actual = subject.canChoose();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait être choisi (50 - 30 = 20)")
    void shouldChoisirOk() {
        // Given
        Ingredient CAROTTE = new Ingredient().setQuantite(50);
        IngredientPlat INGREDIENT_PLAT = new IngredientPlat().setIngredient(CAROTTE).setPlat(subject).setQuantite(30);
        List<IngredientPlat> ingredients = new ArrayList<>();
        ingredients.add(INGREDIENT_PLAT);
        subject.setLibelle("Salade de carotte").setCategorie(null).setId(1).setPlatDuJour(false).setDisponible(true).setPrix(4.5).setIngredients(ingredients);

        // When
        subject.choisir();

        // Then
        Assertions.assertEquals(20, CAROTTE.getQuantite());
    }

    @Test
    @DisplayName("Devrait pas être choisi (50 - 100 < 0)")
    void shouldChoisirKo() {
        // Given
        Ingredient CAROTTE = new Ingredient().setQuantite(50);
        IngredientPlat INGREDIENT_PLAT = new IngredientPlat().setIngredient(CAROTTE).setPlat(subject).setQuantite(100);
        List<IngredientPlat> ingredients = new ArrayList<>();
        ingredients.add(INGREDIENT_PLAT);
        subject.setLibelle("Salade de carotte").setCategorie(null).setId(1).setPlatDuJour(false).setDisponible(true).setPrix(4.5).setIngredients(ingredients);

        // When
        subject.choisir();

        // Then
        Assertions.assertEquals(50, CAROTTE.getQuantite());
    }

    @Test
    @DisplayName("Devrait retourner le même plat")
    void shouldSaveOk() {
        // Given
        subject.setId(1).setLibelle("Carotte");

        // When
        subject.save();

        // Then
        Mockito.verify(platRepository).save(platArgumentCaptor.capture());
        Plat actual = platArgumentCaptor.getValue();

        Assertions.assertEquals(subject, actual);
    }

    @Test
    @DisplayName("Devrait supprimer le bon plat")
    void shouldDeleteOk() {
        // When
        subject.delete();

        // Then
        Mockito.verify(platRepository).deleteById(idPlatArgumentCaptor.capture());
        int actual = idPlatArgumentCaptor.getValue();

        Assertions.assertEquals(subject.getId(), actual);
    }
}