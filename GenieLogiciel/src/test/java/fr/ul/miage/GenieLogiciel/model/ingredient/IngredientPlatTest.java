package fr.ul.miage.GenieLogiciel.model.ingredient;

import fr.ul.miage.GenieLogiciel.model.categorie.Categorie;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
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

@ExtendWith(MockitoExtension.class)
class IngredientPlatTest {
    private IngredientPlat subject;

    @Mock
    private IngredientPlatRepository ingredientPlatRepository;

    @Captor
    private ArgumentCaptor<IngredientPlat> ingredientPlatArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idIngredientArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idPlatArgumentCaptor;

    @BeforeEach
    void init() {
        Ingredient CAROTTE = new Ingredient().setId(1).setLibelle("Carotte").setQuantite(50);
        Categorie LEGUME = new Categorie().setId(1).setLibelle("Légume");
        Plat SALADE_CAROTTE = new Plat().setLibelle("Salade de carotte").setCategorie(LEGUME).setId(1).setPlatDuJour(false).setDisponible(true).setPrix(4.5);
        subject = new IngredientPlat(ingredientPlatRepository).setIngredient(CAROTTE).setQuantite(10).setPlat(SALADE_CAROTTE);
    }

    @Test
    @DisplayName("Devrait ne pas pouvoir être utilisé (50 - 100 < 0)")
    void shouldCanUseKo() {
        // Given
        subject.setQuantite(100);

        // When
        boolean actual = subject.canUse();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait pouvoir être utilisé (50 - 30 = 20)")
    void shouldCanUseOk() {
        // Given
        subject.setQuantite(30);

        // When
        boolean actual = subject.canUse();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait modifier la quantite (50 - 30 = 20)")
    void shouldUseOk() {
        // Given
        subject.setQuantite(30);

        // When
        subject.utiliser();

        // Then
        Assertions.assertEquals(20, subject.getIngredient().getQuantite());
    }

    @Test
    @DisplayName("Devrait ne pas modifier la quantite (50 - 100 < 0)")
    void shouldUseKo() {
        // Given
        subject.setQuantite(100);

        // When
        subject.utiliser();

        // Then
        Assertions.assertEquals(50, subject.getIngredient().getQuantite());
    }

    @Test
    @DisplayName("Devrait retourner le même ingredientPlat")
    void shouldSaveOk() {
        // Given
        subject.setQuantite(200);

        // When
        subject.save();

        // Then
        Mockito.verify(ingredientPlatRepository).save(ingredientPlatArgumentCaptor.capture());
        IngredientPlat actual = ingredientPlatArgumentCaptor.getValue();

        Assertions.assertEquals(subject.getQuantite(), actual.getQuantite());
    }

    @Test
    @DisplayName("Devrait supprimer le bon id d'ingredientPlat")
    void shouldDeleteOk() {
        // Given

        // When
        subject.delete();

        // Then
        Mockito.verify(ingredientPlatRepository).deleteById(idIngredientArgumentCaptor.capture(), idPlatArgumentCaptor.capture());
        int actualIngredient = idIngredientArgumentCaptor.getValue();
        int actualPlat = idPlatArgumentCaptor.getValue();

        Assertions.assertEquals(subject.getIngredient().getId(), actualIngredient);
        Assertions.assertEquals(subject.getPlat().getId(), actualPlat);
    }
}