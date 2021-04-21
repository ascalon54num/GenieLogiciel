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
        this.password=null;
    }

    public Connection getConnection(){
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(url, user, password);
            LOG.warning("Connexion établie avec la base de données");
        } catch (SQLException e) {
        	e.printStackTrace();
            LOG.severe("Erreur connection à la base de données impossible\r\n"+e.getMessage());
        }
        return connexion;
    }
}
