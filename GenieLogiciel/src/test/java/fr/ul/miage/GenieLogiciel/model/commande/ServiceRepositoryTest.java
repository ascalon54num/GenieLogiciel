package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.model.service.Service;
import fr.ul.miage.GenieLogiciel.model.service.ServiceRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Optional;

class ServiceRepositoryTest {
    private static final String SERVICE_DU_SOIR = "Service du soir";
    private static final LocalDate JANVIER_2021 = LocalDate.of(2021, 1, 1);
    private Service service;

    private ServiceRepository subject;

    @BeforeEach
    void init() {
        service = new Service().setLibelle(SERVICE_DU_SOIR).setDateDebut(JANVIER_2021);
        this.subject = new ServiceRepository();
    }

    @AfterEach
    void finish() {
        subject.deleteById(service.getId());
    }

    @Test
    @DisplayName("Devrait avoir un élément")
    void shouldFindByIdOk() {
        // When
        subject.save(service);
        boolean actual = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait ne pas avoir d'élément")
    void shouldFindByIdKo() {
        // When
        boolean actual = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait être ajouté en base")
    void shouldCreate() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // When
        subject.save(service);
        boolean actual = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditLibelle() {
        // Given
        subject.save(service);
        Service old_service = subject.findOneById(service.getId());
        String SERVICE_DU_SOIR_2 = "Service 2 du soir";

        // When
        service.setLibelle(SERVICE_DU_SOIR_2);
        subject.save(service);
        Service new_service = subject.findOneById(service.getId());

        // Then
        Assertions.assertEquals(SERVICE_DU_SOIR_2, new_service.getLibelle(), "Le libelle devrait être modifié");
        Assertions.assertEquals(old_service.getDateDebut(), new_service.getDateDebut(), "La date ne devrait pas changer");
        Assertions.assertEquals(old_service.getId(), new_service.getId(), "L'id ne devrait pas changer");
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditDate() {
        // Given
        subject.save(service);
        Service old_service = subject.findOneById(service.getId());
        LocalDate FEVRIER_2021 = LocalDate.of(2021, 2, 1);

        // When
        service.setDateDebut(FEVRIER_2021);
        subject.save(service);
        Service new_service = subject.findOneById(service.getId());

        // Then
        Assertions.assertEquals(old_service.getLibelle(), new_service.getLibelle(), "Le libelle ne devrait pas être modifié");
        Assertions.assertEquals(FEVRIER_2021, new_service.getDateDebut(), "La date devrait changer");
        Assertions.assertEquals(old_service.getId(), new_service.getId(), "L'id ne devrait pas changer");
    }

    @Test
    @DisplayName("Devrait ne plus être présent dans la base")
    void shouldDelete() {
        // Given
        subject.save(service);
        boolean before = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // When
        subject.deleteById(service.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // Then
        Assertions.assertTrue(before);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait ne rien changer")
    void shouldDeleteNotCreateIngredient() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // When
        subject.deleteById(service.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(service.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertFalse(actual);

    }
}