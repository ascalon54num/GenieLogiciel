package fr.ul.miage.GenieLogiciel.model.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import fr.ul.miage.GenieLogiciel.controller.BddController;

public class TableRepository {

	public Map<Integer, Table> findAll() {
		String query = "SELECT * FROM board";
		BddController bddController = new BddController();
		Connection connection = bddController.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Map<Integer, Table> tableMap = new HashMap<>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Table table = new Table().setStatut(resultSet.getString("statut"))
						.setNbCouvert(resultSet.getInt("nbCouvert")).setId(resultSet.getInt("idTable"));
				tableMap.put(table.getId(), table);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			BddController.closeAll(connection, statement, resultSet);
		}
		return tableMap;
	}

	public Table findOneById(int id) {
		String query = "SELECT * FROM board WHERE idTable = ?";
		BddController bddController = new BddController();
		Connection connection = bddController.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Table table = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				table = new Table().setStatut(resultSet.getString("statut")).setNbCouvert(resultSet.getInt("nbCouvert"))
						.setId(resultSet.getInt("idTable"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			BddController.closeAll(connection, preparedStatement, resultSet);
		}

		return table;
	}
	
	 public Table save(Table table) {
	        BddController bddController = new BddController();
	        Connection connection = bddController.getConnection();
	        PreparedStatement preparedStatement = null;
	        boolean isCreate = table.getId() == 0;
	        try {
	            String query;
	            if (isCreate) {
	                query = "INSERT INTO board (statut, nbCouvert) VALUES (?, ?)";
	            } else {
	                query = "UPDATE board SET statut = ?, nbCouvert = ? WHERE idTable = ?";
	            }
	            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	            preparedStatement.setString(1, table.getStatut());
	            preparedStatement.setInt(2, table.getNbCouvert());
	            if (!isCreate) {
	                preparedStatement.setInt(3, table.getId());
	            }
	            preparedStatement.executeUpdate();

	            if (isCreate) {
	                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    table.setId(generatedKeys.getInt(1));
	                }
	            }

	        } catch (SQLException exception) {
	            exception.printStackTrace();
	        } finally {
	            BddController.closeAll(connection, preparedStatement, null);
	        }
	        return table;
	    }

	 public void deleteById(int id) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM board WHERE idTable = ?";
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