package fr.ul.miage.GenieLogiciel.model.plat;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.categorie.CategorieRepository;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlatRepository;

import java.sql.*;
import java.util.TreeMap;
import java.util.Map;

public class PlatRepository {
    public Map<Integer, Plat> findAll() {
        String query = "SELECT * FROM plat";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, Plat> platMap = new TreeMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Plat plat = generatePlat(resultSet);
                platMap.put(plat.getId(), plat);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return platMap;
    }

    public Plat findOneById(int id) {
        String query = "SELECT * FROM plat WHERE idPlat = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Plat plat = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                plat = generatePlat(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return plat;
    }

    public Plat save(Plat plat) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        boolean isCreate = plat.getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO plat (libelle, prix, isPlatDuJour, idCategorie, isDisponible) VALUES (?, ?, ?, ?, ?)";
            } else {
                query = "UPDATE plat SET libelle = ?, prix = ?, isPlatDuJour = ?, idCategorie = ?, isDisponible = ? WHERE idPlat = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, plat.getLibelle());
            preparedStatement.setDouble(2, plat.getPrix());
            preparedStatement.setInt(3, plat.isPlatDuJour() ? 1 : 0);
            preparedStatement.setInt(4, plat.getCategorie().getId());
            preparedStatement.setInt(5, plat.isDisponible() ? 1 : 0);
            if (!isCreate) {
                preparedStatement.setInt(6, plat.getId());
            }
            preparedStatement.executeUpdate();

            if (isCreate) {
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    plat.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, generatedKeys);
        }
        return plat;
    }

    public void deleteById(int id) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM ingredient_plat WHERE idPlat = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            query = "DELETE FROM plat WHERE idPlat = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
    }

    private Plat generatePlat(ResultSet resultSet) throws SQLException {
        Plat plat = new Plat()
                .setCategorie(new CategorieRepository().findOneById(resultSet.getInt("idCategorie")))
                .setPlatDuJour(resultSet.getInt("isPlatDuJour"))
                .setDisponible(resultSet.getInt("isDisponible"))
                .setLibelle(resultSet.getString("libelle"))
                .setId(resultSet.getInt("idPlat"))
                .setPrix(resultSet.getDouble("prix"));
        plat.setIngredients(new IngredientPlatRepository().findByPlat(plat));
        return plat;
    }
}
