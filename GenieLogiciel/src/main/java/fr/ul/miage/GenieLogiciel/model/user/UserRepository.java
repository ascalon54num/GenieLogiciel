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

    public User save(User user) {
    	
    	BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        boolean isCreate = user.getId() == 0;
        try {
            String query;
            if (isCreate) {
                query = "INSERT INTO UTILISATEUR (login, nom, prenom, idRole) VALUES (?, ?, ?, ?)";
            } else {
                query = "UPDATE UTILISATEUR SET login = ?, nom = ?, prenom = ?, idRole = ? WHERE idUtilisateur = ?";
            }
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getNom());
            preparedStatement.setString(3, user.getPrenom());
            preparedStatement.setInt(4, user.getRole());
            if (!isCreate) {
                preparedStatement.setInt(5, user.getId());
            }
            preparedStatement.executeUpdate();

            if (isCreate) {
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, preparedStatement, generatedKeys);
        }
        return user;
        
        /*
		String sql= "INSERT INTO UTILISATEUR (login, nom, prenom, idRole) VALUES ('"+user.getLogin()+"','"+user.getNom()+"','"+user.getPrenom()+"','"+user.getRole()+"');";
		Connection conn = new BddController().getConnection();
		Statement stmt = null;
        boolean isCreate = plat.getId() == 0;

		try {
			String query;
            if (isCreate) {
            	
            }
            else
            {
            	
            }
			stmt = conn.createStatement();
			
			int rowsAffected = 
					  stmt.executeUpdate( sql, Statement.RETURN_GENERATED_KEYS );  
			
			ResultSet  generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
                
                System.out.println("Insert avec succès : "+user.getId());
            }
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		*/
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

	public Map<Integer, User> findByRole(Integer id) {
		Connection conn = new BddController().getConnection();
	    String query= "SELECT * FROM UTILISATEUR WHERE idRole ='"+id+"';";
	    Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, User> usersMap = new HashMap<>();
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            	User user = new User(); 
            	user.setLogin(resultSet.getString("login"));
            	user.setNom(resultSet.getString("nom"));
            	user.setPrenom(resultSet.getString("prenom"));
            	user.setRole(resultSet.getInt("idRole"));
                user.setId(resultSet.getInt("idUtilisateur"));
                usersMap.put(user.getId(), user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(conn, statement, resultSet);
        }
		return usersMap;
	}
	
	public User findOneById(int id) {
        String query = "SELECT * FROM utilisateur WHERE idUtilisateur = "+id;
        
        BddController bddController = new BddController();
        Connection connection = bddController.getConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
		User user = null;
        try {
        	stmt = connection.createStatement();
        	resultSet = stmt.executeQuery(query);

    		 while(resultSet.next()){
    			 user = new User();
                 user.setLogin(resultSet.getString("login"));
             	user.setNom(resultSet.getString("nom"));
             	user.setPrenom(resultSet.getString("prenom"));
             	user.setRole(resultSet.getInt("idRole"));
                 user.setId(resultSet.getInt("idUtilisateur"));
    		 }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BddController.closeAll(connection, stmt, resultSet);
        }
        
		 return user;
		 

    }
}
