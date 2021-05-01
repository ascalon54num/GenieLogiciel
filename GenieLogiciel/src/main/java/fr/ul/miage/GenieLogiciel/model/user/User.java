package fr.ul.miage.GenieLogiciel.model.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import fr.ul.miage.GenieLogiciel.controller.BddController;
import fr.ul.miage.GenieLogiciel.model.ingredient.IngredientRepository;

public class User {
	private static final Logger LOG = Logger.getLogger(User.class.getName());
	
    private final UserRepository userRepository;

	private int id;
	private String login;
	private String nom;
	private String prenom;
	private int role;
	
	
    public User() {
        this.userRepository = new UserRepository();
    }

    public User(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	 
	 public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
/*
	public User() {
		id= -1;
		login= null;
		nom=null;
		prenom=null;
		role=-1;
	}
*/

	public String getLogin() {
		return login;
	}

	public User setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getNom() {
		return nom;
	}

	public User setNom(String nom) {
		this.nom = nom;
		return this;
	}

	public String getPrenom() {
		return prenom;
	}

	public User setPrenom(String prenom) {
		this.prenom = prenom;
		return this; 
	}

	public User setRole(int role) {
		this.role = role;

	return this;
	}
	
	public int getRole() {
		// TODO Auto-generated method stub
		return role;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role + "]";
	}

	public void findByLogin(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
