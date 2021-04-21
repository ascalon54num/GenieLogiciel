package fr.ul.miage.GenieLogiciel;

import java.sql.Connection;

import fr.ul.miage.GenieLogiciel.controller.BddController;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        BddController bddController = new BddController();
        Connection connect = bddController.getConnection();
    }
}
