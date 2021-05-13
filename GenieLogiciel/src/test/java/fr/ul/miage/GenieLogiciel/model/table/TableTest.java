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

import fr.ul.miage.GenieLogiciel.utils.Constantes;


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
	@DisplayName("Devrait retourner la même table")
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
	
	 @Test
	   @DisplayName("Devrait avoir le statut modifié")
	   void shouldModifyStatus() {
		 	// Given
			subject.setId(1).setStatut(Constantes.STATUS_TABLE[0]).setNbCouvert(5);

			// When
			subject.setStatut(Constantes.STATUS_TABLE[1]);
			subject.save();
			Mockito.verify(tableRepository).save(tableArgumentCaptor.capture());
			Table actual = tableArgumentCaptor.getValue();

			// Then
			Assertions.assertEquals(Constantes.STATUS_TABLE[1], actual.getStatut());
	   }
}
