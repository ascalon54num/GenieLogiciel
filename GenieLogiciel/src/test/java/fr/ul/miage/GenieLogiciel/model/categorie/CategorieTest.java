package fr.ul.miage.GenieLogiciel.model.categorie;

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
class CategorieTest {
    private Categorie subject;

    @Mock
    private CategorieRepository categorieRepository;

    @Captor
    private ArgumentCaptor<Categorie> categorieArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idCategorieArgumentCaptor;

    @BeforeEach
    void init() {
        subject = new Categorie(categorieRepository);
    }

    @Test
    @DisplayName("Devrait retourner la même catégorie")
    void shouldSaveOk() {
        // Given
        subject.setId(1).setLibelle("Poisson");

        // When
        subject.save();

        // Then
        Mockito.verify(categorieRepository).save(categorieArgumentCaptor.capture());
        Categorie actual = categorieArgumentCaptor.getValue();

        Assertions.assertEquals(subject.getId(), actual.getId());
        Assertions.assertEquals(subject.getLibelle(), actual.getLibelle());
    }

    @Test
    @DisplayName("Devrait supprimer le bon id de catégorie")
    void shouldDeleteOk() {
        // Given
        subject.setId(1).setLibelle("Poisson");

        // When
        subject.delete();

        // Then
        Mockito.verify(categorieRepository).deleteById(idCategorieArgumentCaptor.capture());
        int actual = idCategorieArgumentCaptor.getValue();

        Assertions.assertEquals(subject.getId(), actual);
    }
}