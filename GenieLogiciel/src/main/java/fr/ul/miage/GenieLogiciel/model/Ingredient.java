package fr.ul.miage.GenieLogiciel.model;

import fr.ul.miage.GenieLogiciel.controller.BddController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Ingredient {
    private int id;
    private String libelle;
    private int quantite;

    public int getId() {
        return id;
    }

    public Ingredient setId(int id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Ingredient setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public int getQuantite() {
        return quantite;
    }

    public Ingredient setQuantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

    public static Map<String, Ingredient> findAll() {
        String query = "SELECT * FROM ingredient";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Ingredient> ingredientsMap = new HashMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient()
                        .setLibelle(resultSet.getString("libelle"))
                        .setQuantite(resultSet.getInt("quantite"))
                        .setId(resultSet.getInt("idIngredient"));
                ingredientsMap.put(ingredient.getLibelle(), ingredient);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return ingredientsMap;
    }
}
