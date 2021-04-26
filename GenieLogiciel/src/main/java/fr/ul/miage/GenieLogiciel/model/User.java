package fr.ul.miage.GenieLogiciel.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import fr.ul.miage.GenieLogiciel.controller.BddController;

public class User {
	private static final Logger LOG = Logger.getLogger(User.class.getName());
	private int id;
	private String login;
	private String nom;
	private String prenom;
	private int role;
	
	//Ajouter un utilisateur
	public void addUser(int id, String login, String nom, String prenom, int role)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {
		Connection conn = new BddController().getConnection();
		Statement stmt = conn.createStatement();
	    String sql= " INSERT INTO UTILISATEUR VALUES ('"+id+"','"+login+"','"+nom+"','"+prenom+"','"+role+"');";
		int rs = stmt.executeUpdate(sql);
	}


	public User() {
		id= -1;
		login= null;
		nom=null;
		prenom=null;
		role=-1;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public int getRole() {
		// TODO Auto-generated method stub
		return role;
	}

	public void findByLogin(String login) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = new BddController().getConnection();
		Statement stmt = conn.createStatement();
	    String sql= "SELECT * FROM UTILISATEUR WHERE login ='"+login+"';";
		ResultSet rs = stmt.executeQuery(sql);
		 while(rs.next()){
			 this.id = rs.getInt("idUtilisateur");
			 this.login = rs.getString("login");
			 this.nom = rs.getString("nom");
			 this.prenom = rs.getString("prenom");
			 this.role = rs.getInt("idRole");
		 }
	}
	

}
