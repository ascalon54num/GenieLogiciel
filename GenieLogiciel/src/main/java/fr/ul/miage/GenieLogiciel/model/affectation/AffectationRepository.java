package fr.ul.miage.GenieLogiciel.model.affectation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import fr.ul.miage.GenieLogiciel.controller.BddController;

public class AffectationRepository {
	
	public Map<Integer, Affectation> findAll() {
        String query = "SELECT * FROM affectation";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, Affectation> affectationsMap = new HashMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Affectation affectation = new Affectation()
                        .setIdTable(resultSet.getInt("idTable"))
                        .setIdServeur(resultSet.getInt("idServeur"))
                        .setId(resultSet.getInt("idAffectation"));
                affectationsMap.put(affectation.getId(), affectation);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return affectationsMap;
    }
	
	public Affectation save(Affectation affectation) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isCreate = affectation.getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO affectation (idServeur, idTable) VALUES (?, ?)";
            } else {
                query = "UPDATE affectation SET idServeur = ?, idTable = ? WHERE idAffectation = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, affectation.getIdServeur());
            preparedStatement.setInt(2, affectation.getIdTable());
            if (!isCreate) {
                preparedStatement.setInt(3, affectation.getId());
            }
            preparedStatement.executeUpdate();

            if (isCreate) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    affectation.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
        return affectation;
    }

    public void deleteById(int id) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM affectation WHERE idAffectation = ?";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
    }

	public Affectation findOneByTable(int tableId) {
		String query = "SELECT * FROM affectation WHERE idTable = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Affectation affectation = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tableId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                affectation = new Affectation()
                		.setIdTable(resultSet.getInt("idTable"))
                        .setIdServeur(resultSet.getInt("idServeur"))
                        .setId(resultSet.getInt("idAffectation"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return affectation;
	}

	public Affectation findOneById(int id) {
		String query = "SELECT * FROM affectation WHERE idAffectation = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Affectation affectation = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                affectation = new Affectation()
                		.setIdTable(resultSet.getInt("idTable"))
                        .setIdServeur(resultSet.getInt("idServeur"))
                        .setId(resultSet.getInt("idAffectation"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return affectation;
	}
    

}
