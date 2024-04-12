package Pharmacie.medicament;

import Pharmacie.Medicament;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireMedicaments {
    private List<Medicament> stock;

    public GestionnaireMedicaments() {
        this.stock = new ArrayList<>();
    }


    public void ajouterMedicament(Medicament medicament) {
        // Ajouter le médicament au stock
        stock.add(medicament);
    }

    public void supprimerMedicament(Medicament medicament) {
        // Supprimer le médicament du stock
        stock.remove(medicament);
    }

    public void commanderMedicament(String nom, int quantite) {
        // Commander un médicament
        // ...
    }

    public void vendreMedicament(String nom, int quantite) {
        // Vendre un médicament
        // ...
    }

    // Autres méthodes pour la gestion des médicaments

    public List<Medicament> getStock() {
        return stock;
    }

    public void setStock(List<Medicament> stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "GestionnaireMedicaments{" +
                "stock=" + stock +
                '}';
    }
    
}
