package fr.ul.miage.GenieLogiciel.model.ingredient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class IngredientRepositoryTest {

    private static Ingredient CAROTTE;

    private IngredientRepository subject;

    @BeforeEach
    void init() {
        CAROTTE = new Ingredient().setLibelle("Carotte").setQuantite(1);
        this.subject = new IngredientRepository();
    }

    @Test
    @DisplayName("Devrait avoir un élément")
    void shouldFindByIdOk() {
        // When
        subject.save(CAROTTE);
        boolean actual = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // Then
        Assertions.assertTrue(actual);

        subject.deleteById(CAROTTE.getId());
    }

    @Test
    @DisplayName("Devrait ne pas avoir d'élément")
    void shouldFindByIdKo() {
        // When
        boolean actual = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait être ajouté en base")
    void shouldCreate() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // When
        subject.save(CAROTTE);
        boolean actual = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertTrue(actual);

        subject.deleteById(CAROTTE.getId());
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditQuantite() {
        // Given
        subject.save(CAROTTE);
        Ingredient old_carotte = subject.findOneById(CAROTTE.getId());

        // When
        CAROTTE.setQuantite(10);
        subject.save(CAROTTE);
        Ingredient new_carotte = subject.findOneById(CAROTTE.getId());

        // Then
        Assertions.assertEquals(10, new_carotte.getQuantite(), "La quantite devrait être modifiée");
        Assertions.assertEquals(old_carotte.getLibelle(), new_carotte.getLibelle(), "Le libelle ne devrait pas changer");
        Assertions.assertEquals(old_carotte.getId(), new_carotte.getId(), "L'id ne devrait pas changer");

        subject.deleteById(CAROTTE.getId());
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditLibelle() {
        // Given
        subject.save(CAROTTE);
        Ingredient old_carotte = subject.findOneById(CAROTTE.getId());

        // When
        CAROTTE.setLibelle("Radis");
        subject.save(CAROTTE);
        Ingredient new_carotte = subject.findOneById(CAROTTE.getId());

        // Then
        Assertions.assertEquals(old_carotte.getQuantite(), new_carotte.getQuantite(), "La quantite ne devrait pas être modifiée");
        Assertions.assertEquals("Radis", new_carotte.getLibelle(), "Le libelle devrait changer");
        Assertions.assertEquals(old_carotte.getId(), new_carotte.getId(), "L'id ne devrait pas changer");

        subject.deleteById(CAROTTE.getId());
    }

    @Test
    @DisplayName("Devrait ne plus être présent dans la base")
    void shouldDelete() {
        // Given
        subject.save(CAROTTE);
        boolean before = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // When
        subject.deleteById(CAROTTE.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // Then
        Assertions.assertTrue(before);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait ne rien changer")
    void shouldDeleteNotCreateIngredient() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // When
        subject.deleteById(CAROTTE.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(CAROTTE.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertFalse(actual);

    }
}