package fr.ul.miage.GenieLogiciel.model.ingredient;

import fr.ul.miage.GenieLogiciel.controller.BddController;

import java.sql.*;
import java.util.TreeMap;
import java.util.Map;

public class IngredientRepository {


    public Map<Integer, Ingredient> findAll() {
        String query = "SELECT * FROM ingredient";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, Ingredient> ingredientsMap = new TreeMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient()
                        .setLibelle(resultSet.getString("libelle"))
                        .setQuantite(resultSet.getInt("quantite"))
                        .setId(resultSet.getInt("idIngredient"));
                ingredientsMap.put(ingredient.getId(), ingredient);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return ingredientsMap;
    }

    public Ingredient findOneById(int id) {
        String query = "SELECT * FROM ingredient WHERE idIngredient = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Ingredient ingredient = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ingredient = new Ingredient()
                        .setLibelle(resultSet.getString("libelle"))
                        .setQuantite(resultSet.getInt("quantite"))
                        .setId(resultSet.getInt("idIngredient"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return ingredient;
    }

    public Ingredient save(Ingredient ingredient) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isCreate = ingredient.getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO ingredient (libelle, quantite) VALUES (?, ?)";
            } else {
                query = "UPDATE ingredient SET libelle = ?, quantite = ? WHERE idIngredient = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ingredient.getLibelle());
            preparedStatement.setInt(2, ingredient.getQuantite());
            if (!isCreate) {
                preparedStatement.setInt(3, ingredient.getId());
            }
            preparedStatement.executeUpdate();

            if (isCreate) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    ingredient.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
        return ingredient;
    }

    public void deleteById(int id) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM ingredient WHERE idIngredient = ?";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
    }
}
