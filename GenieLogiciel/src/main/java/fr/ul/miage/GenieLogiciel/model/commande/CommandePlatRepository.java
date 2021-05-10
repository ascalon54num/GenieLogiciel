package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.plat.PlatRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandePlatRepository {
    private final BddController bddController;

    public CommandePlatRepository() {
        this.bddController = new BddController();
    }

    public CommandePlat findOneByIds(int idCommande, int idPlat) {
        String query = "SELECT * FROM commande_plat WHERE idCommande = ? AND idPlat = ?";
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CommandePlat plat = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCommande);
            preparedStatement.setInt(2, idPlat);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                plat = generateCommandePlat(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return plat;
    }

    public CommandePlat save(CommandePlat commandePlat) {
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isCreate = commandePlat.getCommande().getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO commande_plat (quantite, idCommande, idPlat) VALUES (?, ?, ?)";
            } else {
                query = "UPDATE categorie SET quantite = ? WHERE idCommande = ? AND idPlat = ?";
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commandePlat.getQuantite());
            preparedStatement.setInt(2, commandePlat.getCommande().getId());
            preparedStatement.setInt(3, commandePlat.getPlat().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, null);
        }
        return commandePlat;
    }

    public List<CommandePlat> findByIdCommande(int idCommande) {
        String query = "SELECT * FROM commande_plat WHERE idCommande = ?";
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CommandePlat> plats = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCommande);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                plats.add(generateCommandePlat(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, resultSet);
        }

        return plats;
    }

    private static CommandePlat generateCommandePlat(ResultSet resultSet) throws SQLException {
        return new CommandePlat()
                .setPlat(new PlatRepository().findOneById(resultSet.getInt("idPlat")))
                .setCommande(new CommandeRepository().findOneById(resultSet.getInt("idCommande")))
                .setQuantite(resultSet.getInt("quantite"));
    }
}
