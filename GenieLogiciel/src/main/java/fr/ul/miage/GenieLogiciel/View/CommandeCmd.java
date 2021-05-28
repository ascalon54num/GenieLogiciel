package fr.ul.miage.GenieLogiciel.View;

import fr.ul.miage.GenieLogiciel.model.affectation.AffectationRepository;
import fr.ul.miage.GenieLogiciel.model.commande.*;
import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import fr.ul.miage.GenieLogiciel.model.plat.PlatRepository;
import fr.ul.miage.GenieLogiciel.model.service.Service;
import fr.ul.miage.GenieLogiciel.model.service.ServiceRepository;
import fr.ul.miage.GenieLogiciel.model.table.Table;
import fr.ul.miage.GenieLogiciel.model.table.TableRepository;
import fr.ul.miage.GenieLogiciel.model.user.User;
import fr.ul.miage.GenieLogiciel.utils.Consts;
import fr.ul.miage.GenieLogiciel.utils.Outil;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;
import fr.ul.miage.GenieLogiciel.utils.Session;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandeCmd {
    private final CommandeRepository commandeRepository;
    private final TableRepository tableRepository;
    private final ServiceRepository serviceRepository;
    private final PlatRepository platRepository;
    private final CommandePlatRepository commandePlatRepository;
    private final CommandeStatutRepository commandeStatutRepository;
	private final User currentUser;

    public CommandeCmd() {
        this.commandeRepository = new CommandeRepository();
        this.tableRepository = new TableRepository();
        this.serviceRepository = new ServiceRepository();
        this.platRepository = new PlatRepository();
        this.commandePlatRepository = new CommandePlatRepository();
        this.commandeStatutRepository = new CommandeStatutRepository();
        this.currentUser = Session.getInstance().getCurrentUser();
    }

    public void save(boolean isCreate) {
        Commande oldCommande = null;
        if (!isCreate) {
            Map<Integer, Commande> commandes;
            if(currentUser.getRole()==Consts.getConstants().get("ROLE").get("SERVER")) {
            	ArrayList<Integer> tablesServer = new AffectationRepository().findTableByServer(currentUser.getId());
            	commandes = commandeRepository.findAllIn(tablesServer);
            } else {
            	commandes = commandeRepository.findAll();
            }
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

        Map<Integer, Table> tables;
        if(currentUser.getRole()==Consts.getConstants().get("ROLE").get("SERVER")) {
        	ArrayList<Integer> tablesServer = new AffectationRepository().findTableByServer(currentUser.getId());
        	tables = tableRepository.findAllIn(tablesServer);
        } else {
        	tables = tableRepository.findAll();
        }
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

                if (plat.isDisponible()) {
                    int finalIdPlat = idPlat;
                    boolean isPlatNonChoisi = commandePlats.stream()
                            .map(commandePlat -> commandePlat.getPlat().getId()).noneMatch(id -> id == finalIdPlat);
                    if (isPlatNonChoisi) {
                        System.out.print("Quantité de ce plat : ");
                        quantite = ScannerWithCheck.scannerIntUtilisateur(false, -1);
                        commandePlats.add(new CommandePlat().setPlat(plat).setQuantite(quantite).setEtat(CommandePlat.EMIS));

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
        Map<Integer, Commande> commandes;
        if(currentUser.getRole()==Consts.getConstants().get("ROLE").get("SERVER")) {
        	ArrayList<Integer> tablesServer = new AffectationRepository().findTableByServer(currentUser.getId());
        	commandes = commandeRepository.findAllIn(tablesServer);
        } else {
        	commandes = commandeRepository.findAll();
        }
        commandes.forEach((id, commande) -> System.out.println(commande));
    }

    public void preparerPlatCommande() {
        Commande commande = commandeRepository.getProchaineCommandeAPreparerPlat();

        if (commande == null) {
            System.err.println("Il n'y a aucune commande en cours");
            Outil.waitTime(500);
            return;
        }

        System.out.println();
        List<CommandePlat> listePlatsAPreparer = commande.getPlats().stream().filter(commandePlat -> commandePlat.getEtat().equals(CommandePlat.EMIS)).collect(Collectors.toList());
        int platsSize = listePlatsAPreparer.size();
        if (platsSize > 0) {
            CommandePlat commandePlat = listePlatsAPreparer.get(0);
            if (commandePlat.checkIfIngredientsOk()) {
                System.out.println("Plat à préparer :");
                System.out.println(commandePlat.getPlat() + " x" + commandePlat.getQuantite());
                System.out.print("Voulez-vous préparer le plat suivant (1/0) ?");
                if (ScannerWithCheck.scannerIntUtilisateur(true, 1) == 1) {
                    for (int i = 0; i < commandePlat.getQuantite(); i++) {
                        commandePlat.preparer(commande.getId());
                        Outil.waitTime(1000);
                        System.out.println("Plat préparé !");
                    }
                    if (platsSize == 1) {
                        commande.modifierStatut(CommandeStatut.EN_COURS, CommandeStatut.STR_EN_COURS);
                        commande.save();
                    }
                }
            } else {
                Outil.waitTime(500);
                System.err.println("Il manque des ingrédients pour faire le plat: ");
                Outil.waitTime(100);
                System.out.println(commandePlat.getPlat() + " x" + commandePlat.getQuantite());
            }
        } else {
            System.err.println("Il n'y a aucun plat à préparer");
            Outil.waitTime(500);
        }
    }

    public void facturer() {
        Map<Integer, Commande> commandes;
        if(currentUser.getRole()==Consts.getConstants().get("ROLE").get("SERVER")) {
        	ArrayList<Integer> tablesServer = new AffectationRepository().findTableByServer(currentUser.getId());
        	commandes = commandeRepository.getCommandesWithStatusIn(CommandeStatut.EN_COURS,tablesServer);
        } else {
        	commandes = commandeRepository.getCommandesWithStatus(CommandeStatut.EN_COURS);
        }
        if (commandes.isEmpty()) {
            System.err.println("Il n'y a aucune commande à facturer");
            Outil.waitTime(500);
            return;
        }
        System.out.println();
        System.out.println("Liste des commandes à facturer :");
        commandes.forEach((id, commande) -> System.out.println(commande));
        int id = ScannerWithCheck.scannerIntUtilisateur(false, -1);
        Commande commande;
        if (commandes.containsKey(id)) {
            commande = commandes.get(id);
        } else {
            System.err.println("Erreur de saisie");
            Outil.waitTime(500);
            return;
        }
        double prixCommande = 0;
        double prixPlat;
        DecimalFormat df = new DecimalFormat("0.##");
        System.out.println("FACTURE: ");
        for (CommandePlat plat : commande.getPlats()) {
            prixPlat = plat.getPlat().getPrix() * plat.getQuantite();
            System.out.println(plat.getQuantite() + "x " + plat.getPlat().getLibelle() + " ==> " + df.format(prixPlat) + "€");
            prixCommande += prixPlat;
        }
        System.out.println("==================");
        System.out.println("TOTAL = " + df.format(prixCommande) + "€");
        commande.facturer();
    }

    public void visualiser() {
        System.out.println();
        System.out.println("Liste des commandes entrantes :");
        Map<Integer, Commande> commandes = commandeRepository.getCommandesWithStatus(CommandeStatut.EMISE);
        if(currentUser.getRole()==Consts.getConstants().get("ROLE").get("SERVER")) {
        	ArrayList<Integer> tablesServer = new AffectationRepository().findTableByServer(currentUser.getId());
        	commandes = commandeRepository.getCommandesWithStatusIn(CommandeStatut.EMISE,tablesServer);
        } else {
        	commandes = commandeRepository.getCommandesWithStatus(CommandeStatut.EMISE);
        }
        commandes.forEach((id, commande) -> System.out.println(commande));
    }

    public void servirPlatCommande() {
        List<CommandePlat> commandePlats = commandePlatRepository.findByEtat(CommandePlat.PRET);
        ArrayList<Integer> elemToRemove = new ArrayList<Integer>();
        if(currentUser.getRole()==Consts.getConstants().get("ROLE").get("SERVER")) {
        	ArrayList<Integer> tablesServer = new AffectationRepository().findTableByServer(currentUser.getId());
        	for (CommandePlat commandePlat : commandePlats) {
        		Commande commande = commandeRepository.findOneById(commandePlat.getIdCommande());
				if(!tablesServer.contains(commande.getTable().getId())) {
					elemToRemove.add(commandePlats.indexOf(commandePlat));
				}
			}
        }
        for (Integer i : elemToRemove) {
			commandePlats.remove(commandePlats.get(i));
		}
        if (commandePlats.isEmpty()) {
            System.err.println("Il n'y a aucun plat à servir");
            Outil.waitTime(500);
            return;
        }

        System.out.println();
        System.out.println("Liste des plats à servir :");
        for (int i = 1; i <= commandePlats.size(); i++) {
            System.out.println(i + " " + commandePlats.get(i - 1));
        }

        System.out.println("======================================");
        System.out.print("Numéro à servir : ");
        int id = ScannerWithCheck.scannerIntUtilisateur(false, commandePlats.size());
        CommandePlat commandePlat = commandePlats.get(id - 1);
        if (commandePlat != null) {
            commandePlat.setEtat(CommandePlat.SERVI);
            commandePlat.update(commandePlat.getIdCommande());
            Outil.waitTime(500);
            System.out.println("Servi !");
            Outil.waitTime(1000);
        } else {
            System.err.println("Erreur de saisie");
            Outil.waitTime(500);
        }
    }
}
