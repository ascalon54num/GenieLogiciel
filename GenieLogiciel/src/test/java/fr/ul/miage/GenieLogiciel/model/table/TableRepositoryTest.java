package fr.ul.miage.GenieLogiciel.model.table;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.ul.miage.GenieLogiciel.utils.Constantes;


public class TableRepositoryTest {
	
	private TableRepository subject;
	 private static Table table1;
	
	@BeforeEach
    void init() {
		table1 = new Table().setNbCouvert(5).setStatut("Libre");
        this.subject = new TableRepository();
    }
	
	 @Test
	    @DisplayName("Devrait avoir un élément")
	    void shouldFindByIdOk() {
	        // When
	        subject.save(table1);
	        boolean actual = Optional.ofNullable(subject.findOneById(table1.getId())).isPresent();

	        // Then
	        Assertions.assertTrue(actual);

	        subject.deleteById(table1.getId());
	    }

	    @Test
	    @DisplayName("Devrait ne pas avoir d'élément")
	    void shouldFindByIdKo() {
	        // When
	        boolean actual = Optional.ofNullable(subject.findOneById(table1.getId())).isPresent();

	        // Then
	        Assertions.assertFalse(actual);
	    }
	    
	    @Test
	    @DisplayName("Devrait être ajouté en base")
	    void shouldCreate() {
	        // Given
	        boolean before = Optional.ofNullable(subject.findOneById(table1.getId())).isPresent();

	        // When
	        subject.save(table1);
	        boolean actual = Optional.ofNullable(subject.findOneById(table1.getId())).isPresent();

	        // Then
	        Assertions.assertFalse(before);
	        Assertions.assertTrue(actual);

	        subject.deleteById(table1.getId());
	    }
	    
}
