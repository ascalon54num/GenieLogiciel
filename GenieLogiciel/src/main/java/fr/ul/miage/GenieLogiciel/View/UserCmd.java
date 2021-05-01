package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.user.User;
import fr.ul.miage.GenieLogiciel.model.user.UserRepository;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

import java.util.Map;

import static fr.ul.miage.GenieLogiciel.utils.Constantes.QUANTITE_MAX_USERS;


public class UserCmd {

    public void add() {
    	
        System.out.println();
        System.out.print("Nom de l'utilisateur : ");
        String nom = ScannerWithCheck.scannerStringUtilisateur(50);
        System.out.print("Prénom de l'utilisateur : ");
        String prenom = ScannerWithCheck.scannerStringUtilisateur(50);
        System.out.print("Login de l'utilisateur : ");
        String login = ScannerWithCheck.scannerStringUtilisateur(50);
        System.out.print("Role de l'utilisateur  [1: DIRECTOR, 2: HOTELMASTER, 3 : SERVER, 4 : COOK] : ");
        int role = ScannerWithCheck.scannerIntUtilisateur(false, 4);

        User user = new User();
        user.setNom(nom).setPrenom(prenom).setLogin(login).setRole(role);
        System.out.println("Utilisateur ajouté = " + user);
        
    }

    public void modifier() {
    	
    }
    
    public void delete() {
    	
        System.out.println();
        UserRepository userRepository = new UserRepository();
        Map<Integer, User> users = userRepository.findAll();
        users.forEach((id, user) -> System.out.println(user));
        System.out.print("Id de l'utilisateur à supprimer : ");
        int idUser = ScannerWithCheck.scannerIntUtilisateur(false, QUANTITE_MAX_USERS);

        if (users.containsKey(idUser)) {
        	User user = users.get(idUser);
        	userRepository.deleteById(idUser);
            //user.save();
            System.out.println("Utilisateur supprimé = " + user);
        } else {
            System.err.println("Erreur de saisie");
        }
        
    }

   

    public void liste() {
        System.out.println();
        System.out.println("Liste des utilisateurs :");
        UserRepository userRepository = new UserRepository();
        Map<Integer, User> users = userRepository.findAll();
        users.forEach((id, user) -> System.out.println(user));
    }

   
}
