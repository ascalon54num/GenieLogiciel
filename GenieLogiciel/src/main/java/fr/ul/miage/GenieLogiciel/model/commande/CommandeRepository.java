package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.service.ServiceRepository;
import fr.ul.miage.GenieLogiciel.model.table.TableRepository;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class CommandeRepository {

    private final BddController bddController;

    public CommandeRepository() {
        this.bddController = new BddController();
    }

    public Map<Integer, Commande> findAll() {
        String query = "SELECT * FROM commande";
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, Commande> commandeMap = new TreeMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Commande commande = generateCommande(resultSet);
                commandeMap.put(commande.getId(), commande);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return commandeMap;
    }

    public Commande findOneById(int id) {
        String query = "SELECT * FROM commande WHERE idCommande = ?";
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Commande commande = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                commande = generateCommande(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }
        return commande;
    }

    public Commande save(Commande commande) {
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        boolean isCreate = commande.getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO commande (idTable, idStatutCommande, idService) VALUES (?, ?, ?)";
            } else {
                query = "UPDATE categorie SET idTable = ?, idStatutCommande = ?, idService = ? WHERE idCommande = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, commande.getTable().getId());
            preparedStatement.setInt(2, commande.getStatut().getId());
            preparedStatement.setInt(3, commande.getService().getId());
            if (!isCreate) {
                preparedStatement.setInt(4, commande.getId());
            }
            preparedStatement.executeUpdate();

            if (isCreate) {
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    commande.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, generatedKeys);
        }
        return commande;
    }

    private static Commande generateCommande(ResultSet resultSet) throws SQLException {
        return new Commande()
                .setId(resultSet.getInt("idCommande"))
                .setStatut(new CommandeStatutRepository().findOneById(resultSet.getInt("idStatutCommande")))
                .setPlats(new CommandePlatRepository().findByIdCommande(resultSet.getInt("idCommande")))
                .setService(new ServiceRepository().findOneById(resultSet.getInt("idService")))
                .setTable(new TableRepository().findOneById(resultSet.getInt("idTable")));
    }
}
