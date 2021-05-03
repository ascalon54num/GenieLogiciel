package fr.ul.miage.GenieLogiciel.model.categorie;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;
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
    @DisplayName("Devrait avoir 1 plat")
    void shouldFindPlatsByIdCategoryOk() {
        // Given
        POISSON.save();
        Plat COLIN_FUME = new Plat().setCategorie(POISSON).setDisponible(true).setPrix(7.20).setLibelle("Colin fumé").setPlatDuJour(true);
        COLIN_FUME.save();
        // When
        subject.save(POISSON);
        boolean actual = subject.findPlatsByIdCategory(POISSON.getId()).isEmpty();

        // Then
        COLIN_FUME.delete();
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait ne pas avoir de plat")
    void shouldFindPlatsByIdCategoryKo() {
        // When
        boolean actual = subject.findPlatsByIdCategory(POISSON.getId()).isEmpty();

        // Then
        Assertions.assertTrue(actual);
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