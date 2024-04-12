package Pharmacie.medicament;

import Pharmacie.Medicament;

import java.time.LocalDate;

public class MedicamentCommande extends Medicament {
    private LocalDate dateCommande;
    private String fournisseur;

    public MedicamentCommande(String nom, double prix, int quantiteEnStock, LocalDate dateCommande, String fournisseur) {
        super(nom, prix, quantiteEnStock);
        this.dateCommande = dateCommande;
        this.fournisseur = fournisseur;
    }

    // Getters and setters pour dateCommande et fournisseur
}
