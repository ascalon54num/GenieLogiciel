package fr.ul.miage.GenieLogiciel;

import java.sql.Connection;
import java.util.Scanner;

import fr.ul.miage.GenieLogiciel.controller.CommandeController;
import fr.ul.miage.GenieLogiciel.controller.IdentificationController;
import fr.ul.miage.GenieLogiciel.utils.Session;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
