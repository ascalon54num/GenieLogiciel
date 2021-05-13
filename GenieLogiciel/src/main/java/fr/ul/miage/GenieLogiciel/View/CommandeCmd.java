package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.commande.*;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import fr.ul.miage.GenieLogiciel.model.plat.PlatRepository;
import fr.ul.miage.GenieLogiciel.model.service.Service;
import fr.ul.miage.GenieLogiciel.model.service.ServiceRepository;
import fr.ul.miage.GenieLogiciel.model.table.Table;
import fr.ul.miage.GenieLogiciel.model.table.TableRepository;
import fr.ul.miage.GenieLogiciel.utils.Outil;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandeCmd {
    private final CommandeRepository commandeRepository;
    private final TableRepository tableRepository;
    private final ServiceRepository serviceRepository;
    private final PlatRepository platRepository;
    private final CommandePlatRepository commandePlatRepository;
    private final CommandeStatutRepository commandeStatutRepository;

    public CommandeCmd() {
        this.commandeRepository = new CommandeRepository();
        this.tableRepository = new TableRepository();
        this.serviceRepository = new ServiceRepository();
        this.platRepository = new PlatRepository();
        this.commandePlatRepository = new CommandePlatRepository();
        this.commandeStatutRepository = new CommandeStatutRepository();
    }

    public void update(boolean isCreate) {
        Commande oldCommande = null;
        if (!isCreate) {
            Map<Integer, Commande> commandes = commandeRepository.findAll();
            if (commandes.isEmpty()) {
                System.err.println("Il n'y a aucune commande d'enregistrée");
                Outil.waitTime(500);
                return;
            }

            commandes.forEach((id, plat) -> System.out.println(plat));
            System.out.print("Id de la commande à modifier : ");
            int id = ScannerWithCheck.scannerIntUtilisateur(false, -1);
            if (commandes.containsKey(id)) {
                oldCommande = commandes.get(id);
            } else {
                System.err.println("Erreur de saisie");
                Outil.waitTime(500);
                return;
            }
        }

        Map<Integer, Table> tables = tableRepository.findAll();
        if (tables.isEmpty()) {
            System.err.println("Il n'y a pas de table d'enregistrée");
            Outil.waitTime(500);
            return;
        }
        Map<Integer, Service> services = serviceRepository.findAll();
        if (services.isEmpty()) {
            System.err.println("Il n'y a pas de service d'enregistré");
            Outil.waitTime(500);
            return;
        }
        Map<Integer, Plat> plats = platRepository.findAll();
        if (plats.isEmpty()) {
            System.err.println("Il n'y a pas de plat d'enregistré");
            Outil.waitTime(500);
            return;
        }

        tables.forEach((id, t) -> System.out.println(t));
        System.out.println("=============================================================");
        System.out.print("Id de la table : ");
        int idTable = ScannerWithCheck.scannerIntUtilisateur(false, -1);
        Table table;
        if (tables.containsKey(idTable)) {
            table = tables.get(idTable);
        } else {
            System.err.println("Erreur de saisie");
            Outil.waitTime(500);
            return;
        }

        services.forEach((id, s) -> System.out.println(s));
        System.out.println("=============================================================");
        System.out.print("Id du service : ");
        int idService = ScannerWithCheck.scannerIntUtilisateur(false, -1);
        Service service;
        if (services.containsKey(idService)) {
            service = services.get(idService);
        } else {
            System.err.println("Erreur de saisie");
            Outil.waitTime(500);
            return;
        }

        List<CommandePlat> commandePlats = new ArrayList<>();
        boolean isFinishAddPlat = false;
        int idPlat, quantite;
        while (!isFinishAddPlat) {
            System.out.println();
            plats.forEach((id, plat) -> System.out.println(plat));
            System.out.println("=============================================================");
            System.out.print("Id du plat à ajouter : ");
            idPlat = ScannerWithCheck.scannerIntUtilisateur(false, -1);
            if (plats.containsKey(idPlat)) {
                Plat plat = plats.get(idPlat);

                if(plat.isDisponible()) {
                    int finalIdPlat = idPlat;
                    boolean isPlatNonChoisi = commandePlats.stream()
                            .map(commandePlat -> commandePlat.getPlat().getId()).noneMatch(id -> id == finalIdPlat);
                    if (isPlatNonChoisi) {
                        System.out.print("Quantité de ce plat : ");
                        quantite = ScannerWithCheck.scannerIntUtilisateur(false, -1);
                        commandePlats.add(new CommandePlat().setPlat(plat).setQuantite(quantite));

                        System.out.print("Ajouter un autre plat (1/0) ? : ");
                        isFinishAddPlat = ScannerWithCheck.scannerIntUtilisateur(true, 1) == 0;
                    } else {
                        System.err.println("Le plat a déjà été ajouté");
                        Outil.waitTime(500);
                    }
                } else {
                    System.err.println("Le plat sélectionné n'est pas disponible");
                    Outil.waitTime(500);
                }
            } else {
                System.err.println("Erreur de saisie");
                Outil.waitTime(500);
            }
        }

        Commande newCommande = new Commande()
                .setService(service)
                .setTable(table)
                .setPlats(commandePlats)
                .setStatut(commandeStatutRepository.findOneById(CommandeStatut.EMISE));
        if (!isCreate) {
            newCommande.setId(oldCommande.getId());
            commandePlatRepository.deleteByIdCommande(oldCommande.getId());
        }
        newCommande.ajouter();
        commandePlats.forEach(commandePlat -> commandePlatRepository.save(commandePlat, newCommande.getId()));
        System.out.println("Commande ajoutée/modifiée = " + newCommande);
    }

    public void liste() {
        System.out.println();
        System.out.println("Liste des commandes :");
        Map<Integer, Commande> commandes = commandeRepository.findAll();
        commandes.forEach((id, commande) -> System.out.println(commande));
    }

    public void visualiser() {
        System.out.println("Liste des commandes entrantes :");
        Map<Integer, Commande> commandes = commandeRepository.getCommandesWithStatus(CommandeStatut.EMISE);
        commandes.forEach((id, commande) -> System.out.println(commande));
    }
}
