package fr.ul.miage.GenieLogiciel.model.user;

import java.util.logging.Logger;

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
	
	public void delete() {
        userRepository.deleteById(id);
    }
	
	//ajout ou MAJ d'un utilisateur
	public void save() {
        userRepository.save(this);
    }
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role + "]";
	}
	
}
