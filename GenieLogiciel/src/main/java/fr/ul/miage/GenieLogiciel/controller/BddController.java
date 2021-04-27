package fr.ul.miage.GenieLogiciel.controller;

import java.sql.*;
import java.util.logging.Logger;

import fr.ul.miage.GenieLogiciel.utils.ConfigReader;


public class BddController {
    //Logger pour gérer les messages console
	private static final Logger LOG = Logger.getLogger(BddController.class.getName());
    private String url;
    private String user;
    private String password;

    public BddController (){
        this.url=ConfigReader.getProp("urlBdd");
        this.user=ConfigReader.getProp("userBdd");
        String pwd = ConfigReader.getProp("passwordBdd");
        if (pwd.equals("null")){
            this.password = null;
        } else {
            this.password = pwd;
        }
    }

    public Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie avec la base de données");
        } catch (SQLException e) {
            LOG.severe("Erreur connection à la base de données impossible\r\n"+e.getMessage());
        }
        return connexion;
    }
}
