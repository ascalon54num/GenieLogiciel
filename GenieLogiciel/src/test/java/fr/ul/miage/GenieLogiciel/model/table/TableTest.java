package fr.ul.miage.GenieLogiciel.model.table;

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

import fr.ul.miage.GenieLogiciel.model.ingredient.Ingredient;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientRepository;

@ExtendWith(MockitoExtension.class)
public class TableTest {

	private Table subject;
	@Mock
	private TableRepository tableRepository;

	@Captor
	private ArgumentCaptor<Table> tableArgumentCaptor;
	@Captor
	private ArgumentCaptor<Integer> idTableArgumentCaptor;

	@BeforeEach
	void init() {
		subject = new Table(tableRepository);
	}

	@Test
	@DisplayName("Devrait retourner le même ingrédient")
	void shouldSaveOk() {
		// Given
		subject.setId(1).setStatut("Libre").setNbCouvert(5);

		// When
		subject.save();

		// Then
		Mockito.verify(tableRepository).save(tableArgumentCaptor.capture());
		Table actual = tableArgumentCaptor.getValue();

		Assertions.assertEquals(subject.getId(), actual.getId());
		Assertions.assertEquals(subject.getNbCouvert(), actual.getNbCouvert());
		Assertions.assertEquals(subject.getStatut(), actual.getStatut());
	}
}
