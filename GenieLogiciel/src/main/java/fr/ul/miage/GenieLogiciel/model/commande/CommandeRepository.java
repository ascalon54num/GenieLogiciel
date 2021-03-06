package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.service.ServiceRepository;
import fr.ul.miage.GenieLogiciel.model.table.TableRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
            BddController.closeAll(statement, resultSet);
        }

        return commandeMap;
    }

    public Map<Integer, Commande> getCommandesWithStatus(int idStatus) {
        String query = "SELECT * FROM commande WHERE idStatutCommande = ?";
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Commande> commandes = new HashMap<>();
        Commande commande;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idStatus);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                commande = generateCommande(resultSet);
                commandes.put(commande.getId(), commande);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(preparedStatement, resultSet);
        }
        return commandes;
    }

    public Commande getProchaineCommandeAPreparerPlat() {
        String query = "SELECT * FROM commande WHERE idStatutCommande = ? LIMIT 1";
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Commande commande = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, CommandeStatut.EMISE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                commande = generateCommande(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(preparedStatement, resultSet);
        }
        return commande;
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
            BddController.closeAll(preparedStatement, resultSet);
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
                query = "UPDATE commande SET idTable = ?, idStatutCommande = ?, idService = ? WHERE idCommande = ?";
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
            BddController.closeAll(preparedStatement, generatedKeys);
        }
        return commande;
    }

    public static Commande generateCommande(ResultSet resultSet) throws SQLException {
        return new Commande()
                .setId(resultSet.getInt("idCommande"))
                .setStatut(new CommandeStatutRepository().findOneById(resultSet.getInt("idStatutCommande")))
                .setPlats(new CommandePlatRepository().findByIdCommande(resultSet.getInt("idCommande")))
                .setService(new ServiceRepository().findOneById(resultSet.getInt("idService")))
                .setTable(new TableRepository().findOneById(resultSet.getInt("idTable")));
    }

	public Map<Integer, Commande> findAllIn(ArrayList<Integer> tablesServer) {
		String query;
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Commande> commandeMap = new TreeMap<>();
        	for (Integer integer : tablesServer) {
        		query= "SELECT * FROM commande WHERE idTable = ?";
        		try {
	        		preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, integer);
					resultSet = preparedStatement.executeQuery();
		            while (resultSet.next()) {
		                Commande commande = generateCommande(resultSet);
		                commandeMap.put(commande.getId(), commande);
		            }
        		} catch (SQLException exception) {
                    exception.printStackTrace();
                } finally {
                    BddController.closeAll(preparedStatement, resultSet);
                }
        	}
        return commandeMap;
	}

	public Map<Integer, Commande> getCommandesWithStatusIn(int idStatus, ArrayList<Integer> tablesServer) {
		String query;
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Commande> commandes = new HashMap<>();
        Commande commande;
        	for (Integer integer : tablesServer) {
				query = "SELECT * FROM commande WHERE idStatutCommande = ? AND idTable = ?";
				try {
		            preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setInt(1, idStatus);
		            preparedStatement.setInt(2, integer);
		            resultSet = preparedStatement.executeQuery();
		            while (resultSet.next()) {
		                commande = generateCommande(resultSet);
		                commandes.put(commande.getId(), commande);
		            }
	        	 } catch (SQLException exception) {
	                 exception.printStackTrace();
	             } finally {
	                 BddController.closeAll(preparedStatement, resultSet);
	             }
        	}
        return commandes;
	}
}
