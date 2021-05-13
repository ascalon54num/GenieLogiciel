package fr.ul.miage.GenieLogiciel.model.user;

import static org.junit.jupiter.api.Assertions.*;

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

import fr.ul.miage.GenieLogiciel.model.plat.Plat;

@ExtendWith(MockitoExtension.class)
class UserTest {
	
	private User subject;
	 
	@Mock
    private UserRepository userRepository ;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    
    @Captor
    private ArgumentCaptor<Integer> idUserArgumentCaptor;
    
    
    @BeforeEach
    void init() {
        subject = new User(userRepository);
    }
    
	 @Test
	    @DisplayName("Devrait supprimer le bon utilisateur (le bon id)")
	    void shouldDeleteOk() {
	        // Given
	       subject.setId(1);
	       subject.setLogin("manelNasri");
	       subject.setNom("NASRI");
	       subject.setPrenom("Manel");
	       subject.setRole(1);

	        // When
	        subject.delete();

	        // Then
	        Mockito.verify(userRepository).deleteById(idUserArgumentCaptor.capture());
	        int actual = idUserArgumentCaptor.getValue();
	        Assertions.assertEquals(subject.getId(), actual);
	    }
	 
	    @Test
	    @DisplayName("Devrait retourner le même utilisateur")
	    void shouldSaveOk() {
	        // Given
	        subject.setId(1); 
	        subject.setLogin("loginTest");

	        // When
	        subject.save();

	        // Then
	        Mockito.verify(userRepository).save(userArgumentCaptor.capture());
	        User actual = userArgumentCaptor.getValue();

	        Assertions.assertEquals(subject, actual);
	    }


}
