package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandeTest {
    private Commande subject;

    @BeforeEach
    void beforeEach() {
        subject = new Commande();
    }

    @Test
    @DisplayName("Ajout d'un premier plat à la commande")
    void shouldAddFirstPlat() {
        // Given
        Plat CREPES = new Plat().setLibelle("Crêpes");

        // When
        subject.ajouterPlat(CREPES, 5);

        // Then
        Assertions.assertEquals(1, subject.getPlats().size(), "Il devrait y avoir un plat dans la commande");
        Assertions.assertEquals(5, subject.getPlats().get(0).getQuantite(), "Il devrait y avoir 5 quantité de plat");
    }

    @Test
    @DisplayName("Ajout de 3 plats à la commande")
    void shouldAddPlats() {
        // Given
        Plat CREPES = new Plat().setLibelle("Crêpes");
        Plat FRITES = new Plat().setLibelle("Frites");
        Plat ENTRECOTE = new Plat().setLibelle("Entrecôte");

        // When
        subject.ajouterPlat(CREPES, 1);
        subject.ajouterPlat(FRITES, 2);
        subject.ajouterPlat(ENTRECOTE, 3);

        // Then
        Assertions.assertEquals(3, subject.getPlats().size(), "Il devrait y avoir trois plat dans la commande");
        Assertions.assertEquals(1, subject.getPlats().get(0).getQuantite(), "Il devrait y avoir 1 seule assiette");
        Assertions.assertEquals(2, subject.getPlats().get(1).getQuantite(), "Il devrait y avoir 2 assiettes");
        Assertions.assertEquals(3, subject.getPlats().get(2).getQuantite(), "Il devrait y avoir 3 assiettes");
    }

    @Test
    @DisplayName("Ajout de 1 plat avec une mauvaise quantité")
    void shouldAddFirstPlatWithBadQuantity() {
        // Given
        Plat CREPES = new Plat().setLibelle("Crêpes");

        // When
        subject.ajouterPlat(CREPES, -1);

        // Then
        Assertions.assertEquals(0, subject.getPlats().size(), "Il devrait y avoir 0 plat");
    }

    @Test
    @DisplayName("Ajout de 3 plats contenant 1 plat avec mauvaise quantité")
    void shouldAddPlatsWithBadQuantity() {
        // Given
        Plat CREPES = new Plat().setLibelle("Crêpes");
        Plat FRITES = new Plat().setLibelle("Frites");
        Plat ENTRECOTE = new Plat().setLibelle("Entrecôte");

        // When
        subject.ajouterPlat(CREPES, 1);
        subject.ajouterPlat(FRITES, 0);
        subject.ajouterPlat(ENTRECOTE, 3);

        // Then
        Assertions.assertEquals(2, subject.getPlats().size(), "Il devrait y avoir trois plat dans la commande");
        Assertions.assertEquals(1, subject.getPlats().get(0).getQuantite(), "Il devrait y avoir 1 seule assiette");
        Assertions.assertEquals(3, subject.getPlats().get(1).getQuantite(), "Il devrait y avoir 3 assiettes");
    }

    @Test
    @DisplayName("Mise a jour du statut de la commande")
    void shouldMajStatut() {
        // Given
        CommandeStatut STATUT_PRET = new CommandeStatut().setLibelle("Prêt").setId(1);

        // When
        subject.modifierStatut(STATUT_PRET.getId(), STATUT_PRET.getLibelle());

        // Then
        Assertions.assertEquals("Prêt", subject.getStatut().getLibelle());
    }
}