package fr.ul.miage.GenieLogiciel.model.categorie;

import org.junit.jupiter.api.*;

import java.util.Optional;

class CategorieRepositoryTest {
    private static Categorie POISSON;

    private CategorieRepository subject;

    @BeforeEach
    void init() {
        POISSON = new Categorie().setLibelle("PoissonTest");
        this.subject = new CategorieRepository();
    }

    @AfterEach
    void finish() {
        subject.deleteById(POISSON.getId());
    }

    @Test
    @DisplayName("Devrait avoir un élément")
    void shouldFindByIdOk() {
        // When
        subject.save(POISSON);
        boolean actual = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait ne pas avoir d'élément")
    void shouldFindByIdKo() {
        // When
        boolean actual = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait être ajouté en base")
    void shouldCreate() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // When
        subject.save(POISSON);
        boolean actual = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditLibelle() {
        // Given
        subject.save(POISSON);
        Categorie old_poisson = subject.findOneById(POISSON.getId());

        // When
        POISSON.setLibelle("PasPoissonTest");
        subject.save(POISSON);
        Categorie new_categorie = subject.findOneById(POISSON.getId());

        // Then
        Assertions.assertEquals("PasPoissonTest", new_categorie.getLibelle(), "Le libelle devrait changer");
        Assertions.assertEquals(old_poisson.getId(), new_categorie.getId(), "L'id ne devrait pas changer");
    }

    @Test
    @DisplayName("Devrait ne plus être présent dans la base")
    void shouldDelete() {
        // Given
        subject.save(POISSON);
        boolean before = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // When
        subject.deleteById(POISSON.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // Then
        Assertions.assertTrue(before);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait ne rien changer")
    void shouldDeleteNotCreateIngredient() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // When
        subject.deleteById(POISSON.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(POISSON.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertFalse(actual);

    }
}