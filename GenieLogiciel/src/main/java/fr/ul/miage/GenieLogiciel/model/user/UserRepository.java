package fr.ul.miage.GenieLogiciel.model.user;

import fr.ul.miage.GenieLogiciel.controller.BddController;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {


    public Map<Integer, User> findAll() {
        String query = "SELECT * FROM UTILISATEUR";
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, User> usersMap = new HashMap<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	User user = new User(); 
            	user.setLogin(resultSet.getString("login"));
            	user.setNom(resultSet.getString("nom"));
            	user.setPrenom(resultSet.getString("prenom"));
            	user.setRole(resultSet.getInt("role"));
                user.setId(resultSet.getInt("idUtilisateur"));
                usersMap.put(user.getId(), user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, resultSet);
        }

        return usersMap;
    }

    public void addUser(String login, String nom, String prenom, int role) {
		String sql= "INSERT INTO UTILISATEUR (login, nom, prenom, idRole) VALUES ('"+login+"','"+nom+"','"+prenom+"','"+role+"');";
		Connection conn = new BddController().getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

    public void deleteById(int id) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;

        try {
            String query = "DELETE FROM UTILISATEUR WHERE idUtilisateur = "+id;
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, null);
        }
    }
    
    public void deleteByLogin(int login) {
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement statement = null;

        try {
            String query = "DELETE FROM UTILISATEUR WHERE login = '"+login+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, statement, null);
        }
    }
    
    public User findByLogin(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Connection conn = new BddController().getConnection();
		Statement stmt = conn.createStatement();
	    String sql= "SELECT * FROM UTILISATEUR WHERE login ='"+login+"';";
		ResultSet rs = stmt.executeQuery(sql);
		User u = null;
		 while(rs.next()){
			 u =new User();
			 u.setId(rs.getInt("idUtilisateur"));
			 u.setLogin(rs.getString("login"));
			 u.setNom(rs.getString("nom"));
			 u.setPrenom(rs.getString("prenom"));
			 u.setRole(rs.getInt("idRole"));
		 }
		 return u;
	}
}
