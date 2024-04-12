package Pharmacie.medicament;

import Pharmacie.Medicament;

public class MedicamentOrdonnance extends Medicament {
    public MedicamentOrdonnance(String nom, double prix, int quantiteEnStock) {
        super(nom, prix, quantiteEnStock);
    }

    // Méthodes spécifiques aux médicaments nécessitant une ordonnance
    public boolean estDisponibleSurOrdonnance() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " (nécessite une ordonnance)";
    }
}

