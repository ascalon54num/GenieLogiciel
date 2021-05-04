package fr.ul.miage.GenieLogiciel.model.categorie;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientPlatRepository;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import fr.ul.miage.GenieLogiciel.model.plat.PlatRepository;

import java.sql.*;
import java.util.TreeMap;
import java.util.Map;

public class CategorieRepository {
    public Categorie findOneById(int id) {
        String query = "SELECT * FROM categorie WHERE idCategorie = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categorie categorie = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categorie = new Categorie()
                        .setLibelle(resultSet.getString("libelle"))
                        .setId(resultSet.getInt("idCategorie"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return categorie;
    }

    public Map<Integer, Categorie> findAll() {
        String query = "SELECT * FROM categorie";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, Categorie> platMap = new TreeMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Categorie categorie = CategorieRepository.generateCategorie(resultSet);
                platMap.put(categorie.getId(), categorie);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return platMap;
    }

    public Map<Integer, Plat> findPlatsByIdCategory(int id) {
        String query = "SELECT * FROM plat WHERE idCategorie = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Plat> platMap = new TreeMap<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Plat plat = PlatRepository.generatePlat(resultSet);
                platMap.put(plat.getId(), plat);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return platMap;
    }

    public Categorie save(Categorie categorie) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        boolean isCreate = categorie.getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO categorie (libelle) VALUES (?)";
            } else {
                query = "UPDATE categorie SET libelle = ? WHERE idCategorie = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, categorie.getLibelle());
            if (!isCreate) {
                preparedStatement.setInt(2, categorie.getId());
            }
            preparedStatement.executeUpdate();

            if (isCreate) {
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    categorie.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, generatedKeys);
        }
        return categorie;
    }

    public void deleteById(int id) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM categorie WHERE idCategorie = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
    }

    public static Categorie generateCategorie(ResultSet resultSet) throws SQLException {
        return new Categorie()
                .setLibelle(resultSet.getString("libelle"))
                .setId(resultSet.getInt("idCategorie"));
    }
}
