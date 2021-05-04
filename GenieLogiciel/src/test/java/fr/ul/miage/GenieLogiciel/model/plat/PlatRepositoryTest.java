package fr.ul.miage.GenieLogiciel.model.plat;

import fr.ul.miage.GenieLogiciel.model.categorie.Categorie;
import org.junit.jupiter.api.*;

import java.util.Optional;

class PlatRepositoryTest {
    private static Plat COLIN_FUME;
    private static Categorie POISSON;

    private PlatRepository subject;

    @BeforeEach
    void init() {
        POISSON = new Categorie().setLibelle("PoissonTest");
        COLIN_FUME = new Plat().setLibelle("Colin Fumé Test").setCategorie(POISSON);
        this.subject = new PlatRepository();
    }

    @AfterEach
    void finish() {
        subject.deleteById(COLIN_FUME.getId());
        POISSON.delete();
    }

    @Test
    @DisplayName("Devrait avoir un élément")
    void shouldFindByIdOk() {
        // When
        POISSON.save();
        subject.save(COLIN_FUME);
        boolean actual = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait ne pas avoir d'élément")
    void shouldFindByIdKo() {
        // When
        boolean actual = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait être ajouté en base")
    void shouldCreate() {
        // Given
        POISSON.save();
        boolean before = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // When
        subject.save(COLIN_FUME);
        boolean actual = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditLibelle() {
        // Given
        POISSON.save();
        subject.save(COLIN_FUME);
        Plat old_COLIN_FUME = subject.findOneById(COLIN_FUME.getId());

        // When
        COLIN_FUME.setLibelle("Pas Colin fumé Test");
        subject.save(COLIN_FUME);
        Plat new_COLIN_FUME = subject.findOneById(COLIN_FUME.getId());

        // Then
        Assertions.assertEquals("Pas Colin fumé Test", new_COLIN_FUME.getLibelle(), "Le libelle devrait changer");
        Assertions.assertEquals(old_COLIN_FUME.getId(), new_COLIN_FUME.getId(), "L'id ne devrait pas changer");
    }

    @Test
    @DisplayName("Devrait ne plus être présent dans la base")
    void shouldDelete() {
        // Given
        POISSON.save();
        subject.save(COLIN_FUME);
        boolean before = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // When
        subject.deleteById(COLIN_FUME.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // Then
        Assertions.assertTrue(before);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait ne rien changer")
    void shouldDeleteNotCreateIngredient() {
        // Given
        POISSON.save();
        boolean before = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // When
        subject.deleteById(COLIN_FUME.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(COLIN_FUME.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertFalse(actual);

    }
}