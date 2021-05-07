package fr.ul.miage.GenieLogiciel.model.categorie;

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

import java.util.TreeMap;
import java.util.Map;

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
    @DisplayName("Devrait retourner 1 plat identique à celui qui a été créé")
    void shouldGetPlatsOk() {
        // Given
        subject.setId(1).setLibelle("Poisson");
        Plat COLIN_FUME = new Plat().setId(10).setCategorie(subject).setDisponible(true).setPrix(7.20).setLibelle("Colin fumé").setPlatDuJour(true);

        // When
        Mockito.when(categorieRepository.findPlatsByIdCategory(subject.getId())).thenReturn(new TreeMap<>() {{
            put(10, COLIN_FUME);
        }});
        Map<Integer, Plat> plats = subject.getPlats();

        // Then
        Assertions.assertFalse(plats.isEmpty());
        Assertions.assertEquals(plats.get(10), COLIN_FUME);
    }

    @Test
    @DisplayName("Devrait retourner 0 plat")
    void shouldGetPlatsOkZero() {
        // Given
        subject.setId(1).setLibelle("Poisson");

        // When
        Mockito.when(categorieRepository.findPlatsByIdCategory(subject.getId())).thenReturn(new TreeMap<>());
        Map<Integer, Plat> plats = subject.getPlats();

        // Then
        Assertions.assertTrue(plats.isEmpty());
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