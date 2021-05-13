package fr.ul.miage.GenieLogiciel.controller;

import fr.ul.miage.GenieLogiciel.utils.ConfigReader;

import javax.sql.rowset.CachedRowSet;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.logging.Logger;


public class BddController {
    //Logger pour gérer les messages console
    private static final Logger LOG = Logger.getLogger(BddController.class.getName());
    private String url;
    private String user;
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
