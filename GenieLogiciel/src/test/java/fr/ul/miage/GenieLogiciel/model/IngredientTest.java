package fr.ul.miage.GenieLogiciel.model;

import fr.ul.miage.GenieLogiciel.model.repository.IngredientRepository;
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
class IngredientTest {
    private Ingredient subject;

    @Mock
    private IngredientRepository ingredientRepository;

    @Captor
    private ArgumentCaptor<Ingredient> ingredientArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idIngredientArgumentCaptor;

    @BeforeEach
    void init() {
        subject = new Ingredient(ingredientRepository);
    }

    @Test
    @DisplayName("Devrait retourner 40 car 50 - 10")
    void shouldUtiliserOk() {
        // Given
        int quantiteCarotteAUtiliser = 10;
        int quantiteCarotteInitial = 50;
        subject.setId(1).setLibelle("Carotte").setQuantite(quantiteCarotteInitial);

        // When
        subject.utiliser(quantiteCarotteAUtiliser);

        // Then
        Assertions.assertEquals(quantiteCarotteInitial - quantiteCarotteAUtiliser, this.subject.getQuantite());
    }

    @Test
    @DisplayName("Devrait retourner qté initiale car insuffisante")
    void shouldUtiliserKo() {
        // Given
        int quantiteCarotteAUtiliser = 10;
        int quantiteCarotteInitial = 5;
        subject.setId(1).setLibelle("Carotte").setQuantite(quantiteCarotteInitial);

        // When
        subject.utiliser(quantiteCarotteAUtiliser);

        // Then
        Assertions.assertEquals(quantiteCarotteInitial, this.subject.getQuantite());
    }

    @Test
    @DisplayName("Devrait retourner 0 car qté totale = qté utilisée")
    void shouldUtiliserReturnZero() {
        // Given
        int quantiteCarotteAUtiliser = 5;
        int quantiteCarotteInitial = 5;
        subject.setId(1).setLibelle("Carotte").setQuantite(quantiteCarotteInitial);

        // When
        subject.utiliser(quantiteCarotteAUtiliser);

        // Then
        Assertions.assertEquals(0, this.subject.getQuantite());
    }

    @Test
    @DisplayName("Devrait retourner 60 car 50 + 10")
    void shouldAjouterOk() {
        // Given
        int quantiteCarotteAAjouter = 10;
        int quantiteCarotteInitial = 50;
        subject.setId(1).setLibelle("Carotte").setQuantite(quantiteCarotteInitial);

        // When
        subject.ajouter(quantiteCarotteAAjouter);

        // Then
        Assertions.assertEquals(quantiteCarotteInitial + quantiteCarotteAAjouter, this.subject.getQuantite());
    }

    @Test
    @DisplayName("Devrait retourner le même ingrédient")
    void shouldSaveOk() {
        // Given
        subject.setId(1).setLibelle("Carotte").setQuantite(50);

        // When
        subject.save();

        // Then
        Mockito.verify(ingredientRepository).save(ingredientArgumentCaptor.capture());
        Ingredient actual = ingredientArgumentCaptor.getValue();

        Assertions.assertEquals(subject.getId(), actual.getId());
        Assertions.assertEquals(subject.getLibelle(), actual.getLibelle());
        Assertions.assertEquals(subject.getQuantite(), actual.getQuantite());
    }

    @Test
    @DisplayName("Devrait supprimer le bon id d'ingrédient")
    void shouldDeleteOk() {
        // Given
        subject.setId(1).setLibelle("Carotte").setQuantite(50);

        // When
        subject.delete();

        // Then
        Mockito.verify(ingredientRepository).deleteById(idIngredientArgumentCaptor.capture());
        int actual = idIngredientArgumentCaptor.getValue();

        Assertions.assertEquals(subject.getId(), actual);
    }
}