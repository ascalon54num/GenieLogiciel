package fr.ul.miage.GenieLogiciel.model.ingredient;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientPlatRepository {
    public List<IngredientPlat> findByPlat(Plat plat) {
        String query = "SELECT * FROM ingredient_plat WHERE idPlat = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<IngredientPlat> ingredientsPlat = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, plat.getId());
            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                ingredientsPlat.add(generateIngredientPlat(resultSet, plat));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return ingredientsPlat;
    }

    public boolean checkIfExistIngredientPlat(IngredientPlat ingredientPlat) {
        String query = "SELECT * FROM ingredient_plat WHERE idIngredient = ? and idPlat = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exist = false;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ingredientPlat.getIngredient().getId());
            preparedStatement.setInt(2, ingredientPlat.getPlat().getId());
            resultSet = preparedStatement.executeQuery(query);
            if (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return exist;
    }

    public IngredientPlat save(IngredientPlat ingredientPlat) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            boolean isCreate = !checkIfExistIngredientPlat(ingredientPlat);
            String query;
            if (isCreate) {
                query = "INSERT INTO ingredient_plat (quantite, idIngredient, idPlat) VALUES (?, ?, ?)";
            } else {
                query = "UPDATE ingredient_plat SET quantite = ? WHERE idIngredient = ? AND idPlat = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ingredientPlat.getQuantite());
            preparedStatement.setInt(2, ingredientPlat.getIngredient().getId());
            if (!isCreate) {
                preparedStatement.setInt(3, ingredientPlat.getPlat().getId());
            }
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
        return ingredientPlat;
    }

    public void deleteById(int idIngredient, int idPlat) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM ingredient WHERE idIngredient = ? AND idPlat = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idIngredient);
            preparedStatement.setInt(2, idPlat);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
    }

    private IngredientPlat generateIngredientPlat(ResultSet resultSet, Plat plat) throws SQLException {
        return new IngredientPlat()
                .setIngredient(new IngredientRepository().findOneById(resultSet.getInt("idIngredient")))
                .setQuantite(resultSet.getInt("quantite"))
                .setPlat(plat);
    }
}
