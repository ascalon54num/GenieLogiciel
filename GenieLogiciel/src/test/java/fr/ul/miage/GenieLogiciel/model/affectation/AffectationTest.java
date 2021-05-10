package fr.ul.miage.GenieLogiciel.model.affectation;

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
public class AffectationTest {
	private Affectation subject;
	@Mock
	private AffectationRepository affectationRepository;

	@Captor
	private ArgumentCaptor<Affectation> affectationArgumentCaptor;
	@Captor
	private ArgumentCaptor<Integer> idAffectationArgumentCaptor;
	
	@BeforeEach
	void init() {
		subject = new Affectation(affectationRepository);
	}

	@Test
	@DisplayName("Devrait retourner la même table")
	void shouldSaveOk() {
		// Given
		subject.setId(1000).setIdServeur(3).setIdTable(1);

		// When
		subject.save();

		// Then
		Mockito.verify(affectationRepository).save(affectationArgumentCaptor.capture());
		Affectation actual = affectationArgumentCaptor.getValue();

		Assertions.assertEquals(subject.getId(), actual.getId());
		Assertions.assertEquals(subject.getIdServeur(), actual.getIdServeur());
		Assertions.assertEquals(subject.getIdTable(), actual.getIdTable());
	}
}
