package fr.ul.miage.GenieLogiciel.model.service;

import java.time.LocalDate;

public class Service {
    private int id;
    private String libelle;
    private LocalDate dateDebut;
    private ServiceRepository repository;

    public Service() {
        this.repository = new ServiceRepository();
    }

    public int getId() {
        return id;
    }

    public Service setId(int id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Service setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Service setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void save() {
        repository.save(this);
    }

    public void delete() {
        repository.deleteById(id);
    }
}
