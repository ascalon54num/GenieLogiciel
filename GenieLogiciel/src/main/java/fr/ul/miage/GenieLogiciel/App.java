package fr.ul.miage.GenieLogiciel;

import java.sql.Connection;
<<<<<<< HEAD
import java.util.Scanner;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.controller.IdentificationController;
import fr.ul.miage.GenieLogiciel.utils.Session;
=======
import java.sql.SQLException;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.User;
>>>>>>> 08a5585aa600be650fc56aa5ef4226968fdac1f5


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        /**
         * Ce test n'a rien à faire là, cela ce fait dans une classe de test
         * BddController bddController = new BddController();
        Connection connect = bddController.getConnection();
        
        //test de la fonction ajouter Utilisateur
        User utilisateur = new User(); 
        utilisateur.addUser(5, "nasri", "nasri", "manel", 1);
         */
        System.out.println( "Bienvenu sur Resto-gestio !" );
        Scanner sc = new Scanner(System.in);
        IdentificationController idController = new IdentificationController(sc);
        idController.initIdentification();
        while(Session.getInstance().getCurrentUser() == null) {
        	idController.initIdentification();
        }
        CommandeController commande = new CommandeController(sc);
        commande.executeTest();
    }
}
