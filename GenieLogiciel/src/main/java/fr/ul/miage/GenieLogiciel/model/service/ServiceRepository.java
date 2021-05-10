package fr.ul.miage.GenieLogiciel.model.service;

import fr.ul.miage.GenieLogiciel.controller.BddController;

import java.sql.*;

public class ServiceRepository {
    private final BddController bddController;

    public ServiceRepository() {
        this.bddController = new BddController();
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
            BddController.closeAll(connection, preparedStatement, resultSet);
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
            BddController.closeAll(connection, preparedStatement, null);
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
            BddController.closeAll(connection, preparedStatement, null);
        }
    }

    private static Service generateService(ResultSet resultSet) throws SQLException {
        return new Service()
                .setId(resultSet.getInt("idService"))
                .setLibelle(resultSet.getString("libelle"))
                .setDateDebut(resultSet.getDate("dateDebut").toLocalDate());
    }
}
