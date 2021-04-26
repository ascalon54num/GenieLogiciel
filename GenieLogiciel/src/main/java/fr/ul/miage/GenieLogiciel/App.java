package fr.ul.miage.GenieLogiciel;

import java.sql.Connection;
import java.sql.SQLException;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.User;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        System.out.println( "Hello World!" );
        BddController bddController = new BddController();
        Connection connect = bddController.getConnection();
        
        //test de la fonction ajouter Utilisateur
        //User utilisateur = new User(); 
        //utilisateur.addUser(5, "nasri", "nasri", "manel", 1);
    }
}
