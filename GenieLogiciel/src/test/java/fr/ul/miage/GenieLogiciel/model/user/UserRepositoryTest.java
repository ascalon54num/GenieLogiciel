package fr.ul.miage.GenieLogiciel.model.user;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserRepositoryTest {
    private static User testUser;

    private UserRepository subject;

    @BeforeEach
    void init() {
        testUser = new User().setLogin("testLogin").setNom("testNom").setPrenom("testPrenom").setRole(1);
        this.subject = new UserRepository();
    }

    @AfterEach
    void finish() {
        subject.deleteById(testUser.getId());
    }

    @Test
    @DisplayName("Devrait avoir un élément")
    void shouldFindByIdOk() {
        // When
        subject.save(testUser);
        boolean actual = (null!=subject.findOneById(testUser.getId()));

        // Then
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait ne pas avoir d'élément")
    void shouldFindByIdKo() {
        // When
        boolean actual = Optional.ofNullable(subject.findOneById(testUser.getId())).isPresent();

        // Then
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait être ajouté en base")
    void shouldCreate() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(testUser.getId())).isPresent();

        // When
        subject.save(testUser);
        boolean actual = Optional.ofNullable(subject.findOneById(testUser.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Devrait être modifié en base")
    void shouldEditLogin() {
        // Given
        subject.save(testUser);
        User old_testUser = subject.findOneById(testUser.getId());

        // When
        testUser.setLogin("testNouvauLogin");
        subject.save(testUser);
        User new_testUser = subject.findOneById(testUser.getId());

        // Then
        Assertions.assertEquals("testNouvauLogin", new_testUser.getLogin(), "Le login de l'utilisateur devrait changer");
        Assertions.assertEquals(old_testUser.getId(), new_testUser.getId(), "L'id ne devrait pas changer");
    }

    @Test
    @DisplayName("Devrait ne plus être présent dans la base")
    void shouldDelete() {
    	
    	 //User testUser =     new User().setLogin("testLogin").setNom("testNom").setPrenom("testPrenom").setRole(1);
    	 //UserRepository subject  = new UserRepository();

         
        // Given
        subject.save(testUser);
        boolean before = false; 
        if (subject.findOneById(testUser.getId())!=null)
        {
        	before =true; 
        }
        		
        		
        	//	Optional.ofNullable().isPresent();

        // When
        subject.deleteById(testUser.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(testUser.getId())).isPresent();

        // Then
        Assertions.assertTrue(before);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("Devrait ne rien changer")
    void shouldDeleteNotCreateIngredient() {
        // Given
        boolean before = Optional.ofNullable(subject.findOneById(testUser.getId())).isPresent();

        // When
        subject.deleteById(testUser.getId());
        boolean actual = Optional.ofNullable(subject.findOneById(testUser.getId())).isPresent();

        // Then
        Assertions.assertFalse(before);
        Assertions.assertFalse(actual);

    }
}
