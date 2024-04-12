package Pharmacie.medicament;

import Pharmacie.Medicament;

public class MedicamentOrdinaire extends Medicament {
    public MedicamentOrdinaire(String nom, double prix, int quantiteEnStock) {
        super(nom, prix, quantiteEnStock);
    }

    // Méthodes spécifiques aux médicaments délivrables librement
}
