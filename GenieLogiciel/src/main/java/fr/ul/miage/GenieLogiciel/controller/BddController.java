package fr.ul.miage.GenieLogiciel.controller;

import fr.ul.miage.GenieLogiciel.utils.ConfigReader;

import javax.sql.rowset.CachedRowSet;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.logging.Logger;


public class BddController {
    //Logger pour gérer les messages console
    private static final Logger LOG = Logger.getLogger(BddController.class.getName());
    //URL de la BDD
    private String url;
    //Login user
    private String user;
    //password BDD
    private String password;
    private Connection connection;

    public BddController() {
        this.url = ConfigReader.getProp("urlBdd");
        this.user = ConfigReader.getProp("userBdd");
        String pwd = ConfigReader.getProp("passwordBdd");
        if (pwd.equals("null")) {
            this.password = null;
        } else {
            this.password = pwd;
        }
    }
    /**
     * Retourne la connection établie avec la BDD
     * @return Connection
     */
    public Connection getConnection() {
       if(connection == null) {
           try {
               connection = DriverManager.getConnection(url, user, password);
           } catch (SQLException e) {
               LOG.severe("Erreur connection à la base de données impossible\r\n" + e.getMessage());
           }
       }

        return connection;
    }
    /**
     * Ferme les statements et resulset
     * @param statement
     * @param resultSet
     */
    public static void closeAll(Statement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ignored) {
        }
    }
}
