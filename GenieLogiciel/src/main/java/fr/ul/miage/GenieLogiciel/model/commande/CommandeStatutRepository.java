package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.controller.BddController;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class CommandeStatutRepository {
    private final BddController bddController;

    public CommandeStatutRepository() {
        this.bddController = new BddController();
    }

    public Map<Integer, CommandeStatut> findAll() {
        String query = "SELECT * FROM statutcommande";
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, CommandeStatut> commandeStatutMap = new TreeMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CommandeStatut commandeStatut = generateCommandeStatut(resultSet);
                commandeStatutMap.put(commandeStatut.getId(), commandeStatut);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return commandeStatutMap;
    }

    public CommandeStatut findOneById(int id) {
        String query = "SELECT * FROM commande WHERE idStatutCommande = ?";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CommandeStatut statut = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                statut = generateCommandeStatut(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return statut;
    }

    private static CommandeStatut generateCommandeStatut(ResultSet resultSet) throws SQLException {
        return new CommandeStatut()
                .setId(resultSet.getInt("idStatutCommande"))
                .setLibelle(resultSet.getString("libelle"));
    }
}
