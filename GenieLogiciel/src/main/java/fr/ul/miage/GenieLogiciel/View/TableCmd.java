package fr.ul.miage.GenieLogiciel.View;

import static fr.ul.miage.GenieLogiciel.utils.Constantes.QUANTITE_MAX_INGREDIENT;

import java.util.Map;

import fr.ul.miage.GenieLogiciel.model.ingredient.Ingredient;
import fr.ul.miage.GenieLogiciel.model.table.Table;
import fr.ul.miage.GenieLogiciel.model.table.TableRepository;
import fr.ul.miage.GenieLogiciel.utils.ScannerWithCheck;



public class TableCmd {

	public TableCmd() {
		// TODO Auto-generated constructor stub
	}

	public void liste() {
		System.out.println();
        System.out.println("Liste des tables :");
        TableRepository tableRepository = new TableRepository();
        Map<Integer, Table> tables = tableRepository.findAll();
        tables.forEach((id, table) -> System.out.println(table));
	}
	
	public void add() {
        System.out.println();
        System.out.print("Nombre de couvert : ");
        int nbCouverts = ScannerWithCheck.scannerIntUtilisateur(false, 24);
        String statut = "Libre";

        Table newTable = new Table();
        newTable.setNbCouvert(nbCouverts).setStatut(statut).save();
        System.out.println("Table ajoutée = " + newTable);
    }
}
