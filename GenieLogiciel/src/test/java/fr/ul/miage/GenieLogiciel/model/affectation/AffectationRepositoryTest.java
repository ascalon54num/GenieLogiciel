package fr.ul.miage.GenieLogiciel.model.affectation;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
;

public class AffectationRepositoryTest {
	
	private AffectationRepository subject;
	 private static Affectation affectation1;
	
	@BeforeEach
   void init() {
		affectation1 = new Affectation().setIdServeur(3).setIdTable(1);
       this.subject = new AffectationRepository();
   }
	
	 @Test
	    @DisplayName("Devrait avoir un élément")
	    void shouldFindByIdOk() {
	        // When
	        subject.save(affectation1);
	        boolean actual = Optional.ofNullable(subject.findOneById(affectation1.getId())).isPresent();

	        // Then
	        Assertions.assertTrue(actual);

	        subject.deleteById(affectation1.getId());
	    }

	    @Test
	    @DisplayName("Devrait ne pas avoir d'élément")
	    void shouldFindByIdKo() {
	        // When
	        boolean actual = Optional.ofNullable(subject.findOneById(affectation1.getId())).isPresent();

	        // Then
	        Assertions.assertFalse(actual);
	    }
	    
	    @Test
	    @DisplayName("Devrait avoir un élément par la table")
	    void shouldFindByTableOk() {
	        // When
	        subject.save(affectation1);
	        boolean actual = Optional.ofNullable(subject.findOneByTable(affectation1.getIdTable())).isPresent();

	        // Then
	        Assertions.assertTrue(actual);

	        subject.deleteById(affectation1.getId());
	    }

	    @Test
	    @DisplayName("Devrait ne pas avoir d'élément par la table")
	    void shouldFindByTableKo() {
	        // When
	        boolean actual = Optional.ofNullable(subject.findOneByTable(affectation1.getIdTable())).isPresent();

	        // Then
	        Assertions.assertFalse(actual);
	    }
	    
	    @Test
	    @DisplayName("Devrait être ajouté en base")
	    void shouldCreate() {
	        // Given
	        boolean before = Optional.ofNullable(subject.findOneById(affectation1.getId())).isPresent();

	        // When
	        subject.save(affectation1);
	        boolean actual = Optional.ofNullable(subject.findOneById(affectation1.getId())).isPresent();

	        // Then
	        Assertions.assertFalse(before);
	        Assertions.assertTrue(actual);

	        subject.deleteById(affectation1.getId());
	    }
}
