package fr.ul.miage.GenieLogiciel.model.service;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.commande.Commande;
import fr.ul.miage.GenieLogiciel.model.commande.CommandeRepository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ServiceRepository {
    private final BddController bddController;

    public ServiceRepository() {
        this.bddController = new BddController();
    }

    public Map<Integer, Service> findAll() {
        String query = "SELECT * FROM service";
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, Service> servicesMap = new TreeMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Service service = generateService(resultSet);
                servicesMap.put(service.getId(), service);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(statement, resultSet);
        }

        return servicesMap;
    }

    public Service findOneById(int id) {
        String query = "SELECT * FROM service WHERE idService = ?";
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service service = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                service = generateService(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(preparedStatement, resultSet);
        }

        return service;
    }

    public Service save(Service service) {
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isCreate = service.getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO service (libelle, dateDebut) VALUES (?, ?)";
            } else {
                query = "UPDATE service SET libelle = ?, dateDebut = ? WHERE idService = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, service.getLibelle());
            preparedStatement.setDate(2, Date.valueOf(service.getDateDebut()));
            if (!isCreate) {
                preparedStatement.setInt(3, service.getId());
            }
            preparedStatement.executeUpdate();

            if (isCreate) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    service.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(preparedStatement, null);
        }
        return service;
    }

    public void deleteById(int id) {
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM service WHERE idService = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(preparedStatement, null);
        }
    }

    public Map<Integer, Commande> getCommandesByIdService(int id) {
        String query = "SELECT * FROM commande WHERE idService = ?";
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Commande> commandes = new HashMap<>();
        Commande commande;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                commande = CommandeRepository.generateCommande(resultSet);
                commandes.put(commande.getId(), commande);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(preparedStatement, resultSet);
        }

        return commandes;
    }

    private static Service generateService(ResultSet resultSet) throws SQLException {
        return new Service()
                .setId(resultSet.getInt("idService"))
                .setLibelle(resultSet.getString("libelle"))
                .setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
    }
}
