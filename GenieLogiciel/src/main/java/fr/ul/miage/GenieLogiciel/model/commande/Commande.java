package fr.ul.miage.GenieLogiciel.model.commande;

import fr.ul.miage.GenieLogiciel.model.plat.Plat;
import fr.ul.miage.GenieLogiciel.model.service.Service;
import fr.ul.miage.GenieLogiciel.model.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id;
    private Table table;
    private CommandeStatut statut;
    private Service service;
    private List<CommandePlat> plats;

    private final CommandeRepository commandeRepository;

    public Commande() {
        this.plats = new ArrayList<>();
        this.statut = new CommandeStatut();
        this.commandeRepository = new CommandeRepository();
    }

    public int getId() {
        return id;
    }

    public Commande setId(int id) {
        this.id = id;
        return this;
    }

    public Table getTable() {
        return table;
    }

    public Commande setTable(Table table) {
        this.table = table;
        return this;
    }

    public CommandeStatut getStatut() {
        return statut;
    }

    public Commande setStatut(CommandeStatut statut) {
        this.statut = statut;
        return this;
    }

    public Service getService() {
        return service;
    }

    public Commande setService(Service service) {
        this.service = service;
        return this;
    }

    public List<CommandePlat> getPlats() {
        return plats;
    }

    public Commande setPlats(List<CommandePlat> plats) {
        this.plats = plats;
        return this;
    }

    public void ajouterPlat(Plat plat, int quantite) {
        if (quantite > 0) {
            this.plats.add(new CommandePlat().setPlat(plat).setQuantite(quantite));
        }
    }

    public void modifierStatut(String statut) {
        this.statut.setLibelle(statut);
    }

    public void ajouter() {
        commandeRepository.save(this);
    }

    public void save() {
        commandeRepository.save(this);
    }

    public void facturer() {
        plats.forEach(commandePlat -> {
            commandePlat.setEtat("TERMINE");
            commandePlat.update(id);
        });
        modifierStatut(CommandeStatut.STR_FACTUREE);
        table.setAdvancementMeal("TERMINE");
        table.save();
        save();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return id == commande.id;
    }

    @Override
    public String toString() {
        StringBuilder strPlats = new StringBuilder("(");
        for (CommandePlat commandePlat : plats) {
            strPlats.append(commandePlat.getPlat().getLibelle()).append("(x").append(commandePlat.getQuantite()).append(")");
            strPlats.append(", ");
        }
        strPlats.delete(strPlats.length() - 2, strPlats.length());
        strPlats.append(")");
        return id + " {table = " + table.getId() + "(" + table.getNbCouvert() + "🍽)" + ", statut = " + statut + ", service = " + service.getLibelle() + ", plats = " + strPlats + "}";
    }
}
