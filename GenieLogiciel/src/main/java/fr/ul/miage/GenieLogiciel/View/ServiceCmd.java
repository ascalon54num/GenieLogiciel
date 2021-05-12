package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.commande.Commande;
import fr.ul.miage.GenieLogiciel.model.service.Service;
import fr.ul.miage.GenieLogiciel.model.service.ServiceRepository;
import fr.ul.miage.GenieLogiciel.utils.Outil;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

import java.time.LocalDate;
import java.util.Map;

public class ServiceCmd {
    private final ServiceRepository serviceRepository;

    public ServiceCmd() {
        this.serviceRepository = new ServiceRepository();
    }

    public void update(boolean isCreate) {
        Service oldService = null;
        if (!isCreate) {
            Map<Integer, Service> services = serviceRepository.findAll();
            if (services.isEmpty()) {
                System.err.println("Il n'y a aucun service d'enregistré");
                Outil.waitTime(500);
                return;
            }

            services.forEach((id, plat) -> System.out.println(plat));
            System.out.print("Id du service à modifier : ");
            int id = ScannerWithCheck.scannerIntUtilisateur(false, -1);
            if (services.containsKey(id)) {
                oldService = services.get(id);
            } else {
                System.err.println("Erreur de saisie");
                Outil.waitTime(500);
                return;
            }
        }

        System.out.println();
        System.out.print("Nom du service : ");
        String nom = ScannerWithCheck.scannerStringUtilisateur(100);

        System.out.print("Date début de service : ");
        LocalDate dateDebut = ScannerWithCheck.scannerDateUtilisateur();
        System.out.println();

        Service newService = new Service()
                .setLibelle(nom)
                .setDateDebut(dateDebut);

        if (!isCreate) {
            newService.setId(oldService.getId());
        }
        newService.save();

        System.out.println("Service ajouté/modifié = " + newService);
    }

    public void delete() {
        System.out.println();
        Map<Integer, Service> services = serviceRepository.findAll();
        if (services.isEmpty()) {
            System.err.println("Il n'y a pas de service d'enregistrée");
            Outil.waitTime(500);
            return;
        }
        services.forEach((id, service) -> System.out.println(service));
        System.out.print("Id du service à supprimer : ");
        int idService = ScannerWithCheck.scannerIntUtilisateur(false, -1);
        if (services.containsKey(idService)) {
            Map<Integer, Commande> commandesLieesAService = serviceRepository.getCommandesByIdService(idService);
            Service service = services.get(idService);
            if (commandesLieesAService.isEmpty()) {
                service.delete();
                System.out.println("Service supprimé = " + service);
            } else {
                System.err.println("Impossible de supprimer le service suivant = " + service);
                Outil.waitTime(500);
                System.out.println("Les commandes suivantes sont affectées à ce service :");
                for (Commande commande : commandesLieesAService.values()) {
                    System.out.println(commande);
                }
            }
        } else {
            System.err.println("Erreur de saisie");
        }
    }

    public void liste() {
        System.out.println();
        System.out.println("Liste des services :");
        Map<Integer, Service> services = serviceRepository.findAll();
        services.forEach((id, service) -> System.out.println(service));
    }
}
